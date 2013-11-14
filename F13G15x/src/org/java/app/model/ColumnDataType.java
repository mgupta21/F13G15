package org.java.app.model;

public enum ColumnDataType {
	
	String("String", "VARCHAR(55)"), Double("Double", "DECIMAL(10,2)"), Integer(
			"Integer", "Int"), Date( "Date", "Date"), Undefined("Undefined", "null");
	
	private String dataName;
	private String dBDataType;
	

	private ColumnDataType(String dataName, String dBDataType) {
		this.dataName = dataName;
		this.dBDataType = dBDataType;
	}

	public String getDBDataType() {
		return dBDataType;
	}

	public String getDataName() {
		return dataName;
	}

}
