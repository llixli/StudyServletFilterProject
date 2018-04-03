package org.project.study.connection;

import java.sql.Connection;

import javax.servlet.ServletRequest;

public class MyUtils {
	public static final String ATT_NAME = "MY_CONNECTION_ATTRIBUTE";
	
	public static void storeConnection(ServletRequest request, Connection connection) {
		request.setAttribute(ATT_NAME, connection);
	}
	
	public static Connection getStoredConnection(ServletRequest request) {
		Connection connection = (Connection) request.getAttribute(ATT_NAME);
		return connection;
	}
}
