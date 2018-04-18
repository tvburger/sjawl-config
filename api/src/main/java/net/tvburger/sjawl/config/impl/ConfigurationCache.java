package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.common.*;
import net.tvburger.sjawl.config.*;

import java.io.IOException;

public final class ConfigurationCache implements ConfigurationProvider {

    public static final class Factory {

        private Factory() {
        }

        public static ConfigurationCache create(ConfigurationProvider provider) {
            AssertUtil.assertNotNull(provider);
            return new ConfigurationCache(new UnlimitedCache<>(), provider);
        }

    }

    private final Cache<Class<?>, Configuration> cache;
    private final ConfigurationProvider provider;

    private ConfigurationCache(Cache<Class<?>, Configuration> cache, ConfigurationProvider provider) {
        this.cache = cache;
        this.provider = provider;
    }

    @Override
    public <T extends Configuration> T getConfiguration(Class<T> configurationTypeClass) throws NoSuchConfigurationException, NoSuchSpecificationException, IOException, SpecificationFormatException, InvalidSpecificationException {
        Configuration configuration;
        if (!cache.has(configurationTypeClass)) {
            configuration = provider.getConfiguration(configurationTypeClass);
            cache.put(configurationTypeClass, configuration);
        } else {
            configuration = cache.get(configurationTypeClass);
        }
        return configurationTypeClass.cast(configuration);
    }

}
