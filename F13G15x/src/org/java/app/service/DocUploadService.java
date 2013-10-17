package org.java.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

public class DocUploadService {
	
	public void uploadData(String userName, String tableName, InputStream dataStream)
	{
		
	}
	
	//return DB table name
	public String createDataBaseTable(String userName, String tableName)
	{
		return tableName;
	}
	
	@SuppressWarnings("unused")
	private String getColumnHeader()
	{
		return toString();
	}
}
