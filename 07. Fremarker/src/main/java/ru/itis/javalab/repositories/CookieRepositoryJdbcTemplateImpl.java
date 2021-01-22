package ru.itis.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Cookie;

import java.util.List;
import java.util.Optional;

/**
 * created: 19-11-2020 - 0:52
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */

@Repository(value = "CookieRepositoryJdbcTemplateImpl")
public class CookieRepositoryJdbcTemplateImpl implements CookieRepository {

    // language=SQL
    private static final String SQL_SELECT_BY_USER_ID = "select * from cookie where user_id = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_SESSION_ID = "select * from cookie where session_id = ?";

    // language=SQL
    private static final String SQL_SELECT_ALL = "select * from  cookie";

    // language=SQL
    private static final String SQL_INSERT_COOKIE = "insert into cookie(user_id, session_id) values (?, ?)";

    // language=SQL
    private static final String SQL_UPDATE_COOKIE = "update cookie set user_id = ?, session_id = ? where user_id = ?";

    // language=SQL
    private static final String SQL_DELETE_COOKIE = "delete from cookie where user_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Cookie> rowMapper = (resultSet, i) -> Cookie.builder()
            .userId(resultSet.getLong("user_id"))
            .sessionId(resultSet.getString("session_id"))
            .build();

    @Override
    public Long save(Cookie entity) {
        jdbcTemplate.update(SQL_INSERT_COOKIE, entity.getUserId(), entity.getSessionId());
        return 0L;
    }

    @Override
    public List<Cookie> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public void update(Cookie entity) {
        jdbcTemplate.update(SQL_UPDATE_COOKIE, entity.getUserId(), entity.getSessionId(), entity.getUserId());
    }

    @Override
    public void delete(Cookie entity) {
        jdbcTemplate.update(SQL_DELETE_COOKIE, entity.getUserId());
    }

    @Override
    public Optional<Cookie> findBySessionId(String sessionId) {
        Cookie cookie;
        try {
            cookie = jdbcTemplate.queryForObject(SQL_SELECT_BY_SESSION_ID, rowMapper, sessionId);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(cookie);
    }

    @Override
    public Optional<Cookie> findByUserId(Long id) {
        Cookie cookie;
        try {
            cookie = jdbcTemplate.queryForObject(SQL_SELECT_BY_USER_ID, rowMapper, id);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(cookie);
    }
}
