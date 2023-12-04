package com.test.service.service.serviceImpl;

import com.test.service.config.yml.reader.DataSourceProperties;
import com.test.service.config.JDBCTemplateConfig;
import com.test.service.model.User;
import com.test.service.model.db.Mapping;
import com.test.service.service.DBService;
import com.test.service.service.SQLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBServiceImpl implements DBService {

    JDBCTemplateConfig JDBCTemplateConfig;
    DataSourceProperties dataSourceProperties;
    SQLBuilder sqlBuilder = new SQLBuilder();

    @Autowired
    public DBServiceImpl(JDBCTemplateConfig JDBCTemplateConfig, DataSourceProperties dataSourceProperties) {
        this.JDBCTemplateConfig = JDBCTemplateConfig;
        this.dataSourceProperties = dataSourceProperties;
    }

    public List<User> findAllUsers(User user) {
        List<JdbcTemplate> templates = JDBCTemplateConfig.getTemplates();
        int i = 0;
        List<User> result = new ArrayList<>();
        List<String> params;
        for (JdbcTemplate jdbcTemplate : templates) {
            params = new ArrayList<>();
            Mapping mapping = dataSourceProperties.getDataSources().get(i).getMapping();
            String resultSQL = sqlBuilder.buildSQL(dataSourceProperties.getDataSources().get(i).getTable(), user, mapping, params);
            result.addAll(jdbcTemplate.query(resultSQL, params.toArray(), (rs, rowNum) -> {
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
}
