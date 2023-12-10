package com.test.service.user.repository.repoImpl;

import com.test.service.config.JDBCTemplateConfig;
import com.test.service.config.yml.reader.DataSourceProperties;
import com.test.service.db.model.Mapping;
import com.test.service.user.model.User;
import com.test.service.user.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DbUserRepository implements UserRepository {
    private final JDBCTemplateConfig jdbcTemplateConfig;
    private final DataSourceProperties dataSourceProperties;

    public DbUserRepository(JDBCTemplateConfig jdbcTemplateConfig, DataSourceProperties dataSourceProperties) {
        this.jdbcTemplateConfig = jdbcTemplateConfig;
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public List<User> getAllUsers(User user) {
        List<JdbcTemplate> templates = jdbcTemplateConfig.getTemplates();
        int i = 0;
        List<User> result = new ArrayList<>();
        List<String> params;
        for (JdbcTemplate jdbcTemplate : templates) {
            params = new ArrayList<>();
            Mapping mapping = dataSourceProperties.getDataSources().get(i).getMapping();
            String resultSQL = buildSQL(dataSourceProperties.getDataSources().get(i).getTable(), user, mapping, params);
            result.addAll(jdbcTemplate.query(resultSQL,
                    params.toArray(),
                    (rs, rowNum) -> {
                        User user1 = new User();
                        user1.setId(rs.getString(mapping.getId()));
                        user1.setName(rs.getString(mapping.getName()));
                        user1.setSurname(rs.getString(mapping.getSurname()));
                        user1.setUsername(rs.getString(mapping.getUsername()));
                        return user1;
                    }));
            i++;
        }
        return result;
    }

    private String buildSQL(String tableName, User user, Mapping mapping, List<String> params) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName);
        if (user.getId() != null) {
            appendCondition(sql, mapping.getId());
            params.add(user.getId());
        }
        if (user.getName() != null) {
            appendCondition(sql, mapping.getName());
            params.add(user.getName());
        }
        if (user.getUsername() != null) {
            appendCondition(sql, mapping.getUsername());
            params.add(user.getUsername());
        }
        if (user.getSurname() != null) {
            appendCondition(sql, mapping.getSurname());
            params.add(user.getSurname());
        }
        return sql.toString();
    }

    private void appendCondition(StringBuilder sql, String field) {
        if (sql.indexOf("WHERE") == -1) {
            sql.append(" WHERE ");
        } else {
            sql.append(" AND ");
        }
        sql.append(field).append(" = ?");
    }
}
