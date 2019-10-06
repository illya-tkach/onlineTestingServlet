package org.itstep.service.impl;

import org.itstep.dao.DaoFactory;
import org.itstep.dao.QuestionDao;
import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.Question;
import org.itstep.service.QuestionService;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {


    QuestionDao questionDao = DaoFactory.getInstance().createQuestionDao();

    @Override
    public List<QuestionDTO> toDtoQuestions() {
        return null;
    }

    @Override
    public List<Question> getAllQuestionsByTestID(long testID) {
        return questionDao.findAllByTestId(testID);
    }

    @Override
    public QuestionDTO getNextQuestion(long questionID, List<QuestionDTO> questionDTOS) {

        Optional<QuestionDTO> questionDTO = questionDTOS.stream()
                .filter(question -> question.getId() == questionID)
                .findAny();

        if(questionDTO.isPresent()) return questionDTO.get();
        else throw new java.lang.RuntimeException();
    }
}
