<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<style>
	body {
		background-image: url(../images/bg.jpg);
	}
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="messages" var="msg" />
	
	<f:verbatim>
		<center>
			<h3>Upload Data Sheet</h3>
		</center>
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<br/>
		<hr />
	</f:verbatim>
	<br/><br/>
	<center>
	<h:form enctype="multipart/form-data">
		<h:panelGrid columns="2" style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid" >
			<f:facet name="header">
	    		<h:outputText value="#{msg.uploadHeader}"/>
	 		 </f:facet>
	 		 
			<h:panelGrid columns="2">
				<h:selectOneRadio id="radio" value="#{fileUpload.tableType}" layout="pageDirection">
					<f:selectItem itemValue="qzx" itemLabel="Quiz" />
					<f:selectItem itemValue="asx" itemLabel="Assignment" />
					<f:selectItem itemValue="hwx" itemLabel="Home Work" />
					<f:selectItem itemValue="rsx" itemLabel="Roster" />
				</h:selectOneRadio>
			</h:panelGrid><br/>
			
			<h:outputLabel for="tableName" value="#{msg.tableName}" />
				<h:inputText id="tableName" value="#{fileUpload.tableName}" required="true" size="15" maxlength="15" />
				<t:message for="tableName" styleClass="error"></t:message>
			<br/>
			
			<h:outputLabel for="FileUpload" value="#{msg.chooseFile}" />
				<t:inputFileUpload id="FileUpload" storage="default" value="#{fileUpload.uploadedFile}" required="true">
				</t:inputFileUpload>
			<t:message for="FileUpload" styleClass="error"></t:message>
			
			<h:commandButton value="#{msg.upload}" action="persist" />
			
		</h:panelGrid>
		
	</h:form>
	</center>
</f:view>
</body>
</html>