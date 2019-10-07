package org.itstep.dao.impl;

import org.itstep.dao.IData;
import org.itstep.dao.TestDao;
import org.itstep.dao.mapper.TestMapper;
import org.itstep.model.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCTestDao implements TestDao {
    private Connection connection;

    public JDBCTestDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Test> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Test> findAll() {
        List<Test> records = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(IData.getSqlElement(IData.ALL_TESTS));
            TestMapper testMapper = new TestMapper();
            while (rs.next()) {
                records.add(testMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return records;
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

    @Override
    public void saveRating(long testID, long userID, int totalPoints) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_RATING));
            preparedStatement.setLong(1, testID);
            preparedStatement.setLong(2, userID);
            preparedStatement.setInt(3, totalPoints);
            preparedStatement.setInt(4, totalPoints);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
