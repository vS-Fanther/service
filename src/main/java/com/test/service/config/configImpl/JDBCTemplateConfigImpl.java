package com.test.service.config.configImpl;

import com.test.service.config.yml.reader.DataSourceProperties;
import com.test.service.config.JDBCTemplateConfig;
import com.test.service.db.model.InnerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JDBCTemplateConfigImpl implements JDBCTemplateConfig {

    List<JdbcTemplate> templates;
    DataSourceProperties dataSourceProperties;

    @Autowired
    public JDBCTemplateConfigImpl(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public List<JdbcTemplate> getTemplates() {
        if (templates == null) {
            templates = new ArrayList<>();
            for (InnerDataSource dataSource : dataSourceProperties.getDataSources()) {
                templates.add(new JdbcTemplate(DataSourceBuilder.create()
                        .url(dataSource.getUrl())
                        .username(dataSource.getUser())
                        .password(dataSource.getPassword())
                        .driverClassName("org.postgresql.Driver")
                        .build()));
            }
        }
        return templates;
    }
}
