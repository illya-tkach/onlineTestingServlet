package org.itstep.dao.impl;

import org.itstep.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserAccountDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public TestDao createTestDao() {
        return new JDBCTestDao(getConnection());
    }

    @Override
    public QuestionDao createQuestionDao() {
        return new JDBCQuestionDao(getConnection());
    }


    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
