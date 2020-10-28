package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.javalab.models.UserCookie;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * created: 21-10-2020 - 20:49
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
public class CookiesRepositoryJdbcImpl implements CookiesRepository {

    // language=SQL
    private static final String SQL_SELECT_BY_AUTH = "select * from cookie where auth = ?";

    // language=SQL
    private static final String SQL_INSERT_USER_COOKIE = "insert into cookie(user_id, auth) values(?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from cookie where user_id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from cookie";

    //language=SQL
    private static final String SQL_UPDATE_ALL = "update cookie set auth = ? where user_id = ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from cookie where user_id = ?";

//    RowMapper<UserCookie> rowMapper = row -> UserCookie.builder()
//            .userId(row.getLong("user_id"))
//            .auth(row.getString("auth"))
//            .build();

    //    SimpleJdbcTemplate template;
    private RowMapper<UserCookie> userCookieRowMapper = (row, i) -> UserCookie.builder()
            .userId(row.getLong("user_id"))
            .auth(row.getString("auth"))
            .build();

    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public CookiesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("cookie").usingColumns("user_id", "auth");
    }

    @Override
    public Optional<UserCookie> findByAuth(String auth) {
//        return template.selectQuery(SQL_SELECT_USER_COOKIE_BY_AUTH, rowMapper, auth).stream().findAny();
        UserCookie userCookie;
        try {
            userCookie = jdbcTemplate.queryForObject(SQL_SELECT_BY_AUTH, userCookieRowMapper, auth);
        } catch (EmptyResultDataAccessException e) {
            userCookie = null;
        }
        return Optional.ofNullable(userCookie);
    }

    @Override
    public List<UserCookie> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userCookieRowMapper);
    }

    @Override
    public Optional<UserCookie> findById(Long id) {
//        return template.selectQuery(SQL_SELECT_BY_ID, rowMapper, id).stream().findAny();
        UserCookie userCookie;
        try {
            userCookie = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userCookieRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            userCookie = null;
        }
        return Optional.ofNullable(userCookie);
    }

    @Override
    public void save(UserCookie entity) {
//        template.updateQuery(SQL_INSERT_USER_COOKIE, entity.getUserId(), entity.getAuth());
        jdbcTemplate.update(SQL_INSERT_USER_COOKIE, entity.getUserId(), entity.getAuth());
    }

    @Override
    public void update(UserCookie entity) {
        jdbcTemplate.update(SQL_UPDATE_ALL,
                entity.getAuth(),
                entity.getUserId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void delete(UserCookie entity) {
        deleteById(entity.getUserId());
    }
}
