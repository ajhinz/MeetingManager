package admin;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import util.Database;

public class CreateTablesAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		return createTables();
	}
	
	private String createTables() {
		Database db = new Database();
		if (!db.isConnected()) {
			this.addActionError(db.getError());
			return ERROR;
		}
		ServletContext servletContext = ServletActionContext.getServletContext();
		String file = servletContext.getRealPath("CreateTables.sql");
		Boolean wasExecuted = db.executeFile(file);
		db.close();
		if (wasExecuted) {
			return SUCCESS;
		}
		else {
			this.addActionError(db.getError());
			return ERROR;
		}
	}
}
