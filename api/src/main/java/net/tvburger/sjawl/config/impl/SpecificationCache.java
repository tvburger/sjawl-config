package net.tvburger.sjawl.config.impl;

import net.tvburger.sjawl.common.AssertUtil;
import net.tvburger.sjawl.common.Cache;
import net.tvburger.sjawl.common.UnlimitedCache;
import net.tvburger.sjawl.config.NoSuchSpecificationException;
import net.tvburger.sjawl.config.Specification;
import net.tvburger.sjawl.config.SpecificationFormatException;
import net.tvburger.sjawl.config.SpecificationProvider;

import java.io.IOException;

public final class SpecificationCache implements SpecificationProvider {

    public static final class Factory {

        public static SpecificationCache create(SpecificationProvider provider) {
            AssertUtil.assertNotNull(provider);
            return new SpecificationCache(new UnlimitedCache<>(), provider);
        }

        private Factory() {
        }

    }

    private final Cache<String, Specification> cache;
    private final SpecificationProvider provider;

    private SpecificationCache(Cache<String, Specification> cache, SpecificationProvider provider) {
        this.cache = cache;
        this.provider = provider;
    }

    @Override
    public boolean hasSpecification(String specificationName) {
        return cache.has(specificationName) || provider.hasSpecification(specificationName);
    }

    @Override
    public Specification getSpecification(String specificationName) throws NoSuchSpecificationException, IOException, SpecificationFormatException {
        Specification specification;
        if (!cache.has(specificationName)) {
            specification = provider.getSpecification(specificationName);
            cache.put(specificationName, specification);
        } else {
            specification = cache.get(specificationName);
        }
        return specification;
    }

}
