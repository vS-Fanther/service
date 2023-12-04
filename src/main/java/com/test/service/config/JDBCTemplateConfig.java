package com.test.service.config;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface JDBCTemplateConfig {
    public List<JdbcTemplate> getTemplates();
}
