package net.tvburger.sjawl.config;

import net.tvburger.sjawl.config.impl.ConfigurationCache;
import net.tvburger.sjawl.config.impl.ConfigurationServiceLoader;

import java.io.IOException;

public interface ConfigurationProvider {

    final class Singleton {

        private static final ConfigurationProvider INSTANCE =
                ConfigurationCache.Factory.create(ConfigurationServiceLoader.Singleton.get());

        public static ConfigurationProvider get() {
            return INSTANCE;
        }

        private Singleton() {
        }

    }

    <T extends Configuration> T getConfiguration(Class<T> configurationTypeClass) throws NoSuchConfigurationException, NoSuchSpecificationException, IOException, SpecificationFormatException, InvalidSpecificationException;

}
