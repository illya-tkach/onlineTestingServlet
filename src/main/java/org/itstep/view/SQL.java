package org.itstep.view;

import org.apache.log4j.Logger;
import org.itstep.config.AppConfig;
import org.itstep.dao.exception.AppException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * SQL holder for field names and sql queries
 *
 * @author Tkach Illya
 */
public interface SQL {

    /* Logger */
    Logger log = Logger.getLogger(SQL.class);

    Properties sqlProperties = loadSqlProperties();
    /**
     * Load SQL queries from property file
     *
     * @return Properties object for SQL queries
     */
    static Properties loadSqlProperties() {
        try (InputStream inputStream = SQL.class.getResourceAsStream(AppConfig.SQL_PROPERTIES)) {
            Properties sqlProperties = new Properties();
            sqlProperties.load(inputStream);
            return sqlProperties;

        } catch (IOException e) {
            log.error(Messages.IO_EXCEPTION, e);
            throw new AppException(Messages.IO_EXCEPTION, e);
        }
    }

    static String getSqlElement(String key) {
        return sqlProperties.getProperty(key);
    }


    /* Fields */

    // Test
    String ALL_TESTS = "select.test.all";
    String SAVE_RATING = "insert.into.rating";
    // Question
    String QUESTIONS_BY_TEST_ID = "select.question.by.testID";
    // Answer
    String ANSWER_BY_QUESTION_ID = "select.answer.by.questionID";

    // UserAccount
    String FINT_BY_LGN_AND_PSWRD = "select.user.by.nameAndPassword";
    String USER_ROLES_BY_USER_ID = "select.user.role.by.userAccount";
    String SAVE_USER = "insert.into.userAccount";
    String SAVE_USER_ROLE = "insert.into.user.role";
}
