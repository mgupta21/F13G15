<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="../css/styles.css" />
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
			<h3>Analysis Report</h3>
		</center>
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<hr />
	</f:verbatim>
	<h:form>
	
	<table class="Table">
		<thead><tr>
		   <th class="questionTableHeader" scope="col">Table Name</th>
		   <th class="questionTableHeader" scope="col">Column</th>
		   <th class="questionTableHeader" scope="col"></th>
		   <th class="questionTableHeader" scope="col">Analysis Type</th>
		   <th class="questionTableHeader" scope="col"></th>
		   <th class="questionTableHeader" scope="col">Value</th>
		</tr></thead>
		<tbody>
		<tr class="questionTableOddRow">
		   <td>Table 1</td>
		   <td>Column 1</td>
		   <td>&nbsp;&nbsp;&nbsp;</td>
		   <td>Mean</td>
			<td>&nbsp;&nbsp;&nbsp;</td>
		   <td>45</td>
		</tr>
		<tr class="questionTableEvenRow">
		   <td>Table 2</td>
		   <td>Column 1</td>
		   <td>&nbsp;&nbsp;&nbsp;</td>
		   <td>Standard Deviation</td>
		   <td>&nbsp;&nbsp;&nbsp;</td>
		   <td>2.34</td>
		</tr>
		</table><br/>
	
		<h3>Graphical Analysis</h3>
		<p><img alt="graph" src="../images/graph.png"></p>
		
	</h:form>	
</f:view>
</body>
</html>