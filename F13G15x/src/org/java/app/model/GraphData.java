package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "graphData")
@SessionScoped
public class GraphData {

	String xAxis;
	String yAxis;
	
	public String getxAxis() {
		return xAxis;
	}
	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}
	public String getyAxis() {
		return yAxis;
	}
	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}

}
