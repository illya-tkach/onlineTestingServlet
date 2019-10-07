package org.itstep.dao.impl;

import org.apache.log4j.Logger;
import org.itstep.dao.DaoConnection;
import org.itstep.dao.exception.AppException;
import org.itstep.view.Messages;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * JdbcDaoConnection
 *
 * @author Tkach Illya
 */
public class JdbcDaoConnection implements DaoConnection {

    /* Logger */
    private static final Logger log = Logger.getLogger(JdbcDaoConnection.class);

    private Connection connection;
    private boolean inTransaction = false;

    JdbcDaoConnection(Connection connection) {
        super();
        this.connection = connection;
    }

    Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        if (inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION_CONNECTION_CLOSE, e);
            throw new AppException(Messages.SQL_EXCEPTION_CONNECTION_CLOSE, e);
        }
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION_TRANSACTION_BEGIN, e);
            throw new AppException(Messages.SQL_EXCEPTION_TRANSACTION_BEGIN, e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION_TRANSACTION_COMMIT, e);
            throw new AppException(Messages.SQL_EXCEPTION_TRANSACTION_COMMIT, e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION_TRANSACTION_ROLLBACK, e);
            throw new AppException(Messages.SQL_EXCEPTION_TRANSACTION_ROLLBACK, e);
        }
    }
}
