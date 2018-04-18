package net.tvburger.sjawl.config.ext;

import net.tvburger.sjawl.config.Configuration;
import net.tvburger.sjawl.config.InvalidSpecificationException;
import net.tvburger.sjawl.config.Specification;

public interface ConfigurationFactory {

    <T extends Configuration> boolean supportsConfiguration(Class<T> configurationTypeClass);

    <T extends Configuration> T createConfiguration(Specification specification, Class<T> configurationTypeClass) throws InvalidSpecificationException;

}
