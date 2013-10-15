<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%-- <%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link type="text/css" rel="stylesheet" href="../css/styles.css" />
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
		
			<h:outputLabel value="#{msg.firstName}" />
			<h:inputText id="firstName" value="#{userName.firstName}" size="20" />
			<%-- <t:message id="firstNameError" for="firstName" styleClass="error" /> --%>
			<h:outputLabel value="#{msg.lastName}" />
			<h:inputText id="lastName" value="#{userName.lastName}" size="20" />
			<%-- <t:message id="lastNameError" for="lastName" styleClass="error" /> --%>
			<h:outputLabel value="#{msg.userId}" />
			<h:inputText id="UIN" value="#{userName.UIN}" size="20" />
			<%-- <t:message id="UINError" for="UIN" styleClass="error" /> --%>
			<h:outputLabel value="#{msg.userName}" />
			<h:inputText id="name" value="#{userName.name}" size="20" />
			<%-- <t:message id="nameError" for="name" styleClass="error" /> --%>
			<h:outputLabel value="#{msg.password}"/>
			<h:inputSecret id="password" value="#{userName.password}" size="20" />
			<%-- <t:message id="passwordError" for="password" styleClass="error" /> --%>
			<h:outputLabel value="#{msg.cpassword}"/>
			<h:inputSecret id="cpassword" value="#{userName.password}" size="20" />
			<%-- <t:validateEqual for="password" detailMessage="Password Mismatch" />
			<t:message id="passwordError" for="cpassword" styleClass="error" /> --%>
			
			<h:outputLabel value=" "/>
			<h:commandButton id="Register" action="#{userName.verify}" value="Register"/>
		</h:panelGrid>
	</h:form>

</f:view>
</body>
</html>