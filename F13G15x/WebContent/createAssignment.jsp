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
<title>Create Assignment</title>
</head>
<body>
<f:view>
<f:loadBundle basename="messages" var="msg" />
	<f:verbatim>
		<center>
			<h3>Create Assignment</h3>
		</center>
		
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<hr />
	</f:verbatim>
	<center>
		<h:form>
		<h:panelGrid style="background-color: Beige; border: 3px solid; padding:30px;">
			<f:facet name="header">
	    		<h:outputText value="Select Assignment Table"/>
	 		 </f:facet>
	 		<br/>
	 		 <h:panelGrid columns="2">
		 		 <h:outputText value="#{msg['selectTable']}" />
					<h:selectOneMenu value="1">
						<f:selectItems value="#{userService.geTablesByUserName()}" var="column" itemLabel="" itemValue="" />
				</h:selectOneMenu>
			</h:panelGrid>
	 		 
	 	</h:panelGrid>
			<br/>
		
			<h:panelGrid style="background-color: Beige; border: 3px solid; padding:30px;">
			
			<h:panelGrid columns="2">
				<h:outputLabel for="assignmentName" value="#{msg.assignmentName}" />
					<h:inputText id="assignmentName" value="[assignmentName]" required="true" size="15" maxlength="15" />
					<%-- <t:message for="assignmentName" styleClass="error"></t:message> --%>
				<br/>
			</h:panelGrid>
			
			<h5>Question 1</h5>
	 		 
	 		 <h:panelGrid columns="6" >
				<h:outputText value="#{msg['selectColumn']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['analysisType']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['points']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
			</h:panelGrid>
			<br/>
			
			<h5>Question 2</h5>
	 		 
	 		 <h:panelGrid columns="6" >
				<h:outputText value="#{msg['selectColumn']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['analysisType']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['points']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
			</h:panelGrid>
			<br/>
			
			<h5>Question 3</h5>
	 		 
	 		 <h:panelGrid columns="6" >
				<h:outputText value="#{msg['selectColumn']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['analysisType']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['points']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
			</h:panelGrid>
			<br/>
			
			<h5>Question 4</h5>
	 		 
	 		 <h:panelGrid columns="6" >
				<h:outputText value="#{msg['selectColumn']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['analysisType']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['points']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
			</h:panelGrid>
			<br/>
			
			<h5>Question 5</h5>
	 		 
	 		 <h:panelGrid columns="6" >
				<h:outputText value="#{msg['selectColumn']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['analysisType']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
				<h:outputText value="#{msg['points']}" />
				<h:selectOneMenu value="1">
					<f:selectItems value="1" var="column" itemLabel="#{column.name}" itemValue="#{column}" />
				</h:selectOneMenu>
			</h:panelGrid>
			<br/>
			
		</h:panelGrid>
		
		<br/>
	
	 		<center>
					<h:commandButton id="create" value="Create Assignment" style="margin: 0 auto" action="#{quizCreateService.createQuiz}" />
					<h:commandButton id="add" value="Add question" style="margin: 0 auto" action="#{createquiz.addQuestion}" />
			</center>
		</h:form>	
	</center>
</f:view>
</body>
</html>