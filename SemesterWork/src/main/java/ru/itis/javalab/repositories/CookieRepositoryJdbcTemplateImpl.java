package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.User;
import ru.itis.javalab.models.UserCookie;

import java.util.List;
import java.util.Optional;

/**
 * created: 21-02-2021 - 15:24
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Repository
public class CookieRepositoryJdbcTemplateImpl implements CrudCookieRepository {

    // language=SQL
    private static final String SQL_INSERT_USER_COOKIE =
            "insert into cookie(user_id, session_id) " +
            "values (?, ?)";

    // language=SQL
    private static final String SQL_SELECT_BY_SESSION_ID =
            "select *" +
            "from cookie " +
            "where session_id = ?";

    private final RowMapper<UserCookie> mapper = (resultSet, i) -> UserCookie
            .builder()
            .user((User) resultSet.getObject("user_id"))
            .sessionId(resultSet.getString("session_id"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public CookieRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserCookie> getBySessionId(String sessionId) {
        UserCookie userCookie;
        try {
            userCookie = jdbcTemplate.queryForObject(SQL_SELECT_BY_SESSION_ID, mapper, sessionId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(userCookie);
    }

    @Override
    public Long save(UserCookie entity) {
        jdbcTemplate.update(SQL_INSERT_USER_COOKIE, entity.getUser(), entity.getSessionId());
        return entity.getUser().getId();
    }

    @Override
    public List<UserCookie> findAll() {
        return null;
    }

    @Override
    public void update(UserCookie entity) {

    }

    @Override
    public void delete(UserCookie entity) {

    }
}
