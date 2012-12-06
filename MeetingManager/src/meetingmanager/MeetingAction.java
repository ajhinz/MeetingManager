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
		
		Database db = new Database();
		
		Map<String, Object> session = getSession();
		Object usrObject = session.get("user");
		if (usrObject == null) {
			return "login";
		}
		this.user = new Employee((int) usrObject, db);
		
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
