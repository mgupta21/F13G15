<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="messages" var="msg" />
	
	<f:verbatim>
		<center>
			<h3>Student Data Analysis</h3>
		</center>
		<br />
		<hr />
	</f:verbatim>
	<h:form>
		<h:panelGrid columns="2" style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid" >
		
			<h:outputLabel value="#{msg.userName}" />
			<h:inputText id="name" value="#{login.userName}" size="20" />
			<h:outputLabel value="#{msg.password}"/>
			<h:inputSecret id="password" value="#{login.password}" size="20" />
			
			<h:outputLabel value=" "/>
			<h:commandButton action="#{loginService.authenticate}" value="#{msg.login}"/><a title="Register" href="register.jsp">New User Registration</a>
		</h:panelGrid>
	</h:form>
	
</f:view>
</body>
</html>