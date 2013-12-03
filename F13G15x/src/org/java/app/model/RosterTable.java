package org.java.app.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="rosterTable")
@SessionScoped
public class RosterTable {
	
	private String professorName;
	private String course;
	private List<TableColumn> tableColumns;
	
	public String getProfessorName() {
		return professorName;
	}
	public String getCourse() {
		return course;
	}
	public List<TableColumn> getTableColumns() {
		return tableColumns;
	}
	public void setTableColumns(List<TableColumn> tableColumns) {
		this.tableColumns = tableColumns;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
}
