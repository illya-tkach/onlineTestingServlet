package org.itstep.service.impl;


import org.itstep.dao.DaoFactory;
import org.itstep.dao.TestDao;
import org.itstep.dto.AnswerDTO;
import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.Test;
import org.itstep.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {

    TestDao testDao = DaoFactory.getInstance().createTestDao();


    @Override
    public List<Test> getAllTests() {
        return testDao.findAll();
    }

    @Override
    public int calculatePoints(List<QuestionDTO> questionDTOS) {
        int totalPoints = 0;

        for (QuestionDTO question : questionDTOS){
            boolean isUserCorrectAnswered = true;
            for (AnswerDTO answer :question.getAnswers()) {
                if(answer.isAnswered() != answer.isCorrect()){
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
        testDao.saveRating(testID, userID, totalPoints);
    }

//    @Override
//    public Test getById(long id) {
//        return testRepository.getOne(id);
//    }
//
//    @Override
//    public int calculatePoints(List<QuestionDTO> questionDTOS) {
//        int totalPoints = 0;
//
//        for (QuestionDTO question : questionDTOS){
//            boolean isUserCorrectAnswered = true;
//            for (AnswerDTO answer :question.getAnswers()) {
//                if(answer.isAnswered() != answer.isCorrect()){
//                    isUserCorrectAnswered = false;
//                    break;
//                }
//            }
//            if (isUserCorrectAnswered)
//                totalPoints += 1;
//        }
//
//        return totalPoints;
//    }
//
//    @Override
//    public void save(Test test) {
//        testRepository.save(test);
//    }
}
