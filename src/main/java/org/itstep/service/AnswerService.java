package org.itstep.service;

import org.itstep.dto.QuestionDTO;

import java.util.List;

public interface AnswerService {
    void setAnswerToQuestion(long questionID, long answerID, List<QuestionDTO> questionDTOList);
}
