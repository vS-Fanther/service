package com.test.service.service;

import com.test.service.config.DataSourceConfig;
import com.test.service.config.DataSourceReader;
import com.test.service.model.User;
import com.test.service.model.db.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBService {

    DataSourceConfig dataSourceConfig;

    @Autowired
    public DBService(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public List<User> findAllUsers() {
        List<com.test.service.model.db.DataSource> dataSourcesConfigs = DataSourceReader.getDataSources();

        int i = 0;
        List<User> result = new ArrayList<>();

        for (JdbcTemplate jdbcTemplate : dataSourceConfig.jdbcTemplates(dataSourceConfig.dataSources())) {
            String sql = "SELECT * FROM " + dataSourcesConfigs.get(i).getTable();
            Mapping mapping = dataSourcesConfigs.get(i).getMapping();

            result.addAll(jdbcTemplate.query(sql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getString(mapping.getId()));
                    user.setName(rs.getString(mapping.getName()));
                    user.setSurname(rs.getString(mapping.getSurname()));
                    user.setUsername(rs.getString(mapping.getUsername()));
                    // установите другие поля пользователя
                    return user;
                }
            }));
            i++;
        }
        return result;
    }
}
