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
			<h3>Generate Report</h3>
		</center>
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<br/>
		<hr />
	</f:verbatim>
	<h:panelGrid columns="2" style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid" >
		<h:panelGrid columns="1">
			<h:selectOneRadio id="radio" value=""
				layout="pageDirection">
				<f:selectItem itemValue="quiz" itemLabel="Quiz" />
				<f:selectItem itemValue="assignment" itemLabel="Assignment" />
				<f:selectItem itemValue="rooster" itemLabel="Rooster" />
			</h:selectOneRadio>
		</h:panelGrid>
		<h:panelGrid columns="2">
			<h:commandButton value="#{msg.upload}" action="#{loginService.authenticate}" />
			<h:commandButton value="#{msg.back}" action="home.jsp" />
		</h:panelGrid>
	</h:panelGrid>
	
</f:view>
</body>
</html>