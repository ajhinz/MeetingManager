package meetingmanager;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class SaveLoginAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private int employeeId;
	private Map<String, Object> session;
	private Employee user;
	
	public String execute() throws SQLException {
		Database db = new Database();
		
		this.user = new Employee(this.employeeId, db);
		this.session.put("user", this.employeeId);
		
		return SUCCESS;
	}
	
	public Employee getUser() {
		return this.user;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = Integer.parseInt(employeeId);
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
}
