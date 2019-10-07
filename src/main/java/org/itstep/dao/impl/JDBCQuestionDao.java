package org.itstep.dao.impl;

import org.itstep.dao.QuestionDao;
import org.itstep.dao.mapper.AnswerMapper;
import org.itstep.dao.mapper.QuestionMapper;
import org.itstep.model.entity.Answer;
import org.itstep.model.entity.Question;
import org.itstep.model.entity.Test;
import org.itstep.view.SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCQuestionDao implements QuestionDao {

    private Connection connection;

    public JDBCQuestionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Question> findAllByTestId(long testId) {
        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.getSqlElement(SQL.QUESTIONS_BY_TEST_ID));
            preparedStatement.setLong(1, testId);
            ResultSet rs = preparedStatement.executeQuery();

            QuestionMapper questionMapper = new QuestionMapper();
            while (rs.next()) {
                Question question = questionMapper.extractFromResultSet(rs);
                question.setAnswers(getAnswers(question));
                questions.add(question);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return questions;
    }


    private List<Answer> getAnswers(Question question) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL.getSqlElement(SQL.ANSWER_BY_QUESTION_ID));
        preparedStatement.setLong(1, question.getId());
        ResultSet answerRs = preparedStatement.executeQuery();
        AnswerMapper answerMapper = new AnswerMapper();

        while (answerRs.next()) {
            answers.add(answerMapper.extractFromResultSet(answerRs));
        }

        return answers;
    }

    @Override
    public Optional<Test> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Test> findAll() {
        return null;
    }

    @Override
    public Test create(Test test) {
        return null;
    }

    @Override
    public void update(Test test) {

    }

    @Override
    public void delete(Long id) {

    }
}
