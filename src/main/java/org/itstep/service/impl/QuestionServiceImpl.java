package org.itstep.service.impl;

import org.apache.log4j.Logger;
import org.itstep.dao.DaoConnection;
import org.itstep.dao.DaoFactory;
import org.itstep.dao.QuestionDao;
import org.itstep.dto.QuestionDTO;
import org.itstep.model.entity.Question;
import org.itstep.service.QuestionService;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {

    /* Logger */
    private static final Logger log = Logger.getLogger(QuestionServiceImpl.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    /** Lazy holder for service instance */
    private static class Holder {
        static final QuestionServiceImpl INSTANCE = new QuestionServiceImpl();
    }

    public static QuestionServiceImpl getInstance() {
        return QuestionServiceImpl.Holder.INSTANCE;
    }

    @Override
    public List<Question> getAllQuestionsByTestID(long testID) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            QuestionDao questionDao = daoFactory.createQuestionDao(connection);

            return questionDao.findAllByTestId(testID);
        }
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
