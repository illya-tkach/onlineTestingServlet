package org.itstep.dao.mapper;

import org.itstep.model.entity.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserAccountMapper implements ObjectMapper<UserAccount> {


    @Override
    public UserAccount extractFromResultSet(ResultSet rs) throws SQLException {
        UserAccount userAccount = UserAccount.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .build();
        return userAccount;
    }

}
