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
<title>Assignment Created</title>
</head>
<body>
<f:view>
	<f:verbatim>
		<center>
			<h3>Success</h3>
			<h:panelGrid rendered="#{loginService.prof}">
				<h5>Assignment is Successfully created and assigned to students registered for this course</h5>
			</h:panelGrid>
			<h:panelGrid rendered="#{loginService.admin}">
				<h5>Assignment is Successfully created and assigned to registered students as well as Admin. Admin can now take the exam to test the functionality.</h5>
			</h:panelGrid>	
		</center>
		<br />
		<center><a title="home" href="home.jsp">Home</a></center>
		<br/>
		<hr />
	</f:verbatim>
</f:view>
</body>
</html>