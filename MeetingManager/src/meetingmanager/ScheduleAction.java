package meetingmanager;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class ScheduleAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private Employee user;
	private List<Employee> employees;
	private Map<String, Object> session;

	
	public String execute() throws Exception {
		Database db = new Database();
		
		this.user = (Employee)session.get("user");
		if (this.user == null) {
			this.user = new Employee(1, db);
		}
		else {
			this.user.setDb(db);
		}
		
		this.employees = Employee.getAllEmployees(this.user.getId(), db);
		return SUCCESS;
	}
	
	public List<Employee> getAllEmployees() {
		return this.employees;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
