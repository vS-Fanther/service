package com.test.service.config.yml.reader;

import com.test.service.db.model.InnerDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource(value = "classpath:ds.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "data-sources")
public class DataSourceProperties {

    private List<InnerDataSource> dataSources;

    public List<InnerDataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<InnerDataSource> dataSources) {
        this.dataSources = dataSources;
    }
}
