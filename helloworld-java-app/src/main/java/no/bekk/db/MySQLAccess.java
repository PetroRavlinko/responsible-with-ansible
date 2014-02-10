package no.bekk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String user = null;
	private String password = null;
	private String dbServer = null;
	private String tablename = null;
	private String schema = null;
	

	public MySQLAccess() {
		user = System.getProperty("db.user");
		schema = System.getProperty("db.schema");
		password = System.getProperty("db.password");
		dbServer = System.getProperty("db.server");
		tablename = System.getProperty("db.tablename");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://" + dbServer +"/" + schema + "?user="+ user + "&password=" + password;
			System.out.println("Connecting to database: " + dbUrl);
			connect = DriverManager.getConnection(dbUrl);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public String sayHello() throws Exception {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from " + tablename);
			String message = null;
			while (resultSet.next()) {
				message = resultSet.getString("MESSAGE");
			}
			return message + " :)";
		} catch (Exception e) {
			return "nothing :(";
		} finally {
			close();
		}

	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
