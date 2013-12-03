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
<title>Student Assignments</title>
</head>
<body>
<f:view>
<f:loadBundle basename="messages" var="msg" />
	<f:verbatim>
		<center>
			<h3>Active Assignments</h3>
		</center>
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<hr />
	</f:verbatim>
	<h:form>
		<center>
			<h:panelGrid rendered="#{!empty userService.userAssignments}">
				<h:panelGrid columns="2">
					<h:outputText value="Select examination"></h:outputText>
					<h:selectOneMenu value="#{assignmentService.examSelected}">
						<f:selectItems value="#{userService.userAssignments}" var="userAssignment" itemValue="#{userAssignment}" itemLabel="#{userAssignment}" />
					</h:selectOneMenu>
				</h:panelGrid>
				
				<h:commandButton action="#{assignmentService.takeExamination}" value="Take Examination"></h:commandButton>
			</h:panelGrid>
		</center>
		
		<center>
			<h:panelGrid rendered="#{empty userService.userAssignments}">
				<h:outputText value="#{msg.noAssignment}"/>
			</h:panelGrid>
		</center>	
	</h:form>
	
	
</f:view>
</body>
</html>