package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "tableColumns")
@RequestScoped
public class TableColumn {
	
	private String colName;
	private ColumnDataType colDataType;
	
	public ColumnDataType getColDataType() {
		return colDataType;
	}
	public void setColDataType(ColumnDataType colDataType) {
		this.colDataType = colDataType;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}

}
