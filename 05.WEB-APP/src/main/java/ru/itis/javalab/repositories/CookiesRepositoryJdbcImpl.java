package ru.itis.javalab.repositories;

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
    private static final String SQL_SELECT_USER_COOKIE_BY_AUTH = "select * from cookie where auth = ?";

    // language=SQL
    private static final String SQL_INSERT_USER_COOKIE = "insert into cookie(user_id, auth) values(?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from cookie where user_id = ?";

    RowMapper<UserCookie> rowMapper = row -> UserCookie.builder()
            .userId(row.getLong("user_id"))
            .auth(row.getString("auth"))
            .build();

    DataSource dataSource;
    SimpleJdbcTemplate template;

    public CookiesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public Optional<UserCookie> findByAuth(String auth) {
        return template.selectQuery(SQL_SELECT_USER_COOKIE_BY_AUTH, rowMapper, auth).stream().findAny();
    }

    @Override
    public List<UserCookie> findAll() {
        return null;
    }

    @Override
    public Optional<UserCookie> findById(Long id) {
        return template.selectQuery(SQL_SELECT_BY_ID, rowMapper, id).stream().findAny();
    }

    @Override
    public void save(UserCookie entity) {
        template.updateQuery(SQL_INSERT_USER_COOKIE, entity.getUserId(), entity.getAuth());
    }

    @Override
    public void update(UserCookie entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(UserCookie entity) {

    }
}
