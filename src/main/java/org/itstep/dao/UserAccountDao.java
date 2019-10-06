package org.itstep.dao;


import org.itstep.model.entity.UserAccount;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserAccountDao {
    Optional<UserAccount> getUserByLgnAndPswrd(String login, String password);
    List<String> getUserRolesByUserId(int id);
    UserAccount saveUser(UserAccount user) throws SQLException;
}
