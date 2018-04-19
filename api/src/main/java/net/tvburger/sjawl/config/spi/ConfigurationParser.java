package net.tvburger.sjawl.config.spi;

import net.tvburger.sjawl.config.Configuration;
import net.tvburger.sjawl.config.InvalidSpecificationException;
import net.tvburger.sjawl.config.NoSuchConfigurationException;
import net.tvburger.sjawl.config.Specification;

public interface ConfigurationParser {

    <T extends Configuration> boolean supportsConfiguration(Class<T> configurationTypeClass);

    <T extends Configuration> T parseConfiguration(Specification specification, Class<T> configurationTypeClass) throws InvalidSpecificationException;

    <T extends Configuration> boolean supportsDefaultConfiguration(Class<T> configurationTypeClass);

    <T extends Configuration> T getDefaultConfiguration(Class<T> configurationTypeClass) throws NoSuchConfigurationException;

}
