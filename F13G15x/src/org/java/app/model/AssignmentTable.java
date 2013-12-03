package org.java.app.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="assignmentTable")
@SessionScoped
public class AssignmentTable {
	
	private String assignmentName;
	private String sourceTableName;
	private String professorName;
	private String course;
	private List<TableColumn> tableColumns;
	
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public List<TableColumn> getTableColumns() {
		return tableColumns;
	}
	public void setTableColumns(List<TableColumn> tableColumns) {
		this.tableColumns = tableColumns;
	}
	public String getSourceTableName() {
		return sourceTableName;
	}
	public void setSourceTableName(String sourceTableName) {
		this.sourceTableName = sourceTableName;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}

}
