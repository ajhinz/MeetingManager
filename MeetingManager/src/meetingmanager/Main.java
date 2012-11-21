package meetingmanager;

import java.sql.SQLException;

import util.Database;
import meetingmanager.Meeting;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database db = new Database();
		try {
			Meeting m = new Meeting(1, db);
			System.out.println(m.getLocation());
			System.out.println(m.getCreatedBy());
			
			Invitation i = new Invitation(1, db);
			System.out.println(i.getEmployee());
			System.out.println(i.getMeeting());
			System.out.println(i.getResponse());

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
