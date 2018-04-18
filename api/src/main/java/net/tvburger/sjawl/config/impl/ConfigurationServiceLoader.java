package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.config.*;
import net.tvburger.sjawl.config.ext.ConfigurationFactory;

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
                    ServiceLoader.load(ConfigurationFactory.class),
                    SpecificationProvider.Singleton.get());
        }

    }

    private final ServiceLoader<ConfigurationFactory> serviceLoader;
    private final SpecificationProvider specificationProvider;

    private ConfigurationServiceLoader(ServiceLoader<ConfigurationFactory> serviceLoader, SpecificationProvider specificationProvider) {
        this.serviceLoader = serviceLoader;
        this.specificationProvider = specificationProvider;
    }

    @Override
    public <T extends Configuration> T getConfiguration(Class<T> configurationTypeClass) throws NoSuchConfigurationException, NoSuchSpecificationException, IOException, SpecificationFormatException, InvalidSpecificationException {
        synchronized (serviceLoader) {
            for (ConfigurationFactory factory : serviceLoader) {
                if (factory.supportsConfiguration(configurationTypeClass)) {
                    Specification specification = specificationProvider.getSpecification(configurationTypeClass.getName());
                    return factory.createConfiguration(specification, configurationTypeClass);
                }
            }
            throw new NoSuchConfigurationException(configurationTypeClass);
        }
    }

}
