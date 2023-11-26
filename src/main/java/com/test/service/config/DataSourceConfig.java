package com.test.service.config;

import com.test.service.model.db.InnerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface DataSourceConfig {
    public List<JdbcTemplate> getTemplates();
    public List<InnerDataSource> getInnerDataSources();
}
