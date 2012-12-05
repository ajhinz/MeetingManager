package meetingmanager;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class AvailableLocationsAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String dateStr;
	private String startTimeStr;
	private String endTimeStr;
	private List<Location> availableLocations;
	
	public String execute() throws SQLException, ParseException {
		Database db = new Database();
		SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd HHmm");
		
		Date startTime = parser.parse(this.dateStr + " " + this.startTimeStr);
		Date endTime = parser.parse(this.dateStr + " " + this.endTimeStr);
		
		this.availableLocations = Location.getAvailableLocations(startTime, endTime, db);
		
		return SUCCESS;
	}
	
	public List<Location> getAvailableLocations() {
		return this.availableLocations;
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
	
}
