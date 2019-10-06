package org.itstep.dao.impl;

import org.itstep.dao.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class JdbcDaoFactoryTest {

    private DaoFactory daoFactory;

    @Before
    public void setUp() throws Exception {
        daoFactory = DaoFactory.getInstance();
    }

    @Test
    public void testCreateUserAccountDao() {
        UserAccountDao userDao = daoFactory.createUserDao();
        assertNotNull(userDao);
    }


}