package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="colDataTypes")
@SessionScoped
public class ColumnDataTypes {
	
	 public ColumnDataType[] getDataTypes() {
	        return ColumnDataType.values();
	 }

}
