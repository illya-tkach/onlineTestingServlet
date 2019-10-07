package org.itstep.service.impl;


import org.apache.log4j.Logger;
import org.itstep.dao.DaoConnection;
import org.itstep.dao.DaoFactory;
import org.itstep.dao.UserAccountDao;
import org.itstep.model.entity.UserAccount;
import org.itstep.service.UserService;
import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    /* Logger */
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Lazy holder for service instance
     */
    private static class Holder {
        static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImpl.Holder.INSTANCE;
    }

    @Override
    public UserAccount save(UserAccount user) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserAccountDao accountDao = daoFactory.createUserDao(connection);
            return accountDao.saveUser(user);
        }
    }

    @Override
    public Optional<UserAccount> findUserByLgnAndPswrd(String email, String password) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserAccountDao accountDao = daoFactory.createUserDao(connection);
            Optional<UserAccount> userAccount = accountDao.getUserByLgnAndPswrd(email, password);
            if (userAccount.isPresent())
                userAccount.get().setRoles(accountDao.getUserRolesByUserId(userAccount.get().getId()));
            return userAccount;
        }
    }


}
