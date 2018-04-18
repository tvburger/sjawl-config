package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.common.AssertUtil;
import net.tvburger.sjawl.config.*;

import java.util.*;

public final class MapSpecification implements Specification {

    public static final class Factory {

        public static MapSpecification create(Map<List<String>, Object> settingsMap) {
            AssertUtil.assertNotNull(settingsMap);
            Map<List<String>, Object> clonedSettingsMap = new LinkedHashMap<>();
            for (Map.Entry<List<String>, Object> entry : settingsMap.entrySet()) {
                clonedSettingsMap.put(new ArrayList<>(entry.getKey()), entry.getValue());
            }
            return new MapSpecification(Collections.unmodifiableMap(clonedSettingsMap));
        }

        public static MapSpecification create(Specification specification) {
            Map<List<String>, Object> settingsMap = new LinkedHashMap<>();
            for (Setting setting : specification) {
                settingsMap.put(setting.getField(), setting.getValue());
            }
            return new MapSpecification(Collections.unmodifiableMap(settingsMap));
        }

        private Factory() {
        }

    }

    private final class SettingIterable implements Iterable<Setting> {

        private final List<String> fieldPrefix;

        private SettingIterable(List<String> fieldPrefix) {
            this.fieldPrefix = fieldPrefix;
        }

        @Override
        public Iterator<Setting> iterator() {
            return new SettingIterator(fieldPrefix);
        }

    }

    private final class SettingIterator implements Iterator<Setting> {

        private final Iterator<Map.Entry<List<String>, Object>> iterator = settingsMap.entrySet().iterator();
        private final List<String> fieldPrefix;
        private Setting next;

        private SettingIterator(List<String> fieldPrefix) {
            this.fieldPrefix = fieldPrefix;
        }

        @Override
        public boolean hasNext() {
            if (next == null) {
                setNext();
            }
            return next != null;
        }

        @Override
        public Setting next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Setting current = next;
            setNext();
            return current;
        }

        private void setNext() {
            next = null;
            while (next == null && iterator.hasNext()) {
                Map.Entry<List<String>, Object> entry = iterator.next();
                if (SettingUtil.prefixMatches(fieldPrefix, entry.getKey())) {
                    next = new Setting(entry.getKey(), entry.getValue());
                }
            }
        }


    }

    private final Map<List<String>, Object> settingsMap;

    MapSpecification(Map<List<String>, Object> settingsMap) {
        this.settingsMap = settingsMap;
    }

    @Override
    public Iterator<Setting> iterator() {
        return new SettingIterator(Collections.emptyList());
    }

    @Override
    public boolean hasSetting(List<String> field) {
        for (Setting setting : getSettings(field)) {
            if (SettingUtil.sameField(setting.getField(), field)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<List<String>> getDefined(List<String> fieldPrefix) {
        Set<String> nextLevelNames = new LinkedHashSet<>();
        for (Setting setting : getSettings(fieldPrefix)) {
            if (setting.getField().size() > fieldPrefix.size()) {
                nextLevelNames.add(setting.getField().get(fieldPrefix.size()));
            }
        }
        List<List<String>> fieldNames = new ArrayList<>();
        for (String nextLevelName : nextLevelNames) {
            List<String> defined = new ArrayList<>(fieldPrefix.size() + 1);
            defined.addAll(fieldPrefix);
            defined.add(nextLevelName);
            fieldNames.add(defined);
        }
        return fieldNames;
    }

    @Override
    public Iterable<Setting> getSettings(List<String> fieldPrefix) {
        return new SettingIterable(fieldPrefix);
    }

    @Override
    public Setting getSetting(List<String> field) throws MissingSettingException {
        for (Setting setting : getSettings(field)) {
            if (SettingUtil.sameField(setting.getField(), field)) {
                return setting;
            }
        }
        throw new MissingSettingException(this, field);
    }

}
