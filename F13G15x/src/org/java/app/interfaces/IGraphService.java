package org.java.app.interfaces;

import java.util.List;

public interface IGraphService {

	public void drawGraph(List<String> colData);
	public void createPiechart(List<String> colData);
	public void createBarchart(List<String> colData);
	public void createTimeseries(List<String> colData);
	public void createHistogram(List<String> colData);
	public void createScatterplot( List<String> colData);
	
}