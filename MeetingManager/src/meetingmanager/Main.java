package meetingmanager;

import java.sql.SQLException;
import java.text.ParseException;

import util.Database;
import meetingmanager.Meeting;

public class Main {

	public static void main(String[] args) throws ParseException, SQLException {
		Database db = new Database();
		try {
			Meeting m = new Meeting(1, db);
			System.out.println(m.getLocation());
			System.out.println(m.getCreatedBy());
			
			Invite i = new Invite(1, db);
			System.out.println(i.getEmployee());
			System.out.println(i.getMeeting());
			System.out.println(i.getResponse());

			i.getEmployee().getInvites();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
