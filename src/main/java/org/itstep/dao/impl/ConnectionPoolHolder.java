package org.itstep.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.itstep.dao.IData;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(IData.getSqlElement(IData.DATABASE_URL));
                    ds.setUsername(IData.getSqlElement(IData.DATABASE_USER));
                    ds.setPassword(IData.getSqlElement(IData.DATABASE_PASSWORD));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }


}