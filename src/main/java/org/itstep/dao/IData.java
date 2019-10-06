package org.itstep.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface IData {

    Properties sqlProperties = loadSqlProperties();
    /* Database config */
    String DATABASE_URL = "database.url";
    String DATABASE_USER = "database.user";
    String DATABASE_PASSWORD = "database.password";
    /* IData keys */
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

    static Properties loadSqlProperties() {
        try (InputStream inputStream = IData.class.getResourceAsStream("/sql.properties")) {
            Properties sqlProperties = new Properties();
            sqlProperties.load(inputStream);
            return sqlProperties;

        } catch (IOException e) {

        }
        return null;
    }

    static String getSqlElement(String key) {
        return sqlProperties.getProperty(key);
    }


}
