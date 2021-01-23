package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.javalab.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * created: 18-11-2020 - 22:54
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */

@Repository(value = "UserRepositoryJdbcTemplateImpl")
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    // language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from \"user\" where id = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from \"user\" where email = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_LOGIN = "select * from \"user\" where login = ?";

    // language=SQL
    private static final String SQL_INSERT_USER = "insert into \"user\"(email, login, password) values (?, ?, ?)";

    // language=SQL
    private static final String SQL_UPDATE_USER = "update \"user\" set email = :email, login = :login, password = :password where id = :id";

    // language=SQL
    private static final String SQL_SELECT_ALL = "select * from \"user\"";

    // language=SQL
    private static final String SQL_DELETE_USER = "delete from \"user\" where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<User> rowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .login(resultSet.getString("login"))
            .password(resultSet.getString("password"))
            .build();

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, rowMapper, email);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_LOGIN, rowMapper, login);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Long save(User entity) {
        simpleJdbcInsert.withTableName("\"user\"").usingColumns("email", "login", "password").usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("email", entity.getEmail());
        map.put("login", entity.getLogin());
        map.put("password", entity.getPassword());
        return (Long) simpleJdbcInsert.executeAndReturnKey(map);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public void update(User entity) {
        Map<String, String> map = new HashMap<>();
        map.put("email", entity.getEmail());
        map.put("login", entity.getLogin());
        map.put("password", entity.getPassword());
        map.put("id", String.valueOf(entity.getId()));
        namedParameterJdbcTemplate.update(SQL_UPDATE_USER, map);
    }

    @Override
    public void delete(User entity) {
        jdbcTemplate.update(SQL_DELETE_USER, entity.getId());
    }
}
