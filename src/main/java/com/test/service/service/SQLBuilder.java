package com.test.service.service;

import com.test.service.model.User;
import com.test.service.model.db.Mapping;

public class SQLBuilder {

    private static boolean isSQLContainsWhere = false;

    public static String buildSQL(String sql, User user, Mapping mapping) {
        String result = sql;
        if (user.getSurname() != null) {
            result += addCondition(mapping.getSurname(), result, user.getSurname());
        }
        if (user.getName() != null) {
            result += addCondition(mapping.getName(), result, user.getName());
        }
        if (user.getId() != null) {
            result += addCondition(mapping.getId(), result, user.getId());
        }
        if (user.getUsername() != null) {
            result += addCondition(mapping.getUsername(), result, user.getUsername());
        }
        isSQLContainsWhere = false;
        return result;
    }

    private static String addCondition(String field, String sql, String value) {
        if (!isSQLContainsWhere) {
            isSQLContainsWhere = true;
            return (" WHERE " + field + " = '" + value + "'");
        } else {
            return (" AND " + field + " = '" + value + "'");
        }
    }
}
