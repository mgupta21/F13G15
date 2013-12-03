package org.java.app.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.java.app.common.Constants;
import org.java.app.exceptions.CorruptFileException;
import org.java.app.exceptions.DuplicateTableException;
import org.java.app.exceptions.InsertionException;
import org.java.app.model.FileUpload;

@ManagedBean(name="fileUploadService")
@SessionScoped
public class FileUploadService {
	
	@ManagedProperty(value="#{fileUpload}")
	private FileUpload fileUpload;
	
	Result result;
	private List<String> columnNames;
	int numberColumns;
	int numberRows;
	
	public String redirectToPersist(){
		
		try {
			// Test if Columns are Valid
			fileUpload.getColumns();
		} catch (CorruptFileException e) {
			getFacesMessage(FacesMessage.SEVERITY_ERROR, "Error While uploading table. Either duplicate table exist in database for this user or more than one table column had same name");
			e.printStackTrace();
			return Constants.SERVER_RESPONSE.CORRUPT;
		}
		
		return "persistFile";
	}
	
	public String uploadTable(){
		
		try {
			fileUpload.uploadUserTable();

			
		} catch (DuplicateTableException e) {
			getFacesMessage(FacesMessage.SEVERITY_ERROR, "Error While uploading table. Either duplicate table exist in database for this user or more than one table column had same name");
			e.printStackTrace();
			return Constants.SERVER_RESPONSE.ERROR;
		} catch (InsertionException e) {
			getFacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Data in file. Please upload a CSV in valid format");
			e.printStackTrace();
			return Constants.SERVER_RESPONSE.ERROR;
		} catch (IOException e) {
			getFacesMessage(FacesMessage.SEVERITY_ERROR, "Error While uploading table. Either duplicate table exist in database for this user or more than one table column had same name");
			e.printStackTrace();
			return Constants.SERVER_RESPONSE.CORRUPT;
		} 
		
		getUploadedTable();
		
		return "UserTableUploaded";
	}
	
	public void getUploadedTable(){
		ResultSet rs = fileUpload.getFileUploadFactory().getDbService().getSourceTable(fileUpload.getFileUploadFactory().getDbTableName());
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			result = ResultSupport.toResult(rs);
			numberColumns = rsmd.getColumnCount();
			numberRows = result.getRowCount();
			String columnNameList [] = result.getColumnNames();
			columnNames = new ArrayList<String>();
			for(int i=0; i<numberColumns;i++){
				if((fileUpload.getTableType().equalsIgnoreCase(Constants.TABLE_TYPES.ROOSTER))){
					columnNames.add(columnNameList[i]);
				}else if(i!=0){
					columnNames.add(columnNameList[i]);
				}
			}
			if(!(fileUpload.getTableType().equalsIgnoreCase(Constants.TABLE_TYPES.ROOSTER))){
				numberColumns--;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void getFacesMessage(Severity severity, String message){
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage(severity, message, null));
	}
	
	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public int getNumberColumns() {
		return numberColumns;
	}

	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}

	public int getNumberRows() {
		return numberRows;
	}

	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}
	
	
}
