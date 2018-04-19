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
    public <T extends Configuration> T getConfiguration(Class<T> configurationTypeClass) throws NoSuchConfigurationException, NoSuchSpecificationException, IOException, SpecificationFormatException, InvalidSpecificationException {
        synchronized (serviceLoader) {
            for (ConfigurationParser parser : serviceLoader) {
                if (parser.supportsConfiguration(configurationTypeClass)) {
                    Specification specification = specificationProvider.getSpecification(configurationTypeClass.getName());
                    return parser.parseConfiguration(specification, configurationTypeClass);
                }
            }
            throw new NoSuchConfigurationException(configurationTypeClass);
        }
    }

}
