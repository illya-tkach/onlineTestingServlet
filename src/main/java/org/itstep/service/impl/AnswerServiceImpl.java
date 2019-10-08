package org.itstep.service.impl;

import org.apache.log4j.Logger;
import org.itstep.dto.AnswerDTO;
import org.itstep.dto.QuestionDTO;
import org.itstep.service.AnswerService;
import org.itstep.service.QuestionService;

import java.util.List;
import java.util.Optional;

public class AnswerServiceImpl implements AnswerService {

    /* Logger */
    private static final Logger log = Logger.getLogger(AnswerServiceImpl.class);

    /** Lazy holder for service instance */
    private static class Holder {
        static final AnswerService INSTANCE = new AnswerServiceImpl();
    }

    public static AnswerService getInstance() {
        return AnswerServiceImpl.Holder.INSTANCE;
    }

    @Override
    public void setAnswerToQuestion(long questionID, long answerID, List<QuestionDTO> questionDTOList) {
        Optional<List<AnswerDTO>> answerDTOSet = questionDTOList.stream()
                .filter(question -> question.getId() == questionID)
                .map(question -> question.getAnswers())
                .findAny();

        if(answerDTOSet.isPresent())
        {
            answerDTOSet.get().stream()
                    .filter(answer -> answer.getId() == answerID)
                    .forEach(answer -> answer.setAnswered(true));
        }
    }
}
