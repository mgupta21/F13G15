package org.java.app.model;

public enum AnalysisType {
	
	max("Maximum"), min("Minimum"), std("Standard Deviation"), variance("Variance");

	private String type;
	
	AnalysisType (String type) {
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
}
