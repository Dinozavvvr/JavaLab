package ru.itis.javalab.repositories;

import lombok.AllArgsConstructor;
import ru.itis.javalab.repositories.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created: 14-10-2020 - 22:14
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */

@AllArgsConstructor
public class SimpleJdbcTemplate {

    DataSource dataSource;

    public <T> List<T> selectQuery(String sql, RowMapper<T> rowMapper, Object... args) {

        List<T> resultList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T entity = rowMapper.mapRow(resultSet);
                resultList.add(entity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }


    public void updateQuery(String sql, Object... args) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) {
                    preparedStatement.setNull(i + 1, Types.OTHER);
                } else {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
