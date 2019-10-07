package org.itstep.service.impl;

import org.apache.log4j.Logger;
import org.itstep.dao.DaoConnection;
import org.itstep.dao.DaoFactory;
import org.itstep.dao.TestDao;
import org.itstep.dto.AnswerDTO;
import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.Test;
import org.itstep.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {

    /* Logger */
    private static final Logger log = Logger.getLogger(TestServiceImpl.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Lazy holder for service instance
     */
    private static class Holder {
        static final TestServiceImpl INSTANCE = new TestServiceImpl();
    }

    public static TestServiceImpl getInstance() {
        return TestServiceImpl.Holder.INSTANCE;
    }

    @Override
    public List<Test> getAllTests() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            TestDao testDao = daoFactory.createTestDao(connection);
            return testDao.findAll();
        }
    }

    @Override
    public int calculatePoints(List<QuestionDTO> questionDTOS) {
        int totalPoints = 0;

        for (QuestionDTO question : questionDTOS){
            boolean isUserCorrectAnswered = true;
            for (AnswerDTO answer :question.getAnswers()) {
                if(answer.isAnswered() != answer.getIsCorrect()){
                    isUserCorrectAnswered = false;
                    break;
                }
            }
            if (isUserCorrectAnswered)
                totalPoints += 1;
        }

        return totalPoints;
    }

    @Override
    public void saveRating(long testID, long userID, int totalPoints) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            TestDao testDao = daoFactory.createTestDao(connection);
            testDao.saveRating(testID, userID, totalPoints);
        }
    }

}
