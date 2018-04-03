package org.project.study.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String hostName = "127.0.0.1";
		String dbName = "bearinmind";
		String userName = "root";
		String password = "army";
		
		return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String settingsParameters = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + settingsParameters;
		
		Connection connection = DriverManager.getConnection(connectionURL, userName, password);
		
		return connection;
	}
	
	public static void closeQuietly(Connection connection) {
		try {
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollbackQuietly(Connection connection) {
		try {
			connection.rollback();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
