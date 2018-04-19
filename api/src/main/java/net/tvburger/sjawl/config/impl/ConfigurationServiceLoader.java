package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.config.*;
import net.tvburger.sjawl.config.spi.ConfigurationParser;

import java.io.IOException;
import java.util.ServiceLoader;

public final class ConfigurationServiceLoader implements ConfigurationProvider {

    public static final class Singleton {

        public static final ConfigurationServiceLoader INSTANCE = Factory.create();

        private Singleton() {
        }

        public static ConfigurationServiceLoader get() {
            return INSTANCE;
        }

    }

    public static final class Factory {

        private Factory() {
        }

        public static ConfigurationServiceLoader create() {
            return new ConfigurationServiceLoader(
                    ServiceLoader.load(ConfigurationParser.class),
                    SpecificationProvider.Singleton.get());
        }

    }

    private final ServiceLoader<ConfigurationParser> serviceLoader;
    private final SpecificationProvider specificationProvider;

    private ConfigurationServiceLoader(ServiceLoader<ConfigurationParser> serviceLoader, SpecificationProvider specificationProvider) {
        this.serviceLoader = serviceLoader;
        this.specificationProvider = specificationProvider;
    }

    @Override
    public <T extends Configuration> boolean hasConfiguration(Class<T> configurationTypeClass) {
        synchronized (serviceLoader) {
            for (ConfigurationParser parser : serviceLoader) {
                if (parser.supportsConfiguration(configurationTypeClass)) {
                    return parser.supportsDefaultConfiguration(configurationTypeClass) || specificationProvider.hasSpecification(toSpecificationName(configurationTypeClass));
                }
            }
        }
        return false;
    }

    @Override
    public <T extends Configuration> T getConfiguration(Class<T> configurationTypeClass) throws NoSuchConfigurationException, NoSuchSpecificationException, IOException, SpecificationFormatException, InvalidSpecificationException {
        synchronized (serviceLoader) {
            for (ConfigurationParser parser : serviceLoader) {
                if (parser.supportsConfiguration(configurationTypeClass)) {
                    String specificationName = toSpecificationName(configurationTypeClass);
                    if (specificationProvider.hasSpecification(specificationName)) {
                        Specification specification = specificationProvider.getSpecification(toSpecificationName(configurationTypeClass));
                        return parser.parseConfiguration(specification, configurationTypeClass);
                    } else if (parser.supportsDefaultConfiguration(configurationTypeClass)) {
                        return parser.getDefaultConfiguration(configurationTypeClass);
                    }
                }
            }
            throw new NoSuchConfigurationException(configurationTypeClass);
        }
    }

    private <T extends Configuration> String toSpecificationName(Class<T> configurationTypeClass) {
        return configurationTypeClass.getName();
    }

}
