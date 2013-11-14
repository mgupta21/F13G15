<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%-- <%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%> --%>

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
			<h:panelGrid columns="3" style="background-color: Beige; border-style: solid; padding:20px;">
			
				<h:outputLabel for="UserName" value="#{msg.userName}" />
				<h:inputText id="UserName" value="#{uData.userLogin.userName}" maxlength="20">
					<%-- <f:validator validatorId="org.java.app.validator.UserNameValidator" />
					<f:validateLength minimum="5" maximum="20"/> --%>
				</h:inputText>
				<!-- <t:message id="userNameError" for="name" styleClass="error" /> -->
				<br/>
				
				<h:outputLabel for="userRole" value="#{msg.userRole}" />
				<h:panelGrid >
					<h:selectOneMenu id="userRole" value="#{loginService.role}" >
						<f:selectItem itemValue="03" itemLabel="Student" />
						<f:selectItem itemValue="02" itemLabel="Professor" />
					</h:selectOneMenu>
				</h:panelGrid>
				<br/>
				
				<h:outputLabel for="UIN" value="#{msg.userId}" />
				<h:inputText id="UIN" value="#{uData.userProfile.UIN}">
					<%-- <f:validateLength minimum="6" maximum="6"/>
					<f:validator validatorId="org.java.app.validator.IntFormatValidator" /> --%>
				</h:inputText>
				<!-- <t:message id="UINError" for="UIN" styleClass="error" /> -->
				<br/>
				
				<h:outputLabel for="firstName" value="#{msg.firstName}" />
				<h:inputText id="firstName" value="#{uData.userProfile.firstName}" required="true" maxlength="25" >
					<%-- <f:validator validatorId="org.java.app.validator.StringFormatValidator" /> --%>
				</h:inputText>
				<!-- <t:message id="firstNameError" for="firstName" styleClass="error" /> -->
				
				<h:outputLabel for="lastName" value="#{msg.lastName}" />
				<h:inputText id="lastName" value="#{uData.userProfile.lastName}" required="true" maxlength="25" >
					<%-- <f:validator validatorId="org.java.app.validator.StringFormatValidator" /> --%>
				</h:inputText>
				<!-- <t:message id="lastNameError" for="lastName" styleClass="error" /> -->
				
				
				<h:outputLabel for="email" value="#{msg.email}"/>
				<h:inputText id="email" value="#{uData.userProfile.email}" required="true" maxlength="40">
					<!-- <t:validateEmail detailMessage="Invalid Email Format" /> -->
				</h:inputText>
				<!-- <t:message id="emailError" for="emailId" styleClass="error" /> -->
				
				
				<h:outputLabel for="password" value="#{msg.password}"/> 
				<h:inputSecret id="password" value="#{uData.userLogin.password}" required="true" maxlength="20">
					<%-- <f:validateLength maximum="20" minimum="6" /> --%>
				</h:inputSecret>
				<!-- <t:message id="passwordError" for="password" styleClass="error" /> -->
				
				<h:outputLabel for="cpassword" value="#{msg.cpassword}"/>
				<h:inputSecret id="cpassword">
					<%-- <f:validateLength maximum="20" minimum="6" /> --%>
					<!-- <t:validateEqual for="password" detailMessage="Password Mismatch" /> -->
				</h:inputSecret>
				<!-- <t:message id="cpasswordError" for="cpassword" styleClass="error" /> -->
				
				<h:outputLabel value=" "/>
				<h:commandButton id="Register" action="#{loginService.register}" value="#{msg.register}"/>
				
			</h:panelGrid>
		</h:form>
	</center>

</f:view>
</body>
</html>