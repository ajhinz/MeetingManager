package admin;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class SampleDataAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		return addSampleData();
	}
	
	private String addSampleData() {
		Database db = new Database();
		if (!db.isConnected()) {
			this.addActionError(db.getError());
			return ERROR;
		}
		ServletContext servletContext = ServletActionContext.getServletContext();
		String file = servletContext.getRealPath("SampleData.sql");
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
