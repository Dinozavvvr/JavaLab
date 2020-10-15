package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 12/10/2020 - 17:01
 * 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
public class SimpleJdbcTemplate {
    private DataSource dataSource;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            List<T> resultList = new ArrayList<>();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i+1, args[i]);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T entity = rowMapper.mapRow(resultSet);
                resultList.add(entity);
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
}
