package org.java.app.model;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="stats")
public class Statistics {

	private double minVal;
	private double maxVal;
	private double mean;
	private double variance;
	private double std;
	private double median;
	private double q1;
	private double q3;
	
	public Statistics(String string, double minValue) {
		// TODO Auto-generated constructor stub
	}
	public double getMinVal() {
		return minVal;
	}
	public void setMinVal(double minVal) {
		this.minVal = minVal;
	}
	public double getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(double maxVal) {
		this.maxVal = maxVal;
	}
	public double getMean() {
		return mean;
	}
	public void setMean(double mean) {
		this.mean = mean;
	}
	public double getVariance() {
		return variance;
	}
	public void setVariance(double variance) {
		this.variance = variance;
	}
	public double getStd() {
		return std;
	}
	public void setStd(double std) {
		this.std = std;
	}
	public double getMedian() {
		return median;
	}
	public void setMedian(double median) {
		this.median = median;
	}
	public double getQ1() {
		return q1;
	}
	public void setQ1(double q1) {
		this.q1 = q1;
	}
	public double getQ3() {
		return q3;
	}
	public void setQ3(double q3) {
		this.q3 = q3;
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
