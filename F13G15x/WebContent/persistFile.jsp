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
<title>Create New Table</title>
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
			<h:form>
				
				<t:dataTable value="#{fileUpload.columns}" var="column" border="1" cellspacing="0" cellpadding="1" width="800">

						<h:column>
							<f:facet name="header">
								<h:outputText value="#{msg['ColumnHeader']}"/>
							</f:facet>
							<h:outputText value="#{column.colName}"/>
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputText value="#{msg['DefaultType']}"/>
							</f:facet>
							<h:outputText value="#{column.colDataType}"/>
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputText value="#{msg['ChooseType']}"/>
							</f:facet>
							<h:selectOneMenu value="#{column.colDataType}">
								<f:selectItems value="#{colDataTypes.dataTypes}" var="dataType" itemValue="#{dataType}" itemLabel="#{dataType}" />
							</h:selectOneMenu>
						</h:column>

					</t:dataTable>
				
				<br/>
				<h:commandButton value="#{msg.create}" action="#{fileUploadService.uploadTable}" />
				<h:commandButton value="#{msg.cancel}" action="home" />
				<t:messages layout="list" showDetail="true" showSummary="false" style="color: #FF0000;"/>
			</h:form>

	</center>
</f:view>
</body>
</html>