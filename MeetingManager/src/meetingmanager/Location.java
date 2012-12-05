package meetingmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import util.Database;

public class Location {
	
	private int id;
	private String name;
	private String city;
	
	private static final String sql = "SELECT name, city FROM location WHERE id = ?;";
	
	private static final String available_sql = "select loc.id, loc.name, loc.city " +
			"from location loc " +
			"where loc.id not in (select location " +
			"from meeting " +
			"where ? >= start_time " +
			"and ? <= end_time);";
	
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
	
	public static List<Location> getAvailableLocations(Date startTime, Date endTime, Database db) throws SQLException {
		PreparedStatement statement = db.prepareStatement(available_sql);
		statement.setTimestamp(1, new java.sql.Timestamp(endTime.getTime()));
		statement.setTimestamp(2, new java.sql.Timestamp(startTime.getTime()));
		ResultSet rs = statement.executeQuery();
		
		List<Location> availableLocations = new LinkedList<Location>();
		while (rs.next()) {
			Location loc = new Location(rs.getInt("id"),
										rs.getString("name"),
										rs.getString("city"));
			availableLocations.add(loc);
		}
		return availableLocations;
	}
}
