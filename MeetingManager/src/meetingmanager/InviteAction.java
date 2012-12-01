package meetingmanager;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;


public class InviteAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Employee user;
	private Map<String, Object> session;
	private List<Invite> invites;
	
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
			this.invites = this.user.getInvites();
		} catch (SQLException e) {
			this.addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	private Map<String, Object> getSession() {
		return this.session;
	}

	public List<Invite> getInvites() {
		return this.invites;
	}
}
