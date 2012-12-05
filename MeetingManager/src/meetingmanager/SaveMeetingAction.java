package meetingmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class SaveMeetingAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private Employee user;
	private int[] invitedEmployeeIds;
	private Map<String, Object> session;
	private int location;
	private String dateStr;
	private String startTimeStr;
	private String endTimeStr;
	private Meeting meeting;
	
	public String execute() throws Exception {
		Database db = new Database();
		
		this.user = (Employee)session.get("user");
		if (this.user == null) {
			this.user = new Employee(1, db);
		}
		else {
			this.user.setDb(db);
		}

		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = parser.parse(this.dateStr);
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		
		Date startTime = parser.parse(this.startTimeStr);
		Calendar startTimeCal = Calendar.getInstance();
		startTimeCal.setTime(startTime);
		startTimeCal.set(dateCal.get(Calendar.YEAR),
						 dateCal.get(Calendar.MONTH),
						 dateCal.get(Calendar.DATE));
		startTime = startTimeCal.getTime();
		
		Date endTime = parser.parse(this.endTimeStr);
		Calendar endTimeCal = Calendar.getInstance();
		endTimeCal.setTime(endTime);
		endTimeCal.set(dateCal.get(Calendar.YEAR),
					   dateCal.get(Calendar.MONTH),
				       dateCal.get(Calendar.DATE));
		endTime = endTimeCal.getTime();
		
		
		this.meeting = new Meeting(this.location, startTime, endTime, this.user.getId(), this.invitedEmployeeIds, db);
		
		return SUCCESS;
	}
	
	public void setInvitedEmployees(String[] invitedEmployees) {
		this.invitedEmployeeIds = new int[invitedEmployees.length];
		for (int i=0; i < invitedEmployees.length; i++) {
			this.invitedEmployeeIds[i] = Integer.parseInt(invitedEmployees[i]);
			System.out.println(invitedEmployees[i]);
		}
	}
	
	public void setLocation(String location) {
		this.location = Integer.parseInt(location);
	}
	
	public void setDate(String dateStr) {
		this.dateStr = dateStr;
	}
	
	public void setStartTime(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	
	public void setEndTime(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	
	public Meeting getMeeting() {
		return this.meeting;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
