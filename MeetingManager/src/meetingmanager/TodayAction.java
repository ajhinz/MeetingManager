package meetingmanager;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class TodayAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Date day = new Date();
	private Map params;
	private Employee user;
	
	public String execute() throws Exception {
		Database db = new Database();
		Map<String, Object> session = getSession();
		this.user = (Employee)session.get("user");
		if (this.user == null) {
			this.user = new Employee(1, db);
		}
		
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	private Map<String, Object> getSession() {
		return this.session;
	}
	public void setDay(String day) {
		SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd");
		try {
			this.day = parser.parse(day);
		} catch (ParseException e) {
			// Defaults to today
		}
	}
	
	public Date getDay() {
		return this.day;
	}
	
	public Date getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.day);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	public Date getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.day);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	public List<Meeting> getSchedule() {
		try {
			return this.user.getSchedule(this.day);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new LinkedList<Meeting>();
	}
	
}
