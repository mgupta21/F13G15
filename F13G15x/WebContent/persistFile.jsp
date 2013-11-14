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
				<t:dataTable value="#{fileUpload.columns}" var="tableColumns">

					<h:column>
						<f:facet name="header">
							<h:outputText value="#{msg['ColumnHeader']}"/>
						</f:facet>
						<h:outputText value="#{tableColumns.colName}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputText value="#{msg['DefaultType']}"
								style="font-size: 20px; font-weight: bold" />
						</f:facet>
						<h:outputText value="#{tableColumns.colDataType}"
							style="font-size: 20px; font-weight: bold" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputText value="#{msg['ChooseType']}"/>
						</f:facet>
						<h:selectOneMenu value="#{tableColumns.colDataType}">
							<f:selectItems value="#{colDataTypes.dataTypes}"
								var="defaultdatatype" itemValue="#{defaultdatatype}"
								itemLabel="#{defaultdatatype.dataType}" />
						</h:selectOneMenu>
					</h:column>

				</t:dataTable>
				
				<h:commandButton value="#{msg.create}" action="#{fileUpload.createTable}" />
				<h:commandButton value="#{msg.cancel}" action="home" />
				
			</h:form>

	</center>
</f:view>
</body>
</html>