package meetingmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Database;

public class Location {
	
	private int id;
	private String name;
	private String city;
	
	private static final String sql = "SELECT name, city FROM location WHERE id = ?;";
	
	public Location(int id, Database db) throws SQLException {
		/* (id, name, building_number, city) */
		PreparedStatement statement = db.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			this.id = id;
			this.name = rs.getString("name");
			this.city = rs.getString("city");
		}
		rs.close();
		statement.close();
	}
	
	public Location(int id, String name, String city) {
		this.id = id;
		this.name = name;
		this.city = city;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return this.id + ": " + this.name + " (" + this.city + ")";
	}
}
