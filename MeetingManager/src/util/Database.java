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
	private String errMsg;
	public Database() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			this.errMsg = "JDBC driver not found.";
			return;
		}
		try {
			this.connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/meetingmanager",
				"meetingmanager", "meetingmanagerpass");
		} catch(SQLException e) {
			this.errMsg = "Could not connect to database!";
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
			this.errMsg = "File not found: " + file;
			return false;
		} catch(SQLException e) {
			this.errMsg = "DB Error: " + e.getMessage();
			return false;
		}
		
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			this.errMsg = "Could not close connection";
		}
	}
	
	public String getError() {
		return this.errMsg;
	}
}
