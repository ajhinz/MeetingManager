package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
	private Connection connection;
	public Database() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC driver not found");
			return;
		}
		try {
			connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/meetingmanager",
				"meetingmanager", "meetingmanagerpass");
		} catch(SQLException e) {
			System.out.println("Could not connect to database!");
			return;
		}
	}

	public Boolean isConnected() {
		return connection != null;
	}
	
	public Boolean executeFile(String file) {
		try {
			Scanner scanner = new Scanner(new File(file));
			scanner.useDelimiter(";");
			while (scanner.hasNext()) {
				String query = scanner.next();
				Statement statement = connection.createStatement();
				statement.executeUpdate(query);
				statement.close();
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		} catch(SQLException e) {
			System.out.println("Error executing statement");
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection");
		}
	}
}
