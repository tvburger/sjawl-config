package net.tvburger.sjawl.config.properties;

import net.tvburger.sjawl.config.Specification;
import net.tvburger.sjawl.config.impl.MapSpecification;

import java.util.*;

public final class PropertiesSpecificationParser {

    public static final class Singleton {

        private static final PropertiesSpecificationParser INSTANCE = new PropertiesSpecificationParser();

        public static PropertiesSpecificationParser get() {
            return INSTANCE;
        }

        private Singleton() {
        }

    }

    public Specification parseSpecification(Properties properties) {
        Map<List<String>, Object> settingsMap = new LinkedHashMap<>();
        for (String stringPropertyName : properties.stringPropertyNames()) {
            settingsMap.put(createField(stringPropertyName), properties.getProperty(stringPropertyName));
        }
        return MapSpecification.Factory.create(settingsMap);
    }

    private List<String> createField(String stringPropertyName) {
        return Arrays.asList(stringPropertyName.split("\\."));
    }

}
