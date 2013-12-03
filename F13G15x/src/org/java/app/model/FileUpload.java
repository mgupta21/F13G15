package org.java.app.model;


import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.java.app.exceptions.CorruptFileException;
import org.java.app.exceptions.DuplicateTableException;
import org.java.app.exceptions.InsertionException;
import org.java.app.service.FileUploadFactory;

@ManagedBean(name = "fileUpload")
@SessionScoped
public class FileUpload {
	
	private UploadedFile uploadedFile;
	private String tableName;
	private String tableType;
	private TableColumn[] columns;
	
	@ManagedProperty(value="#{fileUploadFactory}")
	private FileUploadFactory fileUploadFactory;
	
	public void uploadUserTable() throws DuplicateTableException, IOException, InsertionException{
			
		fileUploadFactory.dataBaseUpload(tableName, tableType, columns, uploadedFile.getInputStream());
		
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
	public TableColumn[] getColumns() throws CorruptFileException {
		
		try {
			columns = CSVUploader.getColumns(uploadedFile.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return columns;
	}
	
	public void setColumns(TableColumn[] columns) {
		this.columns = columns;
	}
	
	public FileUploadFactory getFileUploadFactory() {
		return fileUploadFactory;
	}

	public void setFileUploadFactory(FileUploadFactory fileUploadFactory) {
		this.fileUploadFactory = fileUploadFactory;
	}
	
}



