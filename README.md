# sjawl-config
Standard Java Configuration Library

## Introduction

Almost all code need configuration, and this provides a extensible plug-n-play solution for your configuration needs.

The concept of configuration has been devided into 3 parts:
- system configuration of how to define configs (IT admins)
- specification of how to parse configurations (devs - creators)
- users of the configurations (devs - endusers)

## Specification Definition

The specification can be defined by a SpecificationLoader, and is bound using the META-INF/services construction.

## Definition Parsing

The creation of a configuration, by parsing the specification that has been loaded, is done by a ConfigurationFactory,
and is also bound using the META-INF/services.

## Using a Configuration

To use a configuration, use the ConfigurationProvider and ask for the type of configuration needed.

## Example
See: example/src/test/java/net/tvburger/sjawl/config/example/ExampleTest.java