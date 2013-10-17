package org.java.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.stat.StatUtils;
import org.java.app.model.Statistics;

public class StatsCal {
		 
		 private String variableName; 
		 boolean render; 
		 
		 private List <Statistics> statsList = null; 
		 
		 public StatsCal() { 
		 // TODO Auto-generated constructor stub 
		 render = false; 
		 } 
		 
		 public String processRequest() { 
		 
		 double values [] = {1.0, 2.0, 3.0, 4.0, 5.0}; 
		 
		 double minValue = StatUtils.min(values); 
		 double maxValue = StatUtils.max(values); 
		 double mean = StatUtils.mean(values); 
		 double variance = StatUtils.variance(values, mean); 
		 double std = Math.sqrt(variance); 
		 double median = StatUtils.percentile(values, 50.0); 
		 double q1 = StatUtils.percentile(values, 25.0); 
		 double q3 = StatUtils.percentile(values, 75.0); 
		
		 
		 statsList = new ArrayList <Statistics> (); 
		 
		 statsList.add(new Statistics("minValue", minValue)); 
		 statsList.add(new Statistics("maxValue", maxValue)); 
		 statsList.add(new Statistics("mean", mean)); 
		 statsList.add(new Statistics("variance", variance)); 
		 statsList.add(new Statistics("SD", std)); 
		 statsList.add(new Statistics("median", median)); 
		 statsList.add(new Statistics("q1", q1)); 
		 statsList.add(new Statistics("q3", q3)); 
		 
		 Iterator <Statistics> it = statsList.iterator(); 
		 Statistics sb = null; 
		 while(it.hasNext()) { 
		 sb = (Statistics) it.next(); 
		 System.out.println(": " + sb.getName() + " : " + sb.getValue()); 
		 } 
		 
		 render = true; 
		 return "SUCCESS"; 
		 } 
		 
		 public String getVariableName() { 
		 return variableName; 
		 } 
		 
		 public void setVariableName(String variableName) { 
		 this.variableName = variableName; 
		 } 
		 
		 public boolean isRender() { 
		 return render; 
		 } 
		 
		 public void setRender(boolean render) { 
		 this.render = render; 
		 } 
		 
		 public List<Statistics> getStatsList() { 
		 return statsList; 
		 } 
		 
		 public void setStatsList(List<Statistics> statsList) { 
		 this.statsList = statsList; 
		 } 
		 
		} 
