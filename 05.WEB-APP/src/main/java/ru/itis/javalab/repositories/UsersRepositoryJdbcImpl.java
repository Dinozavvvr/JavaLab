package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from \"user\" where age = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from \"user\"";

    //language=SQL
    private static final String SQL_SELECT_BY_USERNAME = "select * from \"user\" where username = ?";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into \"user\"(username, pasword, first_name, last_name) values(?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from \"user\" where id = ?";

    //language=SQL
    private static final String SQL_SELECT_FIRST_BY_FIRSTNAME_AND_LASTNAME = "select * from \"user\" where first_name = ? and last_name = ? order by id limit 1";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from \"user\" where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_ALL = "update \"user\" set username = ?, first_name = ?, last_name = ?, password = ? where id = ?";
//    private final SimpleJdbcTemplate template;

    private DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

//    private RowMapper<User> userRowMapper = row -> User.builder()
//            .id(row.getLong("id"))
//            .password(row.getString("password"))
//            .username(row.getString("username"))
//            .firstName(row.getString("first_name"))
//            .lastName(row.getString("last_name"))
//            .build();

    private RowMapper<User> jdbcUserRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .password(row.getString("password"))
            .username(row.getString("username"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .build();

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("\"user\"").usingColumns("username", "first_name", "last_name", "password").usingGeneratedKeyColumns("id");
//        this.template = new SimpleJdbcTemplate(dataSource);
    }


    @Override
    public List<User> findAllByAge(Integer age) {
//        return template.selectQuery(SQL_SELECT_BY_AGE, userRowMapper, age);
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, jdbcUserRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_FIRST_BY_FIRSTNAME_AND_LASTNAME, jdbcUserRowMapper, firstName, lastName);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
//        return template.selectQuery(SQL_SELECT_BY_USERNAME, userRowMapper, username).stream().findAny();
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME, jdbcUserRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
//        return template.selectQuery(SQL_SELECT_ALL, userRowMapper);
        return jdbcTemplate.query(SQL_SELECT_ALL, jdbcUserRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
//        return template.selectQuery(SQL_SELECT_BY_ID, userRowMapper, id).stream().findFirst();
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, jdbcUserRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void save(User entity) {
//        template.updateQuery(SQL_INSERT_USER, entity.getUsername(), entity.getPassword(), entity.getFirstName(), entity.getLastName());
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("username", entity.getUsername());
        parameters.put("first_name", entity.getFirstName());
        parameters.put("last_name", entity.getLastName());
        parameters.put("password", entity.getPassword());
        entity.setId((Long) simpleJdbcInsert.executeAndReturnKey(parameters));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE_ALL,
                entity.getUsername(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPassword(),
                entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void delete(User entity) {
        deleteById(entity.getId());
    }
}
