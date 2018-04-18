package net.tvburger.sjawl.config.ext;

import net.tvburger.sjawl.config.NoSuchSpecificationException;
import net.tvburger.sjawl.config.Specification;
import net.tvburger.sjawl.config.SpecificationFormatException;

import java.io.IOException;

public interface SpecificationLoader {

    Specification loadSpecification(String specificationName) throws NoSuchSpecificationException, IOException, SpecificationFormatException;

    boolean hasSpecification(String specificationName);

}
