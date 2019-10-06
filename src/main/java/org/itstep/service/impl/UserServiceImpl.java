package org.itstep.service.impl;


import org.itstep.dao.DaoFactory;
import org.itstep.dao.UserAccountDao;
import org.itstep.model.entity.UserAccount;
import org.itstep.service.UserService;
import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    UserAccountDao userDao = DaoFactory.getInstance().createUserDao();

    @Override
    public UserAccount save(UserAccount user) throws SQLException {
        return userDao.saveUser(user);
    }

    @Override
    public Optional<UserAccount> findUserByLgnAndPswrd(String email, String password) {
        Optional<UserAccount> userAccount = userDao.getUserByLgnAndPswrd(email, password);

        if (userAccount.isPresent())
            userAccount.get().setRoles(userDao.getUserRolesByUserId(userAccount.get().getId()));

        return userAccount;
    }

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;


}
