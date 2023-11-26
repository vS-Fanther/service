package com.test.service.config.configImpl;

import com.test.service.config.DataSourceConfig;
import com.test.service.model.db.InnerDataSource;
import com.test.service.model.db.Mapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class DataSourceConfigImpl implements DataSourceConfig {
    private List<DataSource> dataSources;
    private List<InnerDataSource> innerDataSources = new ArrayList<>();
    private List<JdbcTemplate> templates;

    private void initDataSources() {
        dataSources = new ArrayList<>();
        innerDataSources = readYaml("ds.yaml");
        for (InnerDataSource innerDataSourcesConfig : innerDataSources) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl(innerDataSourcesConfig.getUrl());
            dataSource.setUsername(innerDataSourcesConfig.getUser());
            dataSource.setPassword(innerDataSourcesConfig.getPassword());
            dataSources.add(dataSource);
        }
    }

    private List<JdbcTemplate> initJdbcTemplates(List<DataSource> dataSources) {
        templates = new ArrayList<>();
        for (DataSource dataSource : dataSources) {
            templates.add(new JdbcTemplate(dataSource));
        }
        return templates;
    }

    private List<InnerDataSource> readYaml(String yamlFileName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = DataSourceConfigImpl.class
                .getClassLoader()
                .getResourceAsStream("ds.yaml");
        Object loadedObject = yaml.load(inputStream);
        List<InnerDataSource> result = new ArrayList<>();
        if (loadedObject instanceof LinkedHashMap) {
            LinkedHashMap<String, ArrayList<LinkedHashMap>> map = (LinkedHashMap<String, ArrayList<LinkedHashMap>>) loadedObject;
            ArrayList<LinkedHashMap> list = map.get("data-sources");
            for (LinkedHashMap dataSourceMap : list) {
                InnerDataSource innerDataSource = new InnerDataSource();
                innerDataSource.setName((String) dataSourceMap.get("name"));
                innerDataSource.setStrategy((String) dataSourceMap.get("strategy"));
                innerDataSource.setUrl((String) dataSourceMap.get("url"));
                innerDataSource.setTable((String) dataSourceMap.get("table"));
                innerDataSource.setUser((String) dataSourceMap.get("user"));
                innerDataSource.setPassword((String) dataSourceMap.get("password"));
                LinkedHashMap mappingMap = (LinkedHashMap) dataSourceMap.get("mapping");
                Mapping mapping = new Mapping();
                mapping.setId((String) mappingMap.get("id"));
                mapping.setUsername((String) mappingMap.get("username"));
                mapping.setName((String) mappingMap.get("name"));
                mapping.setSurname((String) mappingMap.get("surname"));
                innerDataSource.setMapping(mapping);
                result.add(innerDataSource);
            }
        }
        return result;
    }

    @Override
    public List<InnerDataSource> getInnerDataSources() {
        return innerDataSources;
    }

    @Override
    public List<JdbcTemplate> getTemplates() {
        if (dataSources == null && templates == null) {
            initDataSources();
            initJdbcTemplates(dataSources);
        }
        return templates;
    }
}
