package org.itstep.dao.impl;

import org.itstep.dao.TestRatingDao;
import org.itstep.dao.mapper.TestRatingMapper;
import org.itstep.model.entity.TestRating;
import org.itstep.view.SQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCTestRatingDao implements TestRatingDao {

    private Connection connection;

    public JDBCTestRatingDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<TestRating> getAllRatings() {
        List<TestRating> ratings = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL.getSqlElement(SQL.ALL_RATINGS));
            TestRatingMapper mapper= new TestRatingMapper();
            while (rs.next()) {
                ratings.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ratings;
    }
}
