package meetingmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Database;

public class Employee {
	private Database db;
	private int id;
	private String name;
	private String team;
	private static final String sql = "SELECT name, team FROM employee WHERE id = ?";
	
	public Employee(int id, Database db) throws SQLException {
		this.db = db;
		PreparedStatement statement = db.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			this.id = id;
			this.name = rs.getString("name");
			this.team = rs.getString("team");
		}
		rs.close();
		statement.close();

	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getTeam() {
		return this.team;
	}
	
	public String toString() {
		return this.id + ": " + this.name + " (" + this.team + ")";
	}
}
