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
<title>Quiz</title>
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
	<h:form>
	<h:panelGrid columns="2" style="background-color: Beige; border-bottom-style: solid; border-top-style: solid; border-left-style: solid; border-right-style: solid;margin: 0 auto;" >
	<h:panelGroup style="text-align:center">
		<h5>Question 1</h5>
	
		<h:selectOneRadio>
        	<f:selectItem id="q11" itemLabel="Answer 1" itemValue="1" />
        	<f:selectItem id="q12" itemLabel="Answer 2" itemValue="2" />
        	<f:selectItem id="q13" itemLabel="Answer 3" itemValue="3" />
        	<f:selectItem id="q14" itemLabel="Answer 4" itemValue="4" />
        </h:selectOneRadio>
		<h5>Question 2</h5>
		<h:selectOneRadio>
        	<f:selectItem id="q21" itemLabel="Answer 1" itemValue="1" />
        	<f:selectItem id="q22" itemLabel="Answer 2" itemValue="2" />
        	<f:selectItem id="q23" itemLabel="Answer 3" itemValue="3" />
        	<f:selectItem id="q24" itemLabel="Answer 4" itemValue="4" />	
    	</h:selectOneRadio>	
		<h5>Question 3</h5>
		<h:selectOneRadio>
        	<f:selectItem id="q31" itemLabel="Answer 1" itemValue="1" />
        	<f:selectItem id="q32" itemLabel="Answer 2" itemValue="2" />
        	<f:selectItem id="q33" itemLabel="Answer 3" itemValue="3" />
        	<f:selectItem id="q34" itemLabel="Answer 4" itemValue="4" />	
    	</h:selectOneRadio>	
    	<h5>Question 4</h5>
		<h:selectOneRadio>
        	<f:selectItem id="q41" itemLabel="Answer 1" itemValue="1" />
        	<f:selectItem id="q42" itemLabel="Answer 2" itemValue="2" />
        	<f:selectItem id="q43" itemLabel="Answer 3" itemValue="3" />
        	<f:selectItem id="q44" itemLabel="Answer 4" itemValue="4" />	
    	</h:selectOneRadio>	
    	<h5>Question 5</h5>
		<h:selectOneRadio>
        	<f:selectItem id="q51" itemLabel="Answer 1" itemValue="1" />
        	<f:selectItem id="q52" itemLabel="Answer 2" itemValue="2" />
        	<f:selectItem id="q53" itemLabel="Answer 3" itemValue="3" />
        	<f:selectItem id="q54" itemLabel="Answer 4" itemValue="4" />	
    	</h:selectOneRadio>	
		<center><h:commandButton id="submit" value="Submit Quiz" style="margin: 0 auto" action="#{quizSubmitService.submitQuiz}" /></center>
	</h:panelGroup>	
	</h:panelGrid>
	</h:form>	
</f:view>
</body>
</html>