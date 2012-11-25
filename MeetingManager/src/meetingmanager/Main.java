package meetingmanager;

import java.sql.SQLException;

import util.Database;
import meetingmanager.Meeting;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Database db = new Database();
		try {
			Meeting m = new Meeting(1, db);
			System.out.println(m.getLocation());
			System.out.println(m.getCreatedBy());
			
			Invite i = new Invite(1, db);
			System.out.println(i.getEmployee());
			System.out.println(i.getMeeting());
			System.out.println(i.getResponse());

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
