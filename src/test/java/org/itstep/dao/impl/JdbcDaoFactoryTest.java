package org.itstep.dao.impl;

import org.itstep.dao.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JdbcDaoFactoryTest {

    private DaoFactory daoFactory;

    @Before
    public void setUp() throws Exception {
        daoFactory = DaoFactory.getInstance();
    }

    @Test
    public void testCreateUserAccountDao() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserAccountDao userDao = daoFactory.createUserDao(connection);
            assertNotNull(userDao);
        }
    }

    @Test
    public void testCreateTestRatingDao() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            TestRatingDao testRatingDao = daoFactory.createTestRatingDao(connection);
            assertNotNull(testRatingDao);
        }
    }

    @Test
    public void testCreateTestDao() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            TestDao testDao = daoFactory.createTestDao(connection);
            assertNotNull(testDao);
        }
    }

    @Test
    public void testCreateQuestionDao() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            QuestionDao questionDao = daoFactory.createQuestionDao(connection);
            assertNotNull(questionDao);
        }
    }



}