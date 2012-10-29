package admin;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import util.Database;

import com.opensymphony.xwork2.ActionSupport;

public class SampleDataAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		if (addSampleData()) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	private Boolean addSampleData() {
		Database db = new Database();
		if (!db.isConnected()) {
			return false;
		}
		ServletContext servletContext = ServletActionContext.getServletContext();
		String file = servletContext.getRealPath("SampleData.sql");
		Boolean wasExecuted = db.executeFile(file);
		db.close();
		return wasExecuted;
	}
}
