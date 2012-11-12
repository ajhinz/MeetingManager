package admin;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class DropTablesAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		return dropTables();
	}
	
	private String dropTables() {
		Database db = new Database();
		if (!db.isConnected()) {
			this.addActionError(db.getError());
			return ERROR;
		}
		ServletContext servletContext = ServletActionContext.getServletContext();
		String file = servletContext.getRealPath("DropTables.sql");
		Boolean wasExecuted = db.executeFile(file);
		db.close();
		if (wasExecuted) {
			return SUCCESS;
		}
		else {
			this.addActionMessage(db.getError());
			return ERROR;
		}
	}
}
