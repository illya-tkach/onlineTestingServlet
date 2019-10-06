package org.itstep.dao.mapper;

import org.itstep.model.entity.Question;
import org.itstep.model.entity.QuestionType;
import org.itstep.model.entity.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements ObjectMapper<Question> {
    @Override
    public Question extractFromResultSet(ResultSet rs) throws SQLException {
        Question question = Question.builder()
                .id(rs.getLong("id"))
                .definition(rs.getString("definitions"))
                .questionType(QuestionType.valueOf(rs.getString("question_type")))
                .build();
        return question;
    }
}
