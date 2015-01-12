package edu.buffalo.ktmb.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	/****
	 * Variable declaration
	 */
	Connection connection = null;
	private static DBManager dbManager = new DBManager();

	private DBManager() {
	}

	/**************************************************
	 * This will return object of DBManager
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static DBManager getInstance() throws SQLException {
		return dbManager;
	}

	/***************************************************
	 * This method will create and return DB Connection
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Connection getConnection() throws FileNotFoundException, IOException {

		final String driverName = "com.mysql.jdbc.Driver";
		final String url = "jdbc:mysql://localhost:3306/testDB";
		final String userName = "root";
		final String password = "root";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			System.out.println("ERROR: failed to load JDBC driver.");
			e.printStackTrace();
		}

		return connection;
	}

	/****************************************************
	 * This method is used for releasing the resources
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	public void releaseResources(Connection connection, Statement stmt,
			ResultSet resultSet) throws SQLException {

		if (resultSet != null)
			resultSet.close();
		if (stmt != null)
			stmt.close();
		if (connection != null)
			connection.close();

	}

	/********************
	 * Overloaded version of release connection
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	public void releaseResources(Connection connection) throws SQLException {
		releaseResources(connection, null, null);
	}

	/*********************
	 * Overloaded version of releaseConnection
	 * 
	 * @param connection
	 * @param statement
	 * @throws SQLException
	 */
	public void releaseResources(Connection connection, Statement statement)
			throws SQLException {
		releaseResources(connection, statement, null);
	}
}