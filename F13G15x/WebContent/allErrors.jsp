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
<title>Error</title>
</head>
<body>
<f:view>
	<f:verbatim>
		<center>
			<h3>System Error</h3>
			<h5 style="color: #FF0000;">You have been Logged Out</h5>
			<h5>Application is Temporarily Down. Please try after some time </h5>
		</center>
		<br />
		<center><a title="Login" href="login.jsp">Login</a></center><br/>
		<hr />
	</f:verbatim>
	<h:form>
			<h:panelGrid>
			    <h:outputText value="The following error has occured:" />
			    <h:inputTextarea style="width: 300%;" rows="20" readonly="true" value="#{sysError.stackTrace}" />
			</h:panelGrid>
	</h:form>
	
	
</f:view>
</body>
</html>