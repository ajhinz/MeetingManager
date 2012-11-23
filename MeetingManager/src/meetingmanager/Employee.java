package meetingmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import util.Database;

public class Employee {
	private Database db;
	private int id;
	private String name;
	private String team;

	private static final String sql = "SELECT name, team FROM employee WHERE id = ?";
	private static final String schedule_sql = "SELECT m.id, m.location, m.start_time, m.end_time, m.created_by, m.minutes FROM invited i JOIN meeting m on i.meeting_id = m.id WHERE i.employee_id = ? AND date(m.start_time) = ? AND i.response ORDER BY m.start_time";
	
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

	public List<Meeting> getSchedule(Date day) throws SQLException {
		List<Meeting> schedule = new LinkedList<Meeting>();

		PreparedStatement statement = db.prepareStatement(schedule_sql);
		statement.setInt(1, this.id);
		statement.setDate(2, new java.sql.Date(day.getTime()));
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			schedule.add(new Meeting(rs));
		}
		rs.close();
		statement.close();
		
		return schedule;
	}
	

}
