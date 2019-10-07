package org.itstep.dao.impl;

import org.itstep.controller.config.SecurityConfig;
import org.itstep.dao.IData;
import org.itstep.dao.UserAccountDao;
import org.itstep.dao.exception.AppException;
import org.itstep.dao.mapper.UserAccountMapper;
import org.itstep.dao.mapper.UserAccountRoleMapper;
import org.itstep.model.entity.UserAccount;
import org.itstep.view.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JDBCUserDao implements UserAccountDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<UserAccount> getUserByLgnAndPswrd(String username, String password) {
        UserAccount userAccount;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.FINT_BY_LGN_AND_PSWRD));
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            UserAccountMapper userMapper = new UserAccountMapper();

            while (rs.next()) {
                userAccount = userMapper.extractFromResultSet(rs);
                if (userAccount != null) return Optional.of(userAccount);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<String> getUserRolesByUserId(int userId) {
        List<String> userRoles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.USER_ROLES_BY_USER_ID));
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            UserAccountRoleMapper roleMapper = new UserAccountRoleMapper();

            while (rs.next()) {
                userRoles.add(roleMapper.extractFromResultSet(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userRoles;
    }

    @Override
    public List<String> saveUserRole(UserAccount userAccount) {
        try {
        List<String> roles = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_USER_ROLE));
        preparedStatement.setInt(1, userAccount.getId());
        preparedStatement.setInt(2, 1);
        preparedStatement.executeUpdate();
        roles.add(SecurityConfig.ROLE_CLIENT);
        return roles;
        } catch (SQLException e){
            throw new AppException(Messages.SQL_ROLE_ERROR, e);
        }
    }

    @Override
    public Optional<UserAccount> findById(Long id) {
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
    }

    @Override
    public List<UserAccount> findAll() {
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
    }

    @Override
    public UserAccount create(UserAccount user) {
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_USER), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());

        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            user.setId(generatedKeys.getInt(1));
        return user;
        } catch (SQLException e) {
            throw new AppException(Messages.SQL_DUPLICATE, e);
        }
    }

    @Override
    public void update(UserAccount userAccount) {
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
    }
}
