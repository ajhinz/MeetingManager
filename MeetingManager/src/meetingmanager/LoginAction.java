package meetingmanager;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import util.Database;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private List<Employee> allEmployees;
	private static final long serialVersionUID = 1L;

	public String execute() throws SQLException {
		Database db = new Database();
		this.allEmployees = Employee.getAllEmployees(-1, db);
		
		this.session.clear();
		return SUCCESS;
	}
	
	public List<Employee> getAllEmployees() {
		return this.allEmployees;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	
}
