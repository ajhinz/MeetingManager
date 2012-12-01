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
	private boolean response;

	private static final String sql = "SELECT meeting_id, employee_id, response FROM invited WHERE id = ?";
	
	private static final String is_attending_sql = "update invited inv1 " + 
	"set response = true " +
	"where id = ? " +
	"and not exists (select 1 " +
	"from invited inv2, meeting m1, meeting m2 " +
	"where inv1.employee_id = inv2.employee_id " +
	"and inv2.response = true " +
	"and inv1.meeting_id = m1.id " +
	"and inv2.meeting_id = m2.id " +
	"and m1.end_time >= m2.start_time " +
	"and m1.start_time <= m2.end_time);";

	private static final String not_attending_sql = "update invited set response = false where id = ?";
	
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

	public Invite(ResultSet rs, Database db) throws SQLException {
		this.db = db;
		this.id = rs.getInt("id");
		this.meetingId = rs.getInt("meeting_id");
		this.employeeId = rs.getInt("employee_id");
		this.response = rs.getBoolean("response");
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
	
	public boolean setResponse(boolean isAttending) throws SQLException {
		if (isAttending) {
			PreparedStatement statement = this.db.prepareStatement(is_attending_sql);
			statement.setInt(1, this.id);
			int rows = statement.executeUpdate();
			return rows == 1;
		}
		else {
			PreparedStatement statement = this.db.prepareStatement(not_attending_sql);
			statement.setInt(1, this.id);
			statement.executeUpdate();
			return true;
		}
	}

}
