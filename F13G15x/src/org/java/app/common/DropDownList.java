package org.java.app.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@ManagedBean(name="ddList")
@ApplicationScoped
public class DropDownList {
	
	public List<SelectItem> getAssignments(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem("Exam01", "Exam 1"));
		list.add(new SelectItem("Exam02", "Exam 2"));
		list.add(new SelectItem("Exam03", "Exam 3"));
		list.add(new SelectItem("Exam04", "Exam 4"));
		list.add(new SelectItem("Quiz01", "Quiz 1"));
		list.add(new SelectItem("Quiz02", "Quiz 2"));
		list.add(new SelectItem("Quiz03", "Quiz 3"));
		list.add(new SelectItem("Quiz04", "Quiz 4"));
		list.add(new SelectItem("HW01", "Home Work 1"));
		list.add(new SelectItem("HW02", "Home Work 2"));
		list.add(new SelectItem("HW03", "Home Work 3"));
		list.add(new SelectItem("HW04", "Home Work 4"));
		return list;
	}
	
	public List<SelectItem> getAnalysisTypes(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem("min", "Minimum"));
		list.add(new SelectItem("max", "Maximum"));
		list.add(new SelectItem("mean", "Mean"));
		list.add(new SelectItem("variance", "Variance"));
		list.add(new SelectItem("std", "Standard Deviation"));
		list.add(new SelectItem("median", "Median"));
		list.add(new SelectItem("q1", "First Quartile"));
		list.add(new SelectItem("q2", "Second Quartile"));
		list.add(new SelectItem("q3", "Third Quartile"));
		return list;
	}
	
	public List<SelectItem> getCourses(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem("s901", "Statistics 901"));
		list.add(new SelectItem("s902", "Statistics 902"));
		list.add(new SelectItem("s903", "Statistics 903"));
		return list;
	}
	
	public List<SelectItem> getPoints(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(2, "2"));
		list.add(new SelectItem(5, "5"));
		list.add(new SelectItem(10, "10"));
		return list;
	}
	
}
