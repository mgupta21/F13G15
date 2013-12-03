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
<title>Login</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="messages" var="msg" />
	
	<f:verbatim>
		<center>
			<h3>User Login</h3>
		</center>
		<br />
		<center><a title="Welcome" href="index.jsp">Welcome Page</a></center>
		<br/><hr /><br/><br/>
	</f:verbatim>
	<center>
		<h:form>
			<h:panelGrid columns="2" style="background-color: Beige; border-style: solid; padding:20px;">
			
				<h:outputLabel value="#{msg.userName}" />
				<h:inputText id="name" value="#{uData.userLogin.userName}" size="20" />
				<h:outputLabel value="#{msg.password}"/>
				<h:inputSecret id="password" value="#{uData.userLogin.password}" size="20" />
				
				<h:outputLabel value="#{msg.course}" />
				<h:selectOneMenu id="course" value="#{uData.userProfile.course}" required="true">
					<f:selectItems value="#{ddList.courses}" var="course" itemValue="#{course}" itemLabel="#{course}" />
				</h:selectOneMenu>
				
				<h:outputLabel value=" "/>
				<h:commandButton action="#{loginService.authenticate}" value="#{msg.login}"/>
				<span style="text-align: center;font-size: 13px; margin-top: 10px;">Not Registered? <a title="Register" href="register.jsp">Create an account</a></span>
			
			</h:panelGrid>
		</h:form>
	</center>
	
</f:view>
</body>
</html>