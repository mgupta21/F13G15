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
	<f:verbatim>
		<center>
			<h3>File Upload Success</h3>
		</center>
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<br/>
		<hr />
	</f:verbatim>
	<h:form>
		<center>
			<h3>Uploaded Table</h3>
			<h:panelGrid columns="2">
				<h:outputText value="Number Of Columns: "/>
				<h:outputText value="#{fileUploadService.numberColumns }"/>
				<h:outputText value="Number Of Rows: "/>
				<h:outputText value="#{fileUploadService.numberRows }"/>
			</h:panelGrid><br/>
			<h:panelGrid columns="2" >
				<t:dataTable value="#{fileUploadService.result}" var="row" border="1" cellspacing="0" cellpadding="1" width="800">
					<t:columns var="col" value="#{fileUploadService.columnNames}">
						<f:facet name="header">
							<t:outputText value="#{col}"></t:outputText>
						</f:facet>
						<t:outputText value="#{row[col]}"/>
					</t:columns>
				</t:dataTable>
			</h:panelGrid>
		</center>
	</h:form>
	
</f:view>
</body>
</html>