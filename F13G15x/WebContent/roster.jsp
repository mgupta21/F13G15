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
			<h3>Roster</h3>
		</center>
		<br />
		<center><a title="Home" href="home.jsp">Home</a></center>
		<hr />
	</f:verbatim>
	<h:form>
	
	<table class="Table">
		<thead><tr>
		   <th class="questionTableHeader" scope="col">Student UIN</th>
		   <th class="questionTableHeader" scope="col">Name</th>
		   <th class="questionTableHeader" scope="col">Qz 1 Score</th>
		   <th class="questionTableHeader" scope="col">Qz 2 Score</th>
		   <th class="questionTableHeader" scope="col">Asgn. 1 Score</th>

		<tbody>
		<tr class="questionTableOddRow">
		   <td>67251751</td>
		    <td>Student 1&nbsp;&nbsp;&nbsp;&nbsp;</td>
		     <td>100&nbsp;&nbsp;&nbsp;&nbsp;</td>
		      <td>90&nbsp;&nbsp;&nbsp;&nbsp;</td>
		       <td>85&nbsp;&nbsp;&nbsp;&nbsp;</td>
		       
		</tr>
		<tr class="questionTableEvenRow">
		   <td>67251752</td>
		    <td>Student 2&nbsp;&nbsp;&nbsp;&nbsp;</td>
		     <td>83&nbsp;&nbsp;&nbsp;&nbsp;</td>
		      <td>87&nbsp;&nbsp;&nbsp;&nbsp;</td>
		       <td>98&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
<p><a title="Edit Roster" href="editRoster.jsp">Edit Roster</a></p>
	
	</h:form>	
</f:view>
</body>
</html>