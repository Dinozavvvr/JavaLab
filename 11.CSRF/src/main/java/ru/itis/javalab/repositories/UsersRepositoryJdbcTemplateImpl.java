package ru.itis.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * created: 23-01-2021 - 20:51
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */

@Repository(value = "UsersRepositoryJdbcTemplateImpl")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    // language=SQL
    private static final String SQL_SELECT_ALL =
            "select * " +
            "from \"user\"";

    // language=SQL
    private static final String SQL_SELECT_ALL_BY_ID =
            "select * " +
            "from \"user\" " +
            "where id = ?";

    // language=SQL
    private static final String SQL_ALL_SELECT_BY_USERNAME =
            "select * " +
            "from \"user\" " +
            "where username = ?";

    // language=SQL
    private static final String SQL_UPDATE_USER =
            "update \"user\" " +
            "set username = ?, first_name = ?, last_name = ?," +
                    "password = ?, is_deleted = ? where id = ?";


    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert jdbcInsert;

    private final RowMapper<User> rowMapper = ((resultSet, i) ->
            User.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .password(resultSet.getString("password"))
                    .isDeleted(resultSet.getBoolean("is_deleted"))
                    .build());

    public UsersRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate,
                                           SimpleJdbcInsert jdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = jdbcInsert;
    }


    @Override
    public Long save(User entity) {
        jdbcInsert.withTableName("\"user\"")
                .usingColumns("username", "first_name",
                        "last_name", "password")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> map = new HashMap<>();

        map.put("username", entity.getUsername());
        map.put("first_name", entity.getFirstName());
        map.put("last_name", entity.getLastName());
        map.put("password", entity.getPassword());

        return (Long) jdbcInsert.executeAndReturnKey(map);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_ALL_BY_ID,
                    rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getIsDeleted(),
                user.getId());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_ALL_SELECT_BY_USERNAME,
                    rowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }
}
