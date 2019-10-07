package org.itstep.dao;

import org.itstep.model.entity.Question;
import org.itstep.model.entity.Test;

import java.util.List;

public interface QuestionDao extends GenericDao<Test> {
    List<Question> findAllByTestId(long testID);
}
