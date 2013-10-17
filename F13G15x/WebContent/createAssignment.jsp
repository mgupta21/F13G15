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
<title>Create Assignment</title>
</head>
<body>
<f:view>
<f:loadBundle basename="messages" var="msg" />
	<f:verbatim>
		<center>
			<h3>Create Assignment</h3>
		</center>
		
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<hr />
	</f:verbatim>
	<h:form>
	<h:panelGrid columns="2" style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid" >
		<f:facet name="header">
    		<h:outputText value="Select Quiz Table"/>
 		 </f:facet>
 		 <h:panelGrid columns="2" >
		<h5>Select Table</h5>		
			<h:selectOneMenu style="width:150px" id="selectTable"  value="#{analysis.table}">
				  <f:selectItems itemValue="Table 1"  itemLabel="Table 1"/>
			</h:selectOneMenu> 
		</h:panelGrid><br/>
		<h:panelGrid columns="2">
			<h:commandButton value="#{msg.submit}" action="#{loginService.authenticate}" />
			<h:commandButton value="#{msg.back}" action="home.jsp" />
		</h:panelGrid>
	</h:panelGrid>
	<br/>

<h:panelGrid>
<h:outputText>Select the column</h:outputText>
<h:selectOneMenu >
   	<f:selectItem itemValue="C1" itemLabel="Column 1" />
   	<f:selectItem itemValue="C2" itemLabel="Column 2" />
   	<f:selectItem itemValue="C3" itemLabel="Column 3" />
</h:selectOneMenu>
<h:outputText>Select the method</h:outputText>
<h:selectOneMenu >
   	<f:selectItem itemValue="M1" itemLabel="Mean" />
   	<f:selectItem itemValue="M2" itemLabel="Median" />
   	<f:selectItem itemValue="M3" itemLabel="Standard Deviation" />
</h:selectOneMenu>
<h:outputText>Enter the right answer upto 2 decimal places</h:outputText>
<h:inputText></h:inputText>
</h:panelGrid>	


 		<center>
				<h:commandButton id="create" value="Create Quiz" style="margin: 0 auto" action="#{quizCreateService.createQuiz}" />
				<h:commandButton id="add" value="Add question" style="margin: 0 auto" action="#{createquiz.addQuestion}" />
		</center>
	</h:form>	

</f:view>
</body>
</html>