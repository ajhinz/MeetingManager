package meetingmanager;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class MeetingAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private int id;
	private Meeting meeting;
	private Employee user;
	private Map<String, Object> session;
	
	public String execute() throws SQLException {
		Map<String, Object> session = getSession();
		Database db = new Database();
		this.user = (Employee)session.get("user");
		if (this.user == null) {
			this.user = new Employee(1, db);
		}
		else {
			this.user.setDb(db);
		}
		
		try {
			this.meeting = new Meeting(this.id, db);
		} catch (SQLException e) {
			this.addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public Meeting getMeeting() {
		return this.meeting;
	}
	
	public Employee getCurrentUser() {
		return this.user;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}
	
}
