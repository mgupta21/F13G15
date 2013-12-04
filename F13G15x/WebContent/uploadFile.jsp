<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
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
			<h:panelGrid style="background-color: Beige; border-style: solid; padding:20px;">
		
					<h:form enctype="multipart/form-data">
		
							<h:panelGrid columns="2" rendered="#{loginService.student}">
								<h:selectOneRadio id="iradio" value="#{fileUpload.tableType}" layout="pageDirection" required="true">
									<f:selectItem itemValue="idx" itemLabel="Individual" />
								</h:selectOneRadio>
								<t:message id="iradioError" for="iradio" style="color: #FF0000;" />
							</h:panelGrid><br/>
							
							<h:panelGrid columns="3" rendered="#{loginService.prof}">
								<h:selectOneRadio id="pradio" value="#{fileUpload.tableType}" layout="pageDirection" required="true">
									<f:selectItem itemValue="asx" itemLabel="Assignment" />
								</h:selectOneRadio>
								<t:message id="pradioError" for="pradio" style="color: #FF0000;" />
							</h:panelGrid><br/>
							
							<h:panelGrid columns="3" rendered="#{loginService.admin}">
								<h:selectOneRadio id="aradio" value="#{fileUpload.tableType}" layout="pageDirection" required="true">
									<f:selectItem itemValue="asx" itemLabel="Assignment" />
									<f:selectItem itemValue="idx" itemLabel="Individual" />
								</h:selectOneRadio>
								<t:message id="aradioError" for="aradio" style="color: #FF0000;" />
							</h:panelGrid><br/>
							
							<h:panelGrid columns="3">
							<h:outputLabel value="#{msg.tableName}" />
								<h:inputText id="tableName" value="#{fileUpload.tableName}" required="true" size="15" maxlength="15" />
							<t:message id="tableNameError" for="tableName" style="color: #FF0000;" />
							</h:panelGrid><br/><br/>
							
							<h:outputLabel value="#{msg.chooseFile}" />
								<t:inputFileUpload id="fileSelect" storage="default" value="#{fileUpload.uploadedFile}" required="true"></t:inputFileUpload>
								<t:message id="fileSelectError" for="fileSelect" style="color: #FF0000;" />
							<br/><br/>
							<%-- <h:commandButton value="#{msg.upload}" action="persistFile.jsp?faces-redirect=true" /> --%>
							<h:commandButton value="#{msg.upload}" action="#{fileUploadService.redirectToPersist}" />
							<%-- <t:messages layout="list" showDetail="true" showSummary="false" style="color: #FF0000;"/> --%>
						<br/><br/>
					</h:form>
				</h:panelGrid>
		</center>
</f:view>
</body>
</html>