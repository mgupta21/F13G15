<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
	body {
		background-image: url(../images/bg.jpg);
	}
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit document</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="messages" var="msg" />
	
	<f:verbatim>
		<center>
			<h3>Edit Document</h3>
		</center>
		<br />
		
		<center><a title="Home" href="home.jsp">Home</a></center>
		<br/>
		<hr />
	</f:verbatim>
	<h:form>
	<h:panelGrid  style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid" >
		<f:facet name="header">
    		<h:outputText value="Select the type of Data you want to edit"/>
 		 </f:facet>
		<h:panelGrid >
			<h:selectOneRadio id="radio" value=""
				layout="pageDirection">
				<f:selectItem itemValue="quiz" itemLabel="Quiz" />
				<f:selectItem itemValue="exam" itemLabel="Exam" />
			</h:selectOneRadio>
		</h:panelGrid>
		<h:panelGrid columns="2" >
			<h:commandButton  value="#{msg.submit}" />
			<h:commandButton value="#{msg.cancel}" action="home.jsp" />
		</h:panelGrid>
	</h:panelGrid>
		<br/><br/>
		
	<h:panelGrid style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid" >
		<f:facet name="header">
    		<h:outputText value="Select the sheet you want to edit"/>
 		 </f:facet>
		
		<h:panelGrid >
			<h:selectOneMenu id="dropdown" value="" >
				
				<f:selectItem itemValue="Sheet1" itemLabel="Sheet1" />
				<f:selectItem itemValue="Sheet2" itemLabel="Sheet2" />
			</h:selectOneMenu>
		</h:panelGrid>
		
		<h:panelGrid columns="2" >
			<h:commandButton value="#{msg.submit}"  />
			<h:commandButton value="#{msg.cancel}" action="home.jsp" />
		</h:panelGrid>
	</h:panelGrid>
	
	<br/><br/>
	
	
	<h:panelGrid style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid">
	<f:facet name="header">
    		<h:outputText value="Document Data"/>
 	</f:facet>
	
	
	
	<h:panelGroup>
           	<h:outputText value="Col1 " />
        	<h:outputText value="Col2 " />
    </h:panelGroup>
    <h:panelGroup>
    		<h:outputText value="Val1.1 " />
        	<h:outputText value="Val1.2 " />
        	<h:commandLink value="Edit " />
        	<h:commandLink value="Update " />
    </h:panelGroup>
    <h:panelGroup>
    		<h:outputText value="Val2.1 " />
        	<h:outputText value="Val2.2 " />
        	<h:commandLink value="Edit " />
        	<h:commandLink value="Update " />
    </h:panelGroup>
	
	</h:panelGrid>
	</h:form>
</f:view>
</body>
</html>