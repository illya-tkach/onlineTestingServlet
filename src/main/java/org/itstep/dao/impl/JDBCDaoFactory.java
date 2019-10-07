package org.itstep.dao.impl;

import org.apache.log4j.Logger;
import org.itstep.dao.*;
import org.itstep.dao.exception.AppException;
import org.itstep.view.Messages;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    /* Logger */
    private static final Logger log = Logger.getLogger(JDBCDaoFactory.class);

    /**
     * Pooled Data Source
     */
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public UserAccountDao createUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();

        return new JDBCUserDao(sqlConnection);
    }

    @Override
    public TestDao createTestDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();

        return new JDBCTestDao(sqlConnection);
    }

    @Override
    public QuestionDao createQuestionDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();

        return new JDBCQuestionDao(sqlConnection);
    }
}
