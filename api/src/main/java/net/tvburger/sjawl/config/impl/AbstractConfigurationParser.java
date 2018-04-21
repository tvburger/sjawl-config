package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.config.Configuration;
import net.tvburger.sjawl.config.InvalidSpecificationException;
import net.tvburger.sjawl.config.NoSuchConfigurationException;
import net.tvburger.sjawl.config.Specification;
import net.tvburger.sjawl.config.spi.ConfigurationParser;

public abstract class AbstractConfigurationParser<T extends Configuration> implements ConfigurationParser {

    private final Class<T> configurationTypeClass;

    public AbstractConfigurationParser(Class<T> configurationTypeClass) {
        this.configurationTypeClass = configurationTypeClass;
    }

    public abstract T parseConfiguration(Specification specification) throws InvalidSpecificationException;

    public boolean supportsDefaultConfiguration() {
        return false;
    }

    public T getDefaultConfiguration() throws NoSuchConfigurationException {
        throw new NoSuchConfigurationException(configurationTypeClass);
    }

    @Override
    public <S extends Configuration> boolean supportsConfiguration(Class<S> configurationTypeClass) {
        return this.configurationTypeClass.equals(configurationTypeClass);
    }

    @Override
    public <S extends Configuration> S parseConfiguration(Specification specification, Class<S> configurationTypeClass) throws InvalidSpecificationException {
        assertType(configurationTypeClass);
        return configurationTypeClass.cast(parseConfiguration(specification));
    }

    @Override
    public <S extends Configuration> boolean supportsDefaultConfiguration(Class<S> configurationTypeClass) {
        assertType(configurationTypeClass);
        return supportsDefaultConfiguration();
    }

    @Override
    public <S extends Configuration> S getDefaultConfiguration(Class<S> configurationTypeClass) throws NoSuchConfigurationException {
        assertType(configurationTypeClass);
        return configurationTypeClass.cast(getDefaultConfiguration());
    }

    private <S extends Configuration> void assertType(Class<S> configurationTypeClass) {
        if (!supportsConfiguration(configurationTypeClass)) {
            throw new IllegalArgumentException();
        }
    }

}
