package org.mfaruga.MFSpringData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.SQLExceptionTranslator;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/application-context.xml" });
		DataSource ds = (DataSource) context.getBean("MFPostgreSQLDS");

		JdbcTemplate template = (JdbcTemplate) context.getBean("MFPostgreSQLJDBCTemplate");
		registerSQLExceptionTranslator(template);
		querySpringWay(template);
		
		queryForCollectionOfUsersSpringWay(template);
		queryForSingleUserSpringWay(template);

		Long generatedID = createUserAndRetrieveAutoGeneratedID_vanilla(template, "faro");
		System.out.println("generated id: " + generatedID);
		Long secondGenerated  = createUserAndRetrieveAutoGeneratedID_withJDBCInsert(template, "faro"); 
		System.out.println("second generated id: " + secondGenerated);
		
		System.out.println("After data source!");
		((ConfigurableApplicationContext) context).close();
	}

	private static void querySpringWay(JdbcTemplate template) {

		List<Map<String, Object>> queryForList = template.queryForList("SELECT * FROM MFPERSON");
		for (Map<String, Object> row : queryForList) {
			System.out.println("Login: " + row.get("login"));
		}
	}

	private static void queryForSingleUserSpringWay(JdbcTemplate template) {

		User user = template.queryForObject("SELECT ID, LOGIN FROM MFPERSON WHERE LOGIN = ?", new Object[] { "BUCK" },
				new MFUserRowMapper());
		System.out.println(user);
	}

	private static void queryForCollectionOfUsersSpringWay(JdbcTemplate template) {
		List<User> users = template.query("SELECT ID, LOGIN FROM MFPERSON WHERE LOGIN = ? ORDER BY ID",
				new Object[] { "faro" }, new MFUserRowMapper());
		for (User user : users) {
			System.out.println(user);
		}
	}

	private static Long createUserAndRetrieveAutoGeneratedID_vanilla(JdbcTemplate template, final String userName) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("INSERT INTO MFPERSON(login) VALUES (?)",
						new String[] { "id" });
				ps.setString(1, userName);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	private static Long createUserAndRetrieveAutoGeneratedID_withJDBCInsert(JdbcTemplate template, String userName) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(template).withTableName("MFPERSON").usingGeneratedKeyColumns("id");
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("login", userName);
		Number returnKey = insert.executeAndReturnKey(params);
		return returnKey.longValue();
	}
	
	private static void registerSQLExceptionTranslator(JdbcTemplate template) {

		SQLExceptionTranslator translator = new SQLExceptionTranslator() {

			public DataAccessException translate(String task, String sql, SQLException ex) {
				System.out.println("Translating exception!");
				return new BadSqlGrammarException("Invalid query", sql, ex);
			}
		};

		template.setExceptionTranslator(translator);

	}

	private static void queryJDBCWay(DataSource ds) {
		Statement statement = null;
		try {
			Connection connection = ds.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM MFPERSON");
			while (rs.next()) {
				String login = rs.getString("login");
				System.out.println(login);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
