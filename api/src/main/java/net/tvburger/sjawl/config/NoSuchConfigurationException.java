package net.tvburger.sjawl.config;

public class NoSuchConfigurationException extends ConfigurationException {

    private final Class<? extends Configuration> configurationTypeClass;

    public NoSuchConfigurationException(Class<? extends Configuration> configurationTypeClass) {
        this.configurationTypeClass = configurationTypeClass;
    }

    public NoSuchConfigurationException(Class<? extends Configuration> configurationTypeClass, String message) {
        super(message);
        this.configurationTypeClass = configurationTypeClass;
    }

    public NoSuchConfigurationException(Class<? extends Configuration> configurationTypeClass, String message, Throwable cause) {
        super(message, cause);
        this.configurationTypeClass = configurationTypeClass;
    }

    public NoSuchConfigurationException(Class<? extends Configuration> configurationTypeClass, Throwable cause) {
        super(cause);
        this.configurationTypeClass = configurationTypeClass;
    }

    public Class<? extends Configuration> getConfigurationTypeClass() {
        return configurationTypeClass;
    }

}
