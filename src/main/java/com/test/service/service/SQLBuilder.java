package com.test.service.service;

import com.test.service.model.User;
import com.test.service.model.db.Mapping;

import java.util.ArrayList;
import java.util.List;

public class SQLBuilder {

    public String buildSQL(String tableName, User user, Mapping mapping, List<String> params) {
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
