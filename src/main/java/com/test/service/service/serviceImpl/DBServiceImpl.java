package com.test.service.service.serviceImpl;

import com.test.service.config.DataSourceConfig;
import com.test.service.model.User;
import com.test.service.model.db.InnerDataSource;
import com.test.service.model.db.Mapping;
import com.test.service.service.DBService;
import com.test.service.service.SQLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBServiceImpl implements DBService {

    DataSourceConfig dataSourceConfig;

    @Autowired
    public DBServiceImpl(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public List<User> findAllUsers(User user) {
        List<JdbcTemplate> templates = dataSourceConfig.getTemplates();
        List<InnerDataSource> innerDataSourcesConfigs = dataSourceConfig.getInnerDataSources();

        int i = 0;
        List<User> result = new ArrayList<>();

        for (JdbcTemplate jdbcTemplate : templates) {
            String sql = "SELECT * FROM " + innerDataSourcesConfigs.get(i).getTable();
            Mapping mapping = innerDataSourcesConfigs.get(i).getMapping();
            String resultSQL = SQLBuilder.buildSQL(sql, user, mapping);
            result.addAll(jdbcTemplate.query(resultSQL, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getString(mapping.getId()));
                    user.setName(rs.getString(mapping.getName()));
                    user.setSurname(rs.getString(mapping.getSurname()));
                    user.setUsername(rs.getString(mapping.getUsername()));
                    return user;
                }
            }));
            i++;
        }
        return result;
    }
}
