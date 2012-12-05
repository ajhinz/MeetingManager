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
	private static final String schedule_sql = "SELECT location.id loc_id, " +
	"location.name loc_name, location.city loc_city, m.id, m.start_time, m.end_time, " +
	"m.created_by, m.minutes FROM invited i JOIN meeting m on i.meeting_id = m.id " +
	"JOIN location on m.location = location.id " +
	"WHERE i.employee_id = ? AND date(m.start_time) = ? AND i.response " +
	"ORDER BY m.start_time";
	
	private static final String invite_sql = "SELECT id, meeting_id, employee_id, response " +
	"FROM invited WHERE employee_id = ? AND response IS NULL";
	
	private static final String get_all_empoyees = "SELECT id, name, team FROM employee WHERE id != ?;";
	
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
	
	public Employee(int id, String name, String team, Database db) {
		this.db = db;
		this.id = id;
		this.name = name;
		this.team = team;
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

	public List<Invite> getInvites() throws SQLException {
		List<Invite> invites = new LinkedList<Invite>();

		PreparedStatement statement = db.prepareStatement(invite_sql);
		statement.setInt(1, this.id);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			invites.add(new Invite(rs, db));
		}
		rs.close();
		statement.close();
		
		return invites;
		
	}
	
	public void setDb(Database db) {
		this.db = db;
	}
	
	public static List<Employee> getAllEmployees(int ignore_id, Database db) throws SQLException {
		List<Employee> employees = new LinkedList<Employee>();
		
		PreparedStatement statement = db.prepareStatement(get_all_empoyees);
		statement.setInt(1, ignore_id);
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee(rs.getInt("id"),
											 rs.getString("name"),
											 rs.getString("team"),
											 db);
			employees.add(employee);
		}
		return employees;		
	}

}
