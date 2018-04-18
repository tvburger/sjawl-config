package net.tvburger.sjawl.config;

import java.util.Arrays;
import java.util.List;

public interface Specification extends Iterable<Specification.Setting> {

    class Setting {

        private final List<String> field;
        private final Object value;

        public Setting(List<String> field, Object value) {
            this.field = field;
            this.value = value;
        }

        public List<String> getField() {
            return field;
        }

        public Object getValue() {
            return value;
        }

    }

    List<List<String>> getDefined(List<String> fieldPrefix);

    default List<List<String>> getDefined(String... fieldPrefix) {
        return getDefined(Arrays.asList(fieldPrefix));
    }

    Iterable<Setting> getSettings(List<String> fieldPrefix);

    default Iterable<Setting> getSettings(String... fieldPrefix) {
        return getSettings(Arrays.asList(fieldPrefix));
    }

    boolean hasSetting(List<String> field);

    default boolean hasSetting(String... field) {
        return hasSetting(Arrays.asList(field));
    }

    Setting getSetting(List<String> field) throws MissingSettingException;

    default Setting getSetting(String... field) throws MissingSettingException {
        return getSetting(Arrays.asList(field));
    }

    default Object getValue(List<String> field) throws MissingSettingException {
        return getSetting(field).getValue();
    }

    default Object getValue(String... field) throws MissingSettingException {
        return getValue(Arrays.asList(field));
    }

}
