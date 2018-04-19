# sjawl-config
Standard Java Configuration Library

## Introduction

Almost all code need configuration, and this provides a extensible plug-n-play solution for your configuration needs.

The concept of configuration has been divided into 3 parts:
- the method of specifying configurations,
- the logic of interpreting a configuration specification, and
- the usage of a configuration.

## Specifying a configuration
The method can be defined by a SpecificationLoader using the java SPI mechanism.
Specify the avialable loaders using META-INF/services/net.tvburger.sjawl.config.spi.SpecificationLoader.

## Interpreting a configuration specification
The logic how to interpret a specification to obtain a configuration can be defined by a ConfigurationParser.
Specify the available parsers using META-INF/services/net.tvburger.sjwal.config.spi.ConfigurationParser.


## Using a Configuration
To use a configuration, use the ConfigurationProvider and ask for the specific configuration.

# Example
See: example/src/test/java/net/tvburger/sjawl/config/example/ExampleTest.java

```
    public void exampleConfigurationUsage() {
    
        // Obtain a configuration provider
        ConfigurationProvider provider = ConfigurationProvider.Singleton.get();
        
        // Get your configuration from the provider
        ExampleConfiguration configuration = provider.getConfiguration(ExampleConfiguration.class);
        
        // Use your configuration
        if (configuration.isSuperGame()) {
            System.out.println("What a great game!");
        }
        
    }
```

# Contact
tvburger@gmail.com, http://www.tvburger.net, https://github.com/tvburger/sjawl-config   