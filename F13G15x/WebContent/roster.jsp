<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="messages" var="msg" />
	
	<f:verbatim>
		<center>
			<h3>Student Data Analysis</h3>
		</center>
		<br />
		<center>
			<h3>Student Roster</h3>
		</center>
		<br />
		<center><a title="Home" href="faces/home.jsp">Home</a></center>
		<br/>
		<hr />
	</f:verbatim>
	<h:panelGrid columns="2" style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid" >
		<f:facet name="header">
    		<h:outputText value="Select Roster Sheet"/>
 		 </f:facet>
		<h5>Select Table</h5>
		<h:panelGrid columns="2">
			<h:selectOneMenu id="selectTable" value="">
				  <f:selectItems itemValue="Table 1"  itemLabel="Table 1"/>
			</h:selectOneMenu> 
		</h:panelGrid><br/>
		<h:panelGrid columns="2">
			<h:commandButton value="#{msg.submit}" action="#{loginService.authenticate}" />
			<h:commandButton value="#{msg.back}" action="home.jsp" />
		</h:panelGrid>
	</h:panelGrid>
	<br/>
	<h:panelGrid columns="2" style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid" >
		<h5>Select Column</h5>
		<h:selectOneRadio id="radioCol" value="" layout="pageDirection">
			<f:selectItem itemValue="Col1" itemLabel="Column 1" />
			<f:selectItem itemValue="Col2" itemLabel="Column 2" />
			<f:selectItem itemValue="Col3" itemLabel="Column 3" />
		</h:selectOneRadio>
		<br/>
		<h5>Select Analysis</h5>
		<h:panelGrid columns="2">
			<h:selectOneMenu id="selectType" value="">
				  <f:selectItems itemValue="Mean"  itemLabel="Mean"/>
				  <f:selectItems itemValue="Median"  itemLabel="Median"/>
				  <f:selectItems itemValue="Standard Deviation"  itemLabel="Standard Deviation"/>
			</h:selectOneMenu> 
		</h:panelGrid><br/>
		<h:panelGrid columns="2">
			<h:commandButton value="#{msg.gen}" action="#{loginService.authenticate}" />
			<h:commandButton value="#{msg.back}" action="home.jsp?faces-redirect=true" />
		</h:panelGrid>
	</h:panelGrid>
	
	
</f:view>
</body>
</html>