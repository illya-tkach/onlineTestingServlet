package org.itstep.dao;

import org.apache.log4j.Logger;
import org.itstep.config.AppConfig;
import org.itstep.dao.exception.AppException;
import org.itstep.view.Messages;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {

    /**
     * Returns DAO connection
     */
    public abstract DaoConnection getConnection();



    /**
     * DAO factory instance
     */
    private static DaoFactory instance;

    /* Logger */
    private static final Logger log = Logger.getLogger(DaoFactory.class);

    /**
     * Creates User DAO
     *
     * @param connection database connection
     * @return implementation of User DAO
     */
    public abstract UserAccountDao createUserDao(DaoConnection connection);

    /**
     * Creates Test DAO
     *
     * @param connection database connection
     * @return implementation of Test DAO
     */
    public abstract TestDao createTestDao(DaoConnection connection);

    /**
     * Creates Question DAO
     *
     * @param connection database connection
     * @return implementation of Question DAO
     */
    public abstract QuestionDao createQuestionDao(DaoConnection connection);

    /**
     * Creates TestRating DAO
     *
     * @param connection database connection
     * @return implementation of TestRating DAO
     */
    public abstract TestRatingDao createTestRatingDao(DaoConnection connection);

    /**
     * Get an instance of DAO factory
     *
     * @return singleton instance of DAO factory
     */
    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    try {
                        InputStream inputStream = DaoFactory.class.getResourceAsStream(AppConfig.DB_FILE);
                        Properties dbProps = new Properties();
                        dbProps.load(inputStream);
                        String factoryClass = dbProps.getProperty(AppConfig.DB_FACTORY_CLASS);
                        instance = (DaoFactory) Class.forName(factoryClass).newInstance();

                    } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                        log.error(Messages.DAO_FACTORY_EXCEPTION, e);
                        throw new AppException(Messages.DAO_FACTORY_EXCEPTION, e);
                    }
                }
            }
        }

        return instance;
    }
}