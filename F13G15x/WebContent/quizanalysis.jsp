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
<f:loadBundle basename="messages" var="msg" />
	<f:verbatim>
		<center>
			<h3>Quiz Analysis</h3>
		</center>
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<hr />
	</f:verbatim>
	<h:form>
	
	<table class="Table">
		<thead><tr>
		   <th class="questionTableHeader" scope="col">Question #</th>
		   <th class="questionTableHeader" scope="col">Question</th>
		   <th class="questionTableHeader" scope="col"></th>
		   <th class="questionTableHeader" scope="col">Correct Answer</th>
		   <th class="questionTableHeader" scope="col"></th>
		   <th class="questionTableHeader" scope="col">Your Answer</th>
		</tr></thead>
		<tbody>
		<tr class="questionTableOddRow">
		   <td>1</td>
		   <td>Find the Mean of column 1</td>
		   <td>&nbsp;&nbsp;&nbsp;</td>
		   <td>30</td>
			<td>&nbsp;&nbsp;&nbsp;</td>
		   <td>45</td>
		</tr>
		<tr class="questionTableEvenRow">
		   <td>2</td>
		   <td>Find the std dev of column 3</td>
		   <td>&nbsp;&nbsp;&nbsp;</td>
		   <td>2.34</td>
		   <td>&nbsp;&nbsp;&nbsp;</td>
		   <td>2.34</td>
</tr>
</table>
	
	</h:form>	
</f:view>
</body>
</html>