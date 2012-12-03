package meetingmanager;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class SaveMinutesAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private int meetingId;
	private String minutes;
	private String originalMinutes;
	private Meeting meeting;
	private int overwrite;
	private Map<String, Object> session;
	private Employee user;
	
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
	
		this.meeting = new Meeting(this.meetingId, db);
		this.originalMinutes = this.meeting.getMinutes();
		if (this.overwrite == 1) {
			this.meeting.setMinutesOverwrite(this.minutes);
			return SUCCESS;
		}
		int num_rows = this.meeting.setMinutes(this.minutes);
		System.out.println("id: "+ this.meetingId+", minutes: " + this.minutes);
		System.out.println("num_rows: " + num_rows);
		if (num_rows == 0) {
			return "choose_minutes";
		}
		
		return SUCCESS;
	}
	
	public void setId(String id) {
		this.meetingId = Integer.parseInt(id);
	}
	
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	
	public Meeting getMeeting() {
		return this.meeting;
	}
	
	public String getOriginalMinutes() {
		return this.originalMinutes;
	}
	
	public void setOverwrite(String overwrite) {
		this.overwrite = Integer.parseInt(overwrite);
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

}
