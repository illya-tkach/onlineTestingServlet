package org.itstep.dao.impl;

import org.itstep.controller.config.SecurityConfig;
import org.itstep.dao.IData;
import org.itstep.dao.UserAccountDao;
import org.itstep.dao.mapper.UserAccountMapper;
import org.itstep.dao.mapper.UserAccountRoleMapper;
import org.itstep.model.entity.UserAccount;

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
    public UserAccount saveUser(UserAccount user) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_USER), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());

        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Duplicate username or password");
        }


        int userId;
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        List<String> roles;

        try {
            roles = saveUserRole(userId);
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("role save exception");
        }

        connection.commit();
        return  UserAccount.builder()
                .id(userId)
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();

    }

    public List<String> saveUserRole(int userId) throws SQLException {
        List<String> roles = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_USER_ROLE));
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, 1);
        preparedStatement.executeUpdate();
        roles.add(SecurityConfig.ROLE_CLIENT);
        return roles;
    }

    @Override
    public Optional<UserAccount> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserAccount> findAll() {
        return null;
    }

    @Override
    public void create(UserAccount userAccount) {

    }

    @Override
    public void update(UserAccount userAccount) {

    }

    @Override
    public void delete(Long id) {

    }
}
