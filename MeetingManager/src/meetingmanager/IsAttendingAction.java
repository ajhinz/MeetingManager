package meetingmanager;

import java.sql.SQLException;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class IsAttendingAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int inviteId;
	private boolean isAttending;
	private String message;
	
	public String execute() throws SQLException {
		Database db = new Database();
		Invite invite = new Invite(this.inviteId, db);
		boolean wasUpdated = invite.setResponse(this.isAttending);
		if (wasUpdated) {
			this.message = "Your response was saved.";
		}
		else {
			this.message = "There was a scheduling conflict.";
		}
		return SUCCESS;
	}
	
	public void setId(int inviteId) {
		this.inviteId = inviteId;
	}
	
	public void setResponse(String response) {
		this.isAttending = response.equals("yes");
	}
	
	public String getMessage() {
		return this.message;
	}
}
