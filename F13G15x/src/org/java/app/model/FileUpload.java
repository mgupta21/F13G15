package org.java.app.model;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;

import org.java.app.service.DocUploadService;
/*import org.apache.myfaces.custom.fileupload.UploadedFile;*/


@ManagedBean(name = "fileUploadBean")
@SessionScoped
public class FileUpload {
	
	/*private UploadedFile uploadedFile;*/
	private String tableName;
	private HtmlDataTable htmlDataTable;
	
	
	
	@ManagedProperty(value = "#{docUploaderService}")
	private DocUploadService docUpload; 
	
	//public String createTable()
	//{
		//Code here
	//}
	
	/*public void setUploadedFile(UploadedFile uploadedFile)
	{
		this.uploadedFile = uploadedFile;
	}
	
	public UploadedFile getUploadedFile()
	{
		return uploadedFile;
	}*/
	
	public String getTableName()
	{
		return tableName;
	}
	
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public HtmlDataTable getHtmlDataTable() {
		return htmlDataTable;
	}

	public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
		this.htmlDataTable = htmlDataTable;
	}
	
	public enum TypeOfUpload
	{
		Quiz,
		Roster,
		Assignment;
	}
	
}



