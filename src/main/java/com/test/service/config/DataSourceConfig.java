package com.test.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataSourceConfig {

    public List<DataSource> dataSources() {
        List<com.test.service.model.db.DataSource> dataSourcesConfigs = DataSourceReader.getDataSources();
        List<DataSource> dataSources = new ArrayList<>();
        for (com.test.service.model.db.DataSource dataSourcesConfig : dataSourcesConfigs) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl(dataSourcesConfig.getUrl());
            dataSource.setUsername(dataSourcesConfig.getUser());
            dataSource.setPassword(dataSourcesConfig.getPassword());
            dataSources.add(dataSource);
        }
        return dataSources;
    }

    public List<JdbcTemplate> jdbcTemplates(List<DataSource> dataSources) {
        List<JdbcTemplate> templates = new ArrayList<>();
        for (DataSource dataSource : dataSources) {
            templates.add(new JdbcTemplate(dataSource));
        }
        return templates;
    }
}
