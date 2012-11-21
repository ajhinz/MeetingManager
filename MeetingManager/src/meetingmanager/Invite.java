package meetingmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Database;

public class Invite {

	private Database db;
	private int id;
	private int meetingId;
	private int employeeId;
	private Meeting meeting;
	private Employee employee;
	private Boolean response;

	private static final String sql = "SELECT meeting_id, employee_id, response FROM invited WHERE id = ?";
	
	public Invite(int id, Database db) throws SQLException {
		this.db = db;
		PreparedStatement statement = db.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			this.id = id;
			this.meetingId = rs.getInt("meeting_id");
			this.employeeId = rs.getInt("employee_id");
			this.response = rs.getBoolean("response");
		}
		rs.close();
		statement.close();

	}
	
	public int getId() {
		return this.id;
	}
	
	public Meeting getMeeting() throws SQLException {
		if (this.meeting == null) {
			this.meeting = new Meeting(this.meetingId, db);
		}
		return meeting;
	}

	public Employee getEmployee() throws SQLException {
		if (this.employee == null) {
			this.employee = new Employee(this.employeeId, db);
		}
		return employee;
	}

	public Boolean getResponse() {
		return response;
	}

}
