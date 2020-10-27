package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
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
    private DataSource dataSource;
    private final SimpleJdbcTemplate template;
    private final JdbcTemplate jdbcTemplate;


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.template = new SimpleJdbcTemplate(dataSource);
    }
    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .password(row.getString("password"))
            .username(row.getString("username"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .build();


    @Override
    public List<User> findAllByAge(Integer age) {
        return template.selectQuery(SQL_SELECT_BY_AGE, userRowMapper, age);

    }

    @Override
    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return template.selectQuery(SQL_SELECT_BY_USERNAME, userRowMapper, username).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return template.selectQuery(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        return template.selectQuery(SQL_SELECT_BY_ID, userRowMapper, id).stream().findFirst();
    }

    @Override
    public void save(User entity) {
        template.updateQuery(SQL_INSERT_USER, entity.getUsername(), entity.getPassword(), entity.getFirstName(), entity.getLastName());
    }

    @Override
    public void update(User entity) {
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }
}
