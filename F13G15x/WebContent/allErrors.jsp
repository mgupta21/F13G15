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
	<f:verbatim>
		<center>
			<h3>Error</h3>
			<h5>Internal Error</h5>
		</center>
		<br />
		<center><a title="Login" href="login.jsp">Login</a></center>
		<br/>
		<hr />
	</f:verbatim>
	<h:panelGrid>
	    <h:outputText value="The follow error occured:" />
	    <h:inputTextarea style="width: 100%;" rows="20" readonly="true" 
	                                 value="#{Error.stackTrace}" />
	</h:panelGrid>
	
</f:view>
</body>
</html>