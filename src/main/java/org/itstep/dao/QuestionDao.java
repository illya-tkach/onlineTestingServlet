package org.itstep.dao;

import org.itstep.model.entity.Question;
import java.util.List;

public interface QuestionDao {
    List<Question> findAllByTestId(long testID);
}
