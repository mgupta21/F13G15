package org.java.app.service;


import javax.faces.bean.ManagedBean;

import org.java.app.common.Constants.SERVER_RESPONSE;



@ManagedBean(name="dataAnalysis")


public class DataAnalysisService {
	

	public static String tableName = new String();
	
	@SuppressWarnings("unused")
	private String getTablesList(String tableName)
	{
		return toString(); // To fetch table list from tables
	}
	
	@SuppressWarnings("unused")
	private String getColumnList(String tableName)
	{
		return toString(); // To fetch column list from tables
	}

		
	public String result()
	{
		return SERVER_RESPONSE.SUCCESS;
	}

}