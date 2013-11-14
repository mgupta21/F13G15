package org.java.app.model;


import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;

import org.apache.myfaces.custom.fileupload.UploadedFile;

@ManagedBean(name = "fileUpload")
@SessionScoped
public class FileUpload {
	
	private UploadedFile uploadedFile;
	private String tableName;
	private String tableType;
	private TableColumn[] columns;
	private HtmlDataTable htmlDataTable;
	/*@ManagedProperty(value="userLogin")
	private UserLogin userLogin;*/
	
	public String createTable() {
		
		return "success";
	}
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public HtmlDataTable getHtmlDataTable() {
		return htmlDataTable;
	}
	public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
		this.htmlDataTable = htmlDataTable;
	}
	public TableColumn[] getColumns() {
		return columns;
	}
	public void setColumns(TableColumn[] columns) {
		this.columns = columns;
	}
	
}



