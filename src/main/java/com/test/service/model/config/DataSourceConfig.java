package com.test.service.model.config;

public class DataSourceConfig {
    private String name;
    private String strategy;
    private String url;
    private String table;
    private String user;
    private String password;
    private MappingConfig mapping;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MappingConfig getMapping() {
        return mapping;
    }

    public void setMapping(MappingConfig mapping) {
        this.mapping = mapping;
    }
}
