package ru.itis.javalab.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.User;

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

@Repository
public class UserRepositoryJdbcTemplateImpl implements CrudUserRepository {

    // language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * " +
            "from \"user\" " +
            "where id = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_EMAIL =
            "select * " +
            "from \"user\" " +
            "where email = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_LOGIN =
            "select * " +
            "from \"user\" " +
            "where login = ?";

    // language=SQL
    private static final String SQL_UPDATE_USER =
            "update \"user\" " +
            "set email = :email, " +
            "login = :login, " +
            "password = :password, " +
            "confirm_code =: confirm_code, " +
            "state =: state " +
            "where id =:id";

    // language=SQL
    private static final String SQL_SELECT_ALL =
            "select * " +
            "from \"user\"";

    // language=SQL
    private static final String SQL_DELETE_USER =
            "delete from \"user\" " +
            "where id = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_CONFIRM_CODE =
            "select * " +
            "from \"user\" " +
            "where confirm_code = ?";

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<User> rowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .login(resultSet.getString("login"))
            .password(resultSet.getString("password"))
            .state(User.State.valueOf(resultSet.getString("state")))
            .confirmCode(resultSet.getString("confirm_code"))
            .build();


    private final SimpleJdbcInsert simpleJdbcInsert;

    public UserRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

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
    public Optional<User> findByConfirmCode(String code) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_CONFIRM_CODE, rowMapper, code);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Long save(User entity) {
        simpleJdbcInsert.withTableName("\"user\"")
                .usingColumns("email", "login", "password", "state", "confirm_code")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("email", entity.getEmail());
        map.put("login", entity.getLogin());
        map.put("password", entity.getPassword());
        map.put("confirm_code", entity.getConfirmCode());
        map.put("state", entity.getState().toString());

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
        map.put("confirm_code", entity.getConfirmCode());
        map.put("state", entity.getState().toString());
        namedParameterJdbcTemplate.update(SQL_UPDATE_USER, map);
    }

    @Override
    public void delete(User entity) {
        jdbcTemplate.update(SQL_DELETE_USER, entity.getId());
    }
}
