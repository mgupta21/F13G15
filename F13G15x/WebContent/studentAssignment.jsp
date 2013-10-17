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
			<h3>Quiz</h3>
		</center>
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<hr />
	</f:verbatim>
	<p>The following assignments are available for the logged in student</p>
	
	<ol>
	<li><a title="quiz1" href="quiz.jsp">Quiz 1</a></li>
	<li><a title="quiz2" href="quiz.jsp">Quiz 2</a></li>
	<li><a title="quiz3" href="quiz.jsp">Quiz 3</a></li>
	</ol>
	
	
</f:view>
</body>
</html>