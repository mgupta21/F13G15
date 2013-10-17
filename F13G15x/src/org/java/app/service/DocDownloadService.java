package org.java.app.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DocDownloadService extends HttpServlet {
	public static  String filename = new String();
	
	public void FetchData(String tableName, OutputStream dataStream)
	{
		filename = "http://partners.adobe.com/public/developer/en/xml/AdobeXMLFormsSamples.pdf";
	}
	
	
	@SuppressWarnings("unused")
	private String getTablesList(String tableType )
	{
		return toString();
	}
	
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) 
	{
	 response.setContentType("");
	 response.setHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
	}
	  

}
