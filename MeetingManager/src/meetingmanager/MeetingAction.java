package meetingmanager;

import java.sql.SQLException;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class MeetingAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int id;
	private Meeting meeting;
	
	public String execute() {
		Database db = new Database();
		try {
			this.meeting = new Meeting(this.id, db);
		} catch (SQLException e) {
			this.addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Meeting getMeeting() {
		return this.meeting;
	}
}
