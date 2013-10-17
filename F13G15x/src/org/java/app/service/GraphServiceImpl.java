package org.java.app.service;


import java.util.List;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import org.java.app.interfaces.IGraphService;


@ManagedBean(name = "graphService")
@SessionScoped

public class GraphServiceImpl implements IGraphService {

	@Override
	public void drawGraph(List<String> colData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPiechart(List<String> colData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBarchart(List<String> colData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTimeseries(List<String> colData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createHistogram(List<String> colData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createScatterplot(List<String> colData) {
		// TODO Auto-generated method stub
		
	}

}
