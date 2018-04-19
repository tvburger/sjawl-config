package net.tvburger.sjawl.config;

import net.tvburger.sjawl.config.impl.SpecificationCache;
import net.tvburger.sjawl.config.impl.SpecificationServiceLoader;

import java.io.IOException;

public interface SpecificationProvider {

    final class Singleton {

        private static final SpecificationProvider INSTANCE =
                SpecificationCache.Factory.create(SpecificationServiceLoader.Singleton.get());

        public static SpecificationProvider get() {
            return INSTANCE;
        }

        private Singleton() {
        }

    }

    boolean hasSpecification(String specificationName);

    Specification getSpecification(String specificationName) throws NoSuchSpecificationException, IOException, SpecificationFormatException;

}
