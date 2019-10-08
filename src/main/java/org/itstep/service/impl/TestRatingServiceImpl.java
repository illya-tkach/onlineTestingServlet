package org.itstep.service.impl;

import org.apache.log4j.Logger;
import org.itstep.dao.DaoConnection;
import org.itstep.dao.DaoFactory;
import org.itstep.dao.TestRatingDao;
import org.itstep.model.entity.TestRating;
import org.itstep.service.TestRatingService;

import java.util.List;

public class TestRatingServiceImpl implements TestRatingService {

    /* Logger */
    private static final Logger log = Logger.getLogger(TestRatingServiceImpl.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Lazy holder for service instance
     */
    private static class Holder {

        static final TestRatingService INSTANCE = new TestRatingServiceImpl();
    }

    public static TestRatingService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<TestRating> getAllRatings() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            TestRatingDao ratingDao = daoFactory.createTestRatingDao(connection);

            return ratingDao.getAllRatings();
        }
    }
}
