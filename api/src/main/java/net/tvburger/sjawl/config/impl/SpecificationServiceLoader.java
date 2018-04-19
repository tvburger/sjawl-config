package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.config.NoSuchSpecificationException;
import net.tvburger.sjawl.config.Specification;
import net.tvburger.sjawl.config.SpecificationFormatException;
import net.tvburger.sjawl.config.SpecificationProvider;
import net.tvburger.sjawl.config.spi.SpecificationLoader;

import java.io.IOException;
import java.util.ServiceLoader;

public final class SpecificationServiceLoader implements SpecificationProvider {

    public static final class Singleton {

        public static final SpecificationServiceLoader INSTANCE = Factory.create();

        private Singleton() {
        }

        public static SpecificationServiceLoader get() {
            return INSTANCE;
        }

    }

    public static final class Factory {

        public static SpecificationServiceLoader create() {
            return new SpecificationServiceLoader(ServiceLoader.load(SpecificationLoader.class));
        }

        private Factory() {
        }

    }

    private final ServiceLoader<SpecificationLoader> serviceLoader;

    private SpecificationServiceLoader(ServiceLoader<SpecificationLoader> serviceLoader) {
        this.serviceLoader = serviceLoader;
    }

    @Override
    public boolean hasSpecification(String specificationName) {
        synchronized (serviceLoader) {
            for (SpecificationLoader loader : serviceLoader) {
                if (loader.hasSpecification(specificationName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Specification getSpecification(String specificationName) throws NoSuchSpecificationException, IOException, SpecificationFormatException {
        synchronized (serviceLoader) {
            for (SpecificationLoader loader : serviceLoader) {
                if (loader.hasSpecification(specificationName)) {
                    return loader.loadSpecification(specificationName);
                }
            }
            throw new NoSuchSpecificationException(specificationName);
        }
    }

}
