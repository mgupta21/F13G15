package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;

import org.java.app.service.AssignmentService;

@ManagedBean(name="tableSelected")
@SessionScoped
public class UserTableSelected {
	
	private HtmlDataTable dataTable;
	private UserTable assignmentTable;
	
	@ManagedProperty(value="#{assignmentService}")
	private AssignmentService assignmentService;
	
	String selectedTable;

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	public String getSelectedTable() {
		return selectedTable;
	}

	public void setSelectedTable(String selectedTable) {
		this.selectedTable = selectedTable;
	}

	
	

}
