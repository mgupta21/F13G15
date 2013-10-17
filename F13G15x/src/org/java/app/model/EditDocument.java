package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.java.app.service.DocDownloadService;
import org.java.app.service.DocUploadService;

@ManagedBean(name = "fileDownloadBean")
@SessionScoped
public class EditDocument {
	
	
	private String[] tableName;
	public static String selectedTable;
	
	@ManagedProperty(value = "#{DocDownloadService}")
	private DocDownloadService docDownload; 
	private DocUploadService docUpload;
	

	public String[] getTablesList()
	{
		//Calls DocDownloadService
		return tableName;
	}
	
	public void GetFileRows(String selectedTable)
	{
		//Calls GetTableData
	}
	
	public void UpdateTableRow(String selectedTable, String rowId)
	{
		//Calls UpdateRowService
	}
	
}