package org.itstep.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.itstep.config.AppConfig;
import org.itstep.dao.DaoFactory;
import org.itstep.view.Messages;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolHolder {

/* Logger */
private static final Logger log = Logger.getLogger(ConnectionPoolHolder.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    InputStream inputStream = DaoFactory.class.getResourceAsStream(AppConfig.DB_FILE);
                    Properties dbProps = new Properties();
                    try {
                        dbProps.load(inputStream);
                        BasicDataSource basicDataSource = new BasicDataSource();

                        basicDataSource.setDriverClassName(dbProps.getProperty(AppConfig.JDBC_DRIVER_CLASS_NAME));
                        basicDataSource.setUrl(dbProps.getProperty(AppConfig.JDBC_URL));
                        basicDataSource.setUsername(dbProps.getProperty(AppConfig.JDBC_USERNAME));
                        basicDataSource.setPassword(dbProps.getProperty(AppConfig.JDBC_PASSWORD));

                        dataSource = basicDataSource;

                    } catch (IOException e) {
                        log.error(Messages.CONNECTION_POOL_EXCEPTION, e);
                    }
                }
            }
        }
        return dataSource;
    }

}