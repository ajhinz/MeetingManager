package admin;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class DropTablesAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		if (dropTables()) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	private Boolean dropTables() {
		Database db = new Database();
		if (!db.isConnected()) {
			return false;
		}
		ServletContext servletContext = ServletActionContext.getServletContext();
		String file = servletContext.getRealPath("DropTables.sql");
		Boolean wasExecuted = db.executeFile(file);
		db.close();
		return wasExecuted;
	}

}
