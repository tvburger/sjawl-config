package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.config.Configuration;
import net.tvburger.sjawl.config.InvalidSpecificationException;
import net.tvburger.sjawl.config.Specification;
import net.tvburger.sjawl.config.spi.ConfigurationParser;

public abstract class AbstractConfigurationParser<T extends Configuration> implements ConfigurationParser {

    private final Class<T> configurationTypeClass;

    public AbstractConfigurationParser(Class<T> configurationTypeClass) {
        this.configurationTypeClass = configurationTypeClass;
    }

    public abstract T parseConfiguration(Specification specification) throws InvalidSpecificationException;

    @Override
    public <S extends Configuration> boolean supportsConfiguration(Class<S> configurationTypeClass) {
        return this.configurationTypeClass.equals(configurationTypeClass);
    }

    @Override
    public <S extends Configuration> S parseConfiguration(Specification specification, Class<S> configurationTypeClass) throws InvalidSpecificationException {
        if (!supportsConfiguration(configurationTypeClass)) {
            throw new IllegalArgumentException();
        }
        return configurationTypeClass.cast(parseConfiguration(specification));
    }

}
