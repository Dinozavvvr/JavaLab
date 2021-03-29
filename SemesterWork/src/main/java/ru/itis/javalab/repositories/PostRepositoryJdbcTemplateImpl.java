package ru.itis.javalab.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Post;
import ru.itis.javalab.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * created: 19-11-2020 - 0:24
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
@Repository
public class PostRepositoryJdbcTemplateImpl implements CrudPostRepository {

    // language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * " +
            "from post " +
            "where id = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_USER_ID =
            "select * " +
            "from post " +
            "where user_id = ?";

    // language=SQL
    private static final String SQL_SELECT_ALL =
            "select * " +
            "from post";

    // language=SQL
    private static final String SQL_INSERT_POST =
            "insert into post(user_id, time, place, description, people, file_name) " +
            "values (?, ?, ?, ?, ?, ?)";

    // language=SQL
    private static final String SQL_UPDATE_POST =
            "update post " +
            "set place = :place, description = :description, people = :people " +
            "where id = :id";

    // language=SQL
    private static final String SQL_DELETE_POST =
            "delete from post " +
            "where id = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_FILENAME =
            "select * " +
            "from post " +
            "where file_name = ?";

    private final SimpleJdbcInsert simpleJdbcInsert;

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Post> rowMapper = (resultSet, i) -> Post.builder()
            .id(resultSet.getLong("id"))
            .user((User) resultSet.getObject("user_id"))
            .time(resultSet.getTimestamp("time"))
            .place(resultSet.getString("place"))
            .description(resultSet.getString("description"))
            .people(resultSet.getString("people"))
            .fileName(resultSet.getString("file_name"))
            .build();

    public PostRepositoryJdbcTemplateImpl(SimpleJdbcInsert simpleJdbcInsert,
                                          JdbcTemplate jdbcTemplate,
                                          NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Post> findAllByUserId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_USER_ID, rowMapper, id);
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post;
        try {
            post = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(post);
    }

    @Override
    public Optional<Post> findByFileName(String fileName) {
        Post post;
        try {
            post = jdbcTemplate.queryForObject(SQL_SELECT_BY_FILENAME, rowMapper, fileName);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(post);
    }

    @Override
    public Long save(Post entity) {

        simpleJdbcInsert.withTableName("post")
                .usingColumns(
                        "user_id",
                        "time",
                        "place",
                        "description",
                        "people",
                        "file_name")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", entity.getUser());
        map.put("time", entity.getTime());
        map.put("place", entity.getPlace());
        map.put("description", entity.getDescription());
        map.put("people", entity.getPeople());
        map.put("file_name", entity.getFileName());

        return (Long) simpleJdbcInsert.executeAndReturnKey(map);
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public void update(Post entity) {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(entity.getId()));
        map.put("place", entity.getPlace());
        map.put("description", entity.getDescription());
        map.put("people", entity.getPeople());

        namedParameterJdbcTemplate.update(SQL_UPDATE_POST, map);
    }

    @Override
    public void delete(Post entity) {
        jdbcTemplate.update(SQL_DELETE_POST, entity.getId());
    }
}
