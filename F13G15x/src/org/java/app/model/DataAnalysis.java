package org.java.app.model;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="analysis")
@SessionScoped

public class DataAnalysis {
	 
	public String table;
	public String column;
	public String type;

 
	public String gettable() {
		return table;
	}
 
	public void settable(String table) {
		this.table = table;
	}
		
	public String getcolumn() {
		return column;
	}
 
	public void setcolumn(String column) {
		this.column = column;
	}
	
	public String gettype() {
		return type;
	}
 
	public void settype(String type) {
		this.type = type;
	}
	
		
	}
 