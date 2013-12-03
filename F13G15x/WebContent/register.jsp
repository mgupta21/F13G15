<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
	body {
		background-image: url(../images/bg.jpg);
	}
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link type="text/css" rel="stylesheet" href="../css/styles.css" />
</head>
<body>
<f:view>
	<f:loadBundle basename="messages" var="msg" />
	<f:verbatim>
		<center>
			<h3>New User Registration</h3>
		</center>
		<br />
		<center><a title="Welcome" href="index.jsp">Welcome Page</a></center>
		<br/><hr /><br/><br/>
	</f:verbatim>
	<center>
		<h:form>
			<h:panelGrid columns="3" style="background-color: Beige; border-style: solid; padding:25px;">
			
				<h:outputLabel for="UserName" value="#{msg.userName}" />
				<h:inputText id="UserName" value="#{uData.userLogin.userName}" maxlength="20" required="true">
					<%-- <f:validator validatorId="org.java.app.validator.UserNameValidator" /> --%>
					<f:validateLength minimum="5" maximum="20"/> 
				</h:inputText>
				<t:message id="userNameError" for="UserName" style="color: #FF0000;" />
				
				<h:outputLabel for="userRole" value="#{msg.userRole}" />
				<h:panelGrid >
					<h:selectOneMenu id="userRole" value="#{loginService.role}" required="true" >
						<f:selectItem itemValue="03" itemLabel="Student" />
						<f:selectItem itemValue="02" itemLabel="Professor" />
					</h:selectOneMenu>
				</h:panelGrid>
				<t:message id="userRoleError" for="userRole" style="color: #FF0000;" />
				
				<h:outputLabel for="UIN" value="#{msg.userId}" />
				<h:inputText id="UIN" value="#{uData.userProfile.UIN}" required="true">
					<f:validateLength minimum="6" maximum="6"/>
					<%-- <f:validator validatorId="org.java.app.validator.IntFormatValidator" /> --%>
				</h:inputText>
				<t:message id="UINError" for="UIN" style="color: #FF0000;" />
				
				<h:outputLabel for="firstName" value="#{msg.firstName}" />
				<h:inputText id="firstName" value="#{uData.userProfile.firstName}" required="true" maxlength="25" >
					<f:validator validatorId="org.java.app.validator.StringFormatValidator" />
				</h:inputText>
				<t:message id="firstNameError" for="firstName" style="color: #FF0000;" />
				
				<h:outputLabel for="lastName" value="#{msg.lastName}" />
				<h:inputText id="lastName" value="#{uData.userProfile.lastName}" required="true" maxlength="25" >
					<f:validator validatorId="org.java.app.validator.StringFormatValidator" />
				</h:inputText>
				<t:message id="lastNameError" for="lastName" style="color: #FF0000;" />
				
				<h:outputLabel for="email" value="#{msg.email}"/>
				<h:inputText id="email" value="#{uData.userProfile.email}" required="true" maxlength="40">
				<t:validateEmail detailMessage="Invalid Email Format" />
				</h:inputText>
				<t:message id="emailError" for="email" style="color: #FF0000;" />
				
				<h:outputLabel for="password" value="#{msg.password}"/> 
				<h:inputSecret id="password" value="#{uData.userLogin.password}" required="true" maxlength="20">
				<f:validateLength maximum="20" minimum="6" />
				</h:inputSecret>
				<t:message id="passwordError" for="password" style="color: #FF0000;" />
				
				<h:outputLabel for="cpassword" value="#{msg.cpassword}"/>
				<h:inputSecret id="cpassword" required="true">
					<t:validateEqual for="password" detailMessage="Password Mismatch" />
				</h:inputSecret>
				<t:message id="cpasswordError" for="cpassword" style="color: #FF0000;" />
				
					<h:outputLabel value="#{msg.course}" />
					<h:selectOneMenu id="course" value="#{uData.userProfile.course}" required="true">
						<f:selectItems value="#{ddList.courses}" var="course" itemValue="#{course}" itemLabel="#{course}" />
					</h:selectOneMenu>
					<t:message id="courseError" for="course" style="color: #FF0000;" />
				
				<h:outputLabel value=" "/>
				<h:commandButton id="Register" action="#{loginService.register}" value="#{msg.register}"/>
			</h:panelGrid>
			<t:messages layout="list" showDetail="true" showSummary="false" style="color: #FF0000;"/>
			<br/>
		</h:form>
	</center>

</f:view>
</body>
</html>