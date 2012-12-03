package meetingmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import meetingmanager.Location;
import meetingmanager.Employee;
import util.Database;

public class Meeting {
	
	private int id;
	private Location location;
	private Timestamp startTime;
	private Timestamp endTime;
	private int createdById;
	private Employee createdBy;
	
	private String minutes;
	private Database db;

	private static final String sql = "SELECT location.id loc_id, location.name loc_name, " +
	"location.city loc_city, start_time, end_time, created_by, minutes " +
	"FROM meeting JOIN location ON meeting.location = location.id " +
	"WHERE meeting.id = ?;";
	
	private static final String add_minutes_sql = "UPDATE meeting SET minutes = ? WHERE id = ? and minutes IS NULL";
	
	private static final String add_minutes_overwrite_sql = "UPDATE meeting SET minutes = ? WHERE id = ?";

	public Meeting(int id, Database db) throws SQLException {
		/* id, location, start_time, end_time, created_by */
		this.db = db;
		PreparedStatement statement = db.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			this.id = id;
			this.location = new Location(rs.getInt("loc_id"),
										 rs.getString("loc_name"),
										 rs.getString("loc_city"));
			this.startTime = rs.getTimestamp("start_time");
			this.endTime = rs.getTimestamp("end_time");
			this.createdById = rs.getInt("created_by");
			this.minutes = rs.getString("minutes");

		}
		rs.close();
		statement.close();
	}

	public Meeting(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.location = new Location(rs.getInt("loc_id"),
				 					 rs.getString("loc_name"),
				 					 rs.getString("loc_city"));
		this.startTime = rs.getTimestamp("start_time");
		this.endTime = rs.getTimestamp("end_time");
		this.createdById = rs.getInt("created_by");
		this.minutes = rs.getString("minutes");
	}
	
	
	public int getId() {
		return this.id;
	}
	
	public Location getLocation() throws SQLException {
		return this.location;
	}

	public Date getStartTime() {
		return new Date(this.startTime.getTime());
	}

	public Date getEndTime() {
		return new Date(this.endTime.getTime());
	}
	public Employee getCreatedBy() throws SQLException {
		if (this.createdBy == null) {
			this.createdBy = new Employee(this.createdById, db);

		}
		return createdBy;
	}

	public String getMinutes() {
		return minutes;
	}

	public int setMinutes(String minutes) throws SQLException {
		this.minutes = minutes;
		PreparedStatement statement = this.db.prepareStatement(add_minutes_sql);
		statement.setString(1, minutes);
		statement.setInt(2, this.id);
		
		return statement.executeUpdate();
	}
	
	public void setMinutesOverwrite(String minutes) throws SQLException {
		this.minutes = minutes;
		PreparedStatement statement = this.db.prepareStatement(add_minutes_overwrite_sql);
		statement.setString(1, minutes);
		statement.setInt(2, this.id);
		statement.executeUpdate();
	}
	
	public boolean isHasMinutes() {
		return this.minutes.length() > 0;
	}
}
