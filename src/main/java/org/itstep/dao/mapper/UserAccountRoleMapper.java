package org.itstep.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountRoleMapper implements ObjectMapper {
    @Override
    public String extractFromResultSet(ResultSet rs) throws SQLException {
        return rs.getString("role_name");
    }
}
