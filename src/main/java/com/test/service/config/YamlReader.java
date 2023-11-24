package com.test.service.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

public class YamlReader {
    private static Properties properties;
    private static Properties readYamlProperties(String yamlFileName) {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource(yamlFileName));
        return yaml.getObject();
    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = readYamlProperties("ds.yaml");
        }
        return properties;
    }
}
