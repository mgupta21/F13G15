package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="analysisTypes")
@SessionScoped
public class AnalysisTypes {
	
	public AnalysisType[] getAnalysisTypes() {
        return AnalysisType.values();
 }
	
}
