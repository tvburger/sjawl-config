package net.tvburger.sjawl.config.properties;


import net.tvburger.sjawl.common.AssertUtil;
import net.tvburger.sjawl.config.*;
import net.tvburger.sjawl.config.ext.SpecificationLoader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesFileLoader implements SpecificationLoader {

    public static final class Factory {

        public static PropertiesFileLoader create(File parentPath) {
            AssertUtil.assertIsDirectory(parentPath);
            return new PropertiesFileLoader(parentPath, PropertiesSpecificationParser.Singleton.get());
        }

        private Factory() {
        }

    }

    private final File parentPath;
    private final PropertiesSpecificationParser parser;

    public PropertiesFileLoader() {
        this(new File(System.getProperty("config.dir", System.getProperty("user.dir"))),
                PropertiesSpecificationParser.Singleton.get());
    }

    protected PropertiesFileLoader(File parentPath, PropertiesSpecificationParser parser) {
        this.parentPath = parentPath;
        this.parser = parser;
    }

    @Override
    public Specification loadSpecification(String specificationName) throws NoSuchSpecificationException, IOException {
        if (!hasSpecification(specificationName)) {
            throw new NoSuchSpecificationException(specificationName);
        }
        Properties properties = new Properties();
        properties.load(new FileReader(getConfigurationFile(specificationName)));
        return parser.parseSpecification(properties);
    }

    @Override
    public boolean hasSpecification(String specificationName) {
        return getConfigurationFile(specificationName).exists();
    }

    private File getConfigurationFile(String specificationName) {
        return new File(parentPath.getPath(), specificationName + ".properties");
    }

}
