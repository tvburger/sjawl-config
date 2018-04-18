package net.tvburger.sjawl.config.example;

import net.tvburger.sjawl.config.*;
import net.tvburger.sjawl.config.ext.ConfigurationFactory;

public final class ExampleConfigurationFactory implements ConfigurationFactory {

    private final ExampleSpecificationParser parser = new ExampleSpecificationParser();

    @Override
    public <T extends Configuration> boolean supportsConfiguration(Class<T> configurationTypeClass) {
        return ExampleConfiguration.class.equals(configurationTypeClass);
    }

    @Override
    public <T extends Configuration> T createConfiguration(Specification specification, Class<T> configurationTypeClass) throws InvalidSpecificationException {
        return configurationTypeClass.cast(parser.parseExampleConfiguration(specification));
    }

}
