package ru.itis.javalab.services;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * created: 11-11-2020 - 20:37
 * project: 06.EntityManager
 *
 * @author dinar
 * @version v0.1
 */

public class EntityManager {

    private DataSource dataSource;
    private JdbcTemplate template;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }

    public <T> void createTable(String tableName, Class<T> entityClass) {

        Field[] fields = entityClass.getDeclaredFields();
        StringBuilder sql = new StringBuilder();

        sql.append("create table ")
                .append(tableName)
                .append("(\n");

        for (Field field : fields) {
            sql.append(field.getName())
                    .append(" ");

            String type = field.getType().getSimpleName().toLowerCase();
            switch (type) {
                case "int":
                case "integer": {
                    type = "integer";
                    break;
                }
                case "long": {
                    type = "bigint";
                    break;
                }
                case "float": {
                    type = "real";
                    break;
                }
                case "double": {
                    type = "double precision";
                    break;
                }
                case "string": {
                    type = "varchar(255)";
                    break;
                }
            }
            sql.append(type)
                    .append(",\n");
        }

        sql.delete(sql.length() - 2, sql.length());
        sql.append("\n)");
        System.out.println(sql);
        template.update(String.valueOf(sql));
    }

    public void save(String tableName, Object entity) {
        Class<?> classOfEntity = entity.getClass();

        Field[] fields = classOfEntity.getDeclaredFields();

        StringBuilder sql = new StringBuilder();
        sql.append("insert into ")
                .append(tableName)
                .append("(");

        for (Field field : fields) {
            sql.append(field.getName())
                    .append(", ");
        }

        sql.delete(sql.length() - 2, sql.length())
                .append(")")
                .append(" values ")
                .append("(");

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (field.getType() == String.class) {
                    sql.append("'")
                            .append(value)
                            .append("'");
                } else {
                    sql.append(value);
                }
                sql.append(", ");
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }

        sql.delete(sql.length() - 2, sql.length()).append(");");

        System.out.println(sql);
        template.update(String.valueOf(sql));
    }

    public <T, ID extends Number> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        String sql = String.format("select * from %s where id = %s", tableName, idValue);

        Field[] f = resultType.getDeclaredFields();
        Class<?>[] types = new Class<?>[f.length];

        for (int i = 0; i < f.length; i++) {
            types[i] = f[i].getType();
        }

        Object[] variables = new Object[f.length];

        try {
            try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                Field[] fields = resultType.getDeclaredFields();
                if (resultSet.next()) {
                    for (int i = 0; i < fields.length; i++) {
                        variables[i] = resultSet.getObject(i + 1);
                    }
                }
                System.out.println(Arrays.toString(variables));
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        try {
            Constructor<?> constructor = resultType.getConstructor(types);
            Object object = constructor.newInstance(variables);
            return (T) object;
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
