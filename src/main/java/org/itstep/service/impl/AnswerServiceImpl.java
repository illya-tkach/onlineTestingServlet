package org.itstep.service.impl;

import org.itstep.dto.AnswerDTO;
import org.itstep.dto.QuestionDTO;
import org.itstep.service.AnswerService;

import java.util.List;
import java.util.Optional;

public class AnswerServiceImpl implements AnswerService {
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
