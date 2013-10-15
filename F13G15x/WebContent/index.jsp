<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="messages" var="msg" />
	<f:verbatim>
		<center><a title="Login" href="login.jsp">Login</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a title="New User" href="register.jsp">Register</a></center>
		<br/><center><a href="pdf/documentation.pdf">Documentation</a></center>
		<center>
			<h3>Student Data Analysis</h3>
		</center>
		<br />
		<hr />
		<b>Team Members:</b><br/>
		Mayank Gupta<br/>
		Ankush Gupta<br/>
		Amit Kumar Tripathi<br/>
		Simranjit Gill<br/>
	</f:verbatim>

</f:view>
</body>
</html>