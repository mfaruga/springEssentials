package org.mfaruga.MFSpringData;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MFUserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User(rs.getLong("ID"), rs.getString("LOGIN"));
	}
}
