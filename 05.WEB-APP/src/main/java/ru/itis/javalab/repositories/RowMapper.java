package ru.itis.javalab.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 12/10/2020 - 17:01
 * 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
