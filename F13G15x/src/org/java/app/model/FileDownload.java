package org.java.app.model;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.java.app.service.DocDownloadService;


@ManagedBean(name = "fileDownloadBean")
@SessionScoped
public class FileDownload {
	
	
	private String[] tableName;
	public static String selectedTable;
	
	@ManagedProperty(value = "#{DocDownloadService}")
	private DocDownloadService docUpload; 
	

	public String[] getTablesList()
	{
		//Calls DocDownloadService
		return tableName;
	}
	
	public void DownloadFile(String selectedTable)
	{
		//Calls DocDownloadService
	}
	
	
}
