package net.tvburger.sjawl.config;

import java.util.List;

public class MissingSettingException extends InvalidSpecificationException {

    private final List<String> settingField;

    public MissingSettingException(Specification configurationSpecification, List<String> settingField) {
        super(configurationSpecification);
        this.settingField = settingField;
    }

    public MissingSettingException(Specification configurationSpecification, List<String> settingField, String message) {
        super(configurationSpecification, message);
        this.settingField = settingField;
    }

    public MissingSettingException(Specification configurationSpecification, List<String> settingField, String message, Throwable cause) {
        super(configurationSpecification, message, cause);
        this.settingField = settingField;
    }

    public MissingSettingException(Specification configurationSpecification, List<String> settingField, Throwable cause) {
        super(configurationSpecification, cause);
        this.settingField = settingField;
    }

    public List<String> getSettingField() {
        return settingField;
    }

}
