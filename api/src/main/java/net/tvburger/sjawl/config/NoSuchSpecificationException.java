package net.tvburger.sjawl.config;

public class NoSuchSpecificationException extends ConfigurationException {

    private final String specificationName;

    public NoSuchSpecificationException(String specificationName) {
        this.specificationName = specificationName;
    }

    public NoSuchSpecificationException(String specificationName, String message) {
        super(message);
        this.specificationName = specificationName;
    }

    public NoSuchSpecificationException(String specificationName, String message, Throwable cause) {
        super(message, cause);
        this.specificationName = specificationName;
    }

    public NoSuchSpecificationException(String specificationName, Throwable cause) {
        super(cause);
        this.specificationName = specificationName;
    }

    public String getSpecificationName() {
        return specificationName;
    }

}
