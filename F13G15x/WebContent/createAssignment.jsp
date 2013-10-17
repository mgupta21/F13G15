<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
		
 		<table class="Table">
<thead><tr>
   <th class="questionTableHeader" scope="col">Question Number</th>
   <th class="questionTableHeader" scope="col">Question</th>
   <th class="questionTableHeader" scope="col">Answer</th>
   <th class="questionTableHeader" scope="col">Method</th>
</tr></thead>
<tbody>
<tr class="questionTableOddRow">
   <td>1</td>
   <td>Find the Mean of column 1</td>
   <td>30</td>
   <td>Mean</td>
</tr>
<tr class="questionTableEvenRow">
   <td>2</td>
   <td>Find the std dev of column 3</td>
   <td>2.34</td>
   <td>Std Dev</td>
</tr>
</table>

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