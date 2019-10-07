package org.itstep.dao;

import org.itstep.model.entity.UserAccount;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserAccountDao extends GenericDao<UserAccount> {

    /**
     * Finds user by its username
     *
     * @param login user's email
     * @param password user's password
     * @return optional user with given email
     */
    Optional<UserAccount> getUserByLgnAndPswrd(String login, String password);

    /**
     * Finds all users by specified role
     *
     * @param id           userAccount id
     * @return all roles of current userAccount
     */
    List<String> getUserRolesByUserId(int id);

    /**
     * Fetches number of rows for all users with specified role
     *
     * @param user useraccount
     * @return number of all users with specified role
     */
    UserAccount saveUser(UserAccount user) throws SQLException;
}
