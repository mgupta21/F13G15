<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome, Mayank</title>
</head>
<body>
<f:view>
	<f:verbatim>
		<center>
			<h3>Student Data Analysis</h3>
		</center>
		<br />
		<hr />
		<center><a title="Uplaod Data" href="upload.jsp">Upload Data Sheet</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a title="Data Analysis" href="analysis.jsp">Data Analysis</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a title="View Report" href="report.jsp">View Report</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a title="Student Rooster" href="roster.jsp">Roster</a>
		&nbsp;&nbsp;|&nbsp;&nbsp;<a title="Download Data" href="download.jsp">Download Data</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a title="Edit Document" href="edit.jsp">Edit Document</a>
		&nbsp;&nbsp;|&nbsp;&nbsp;<a title="Assignment" href="createAssignment.jsp">Create Assignment</a></center>
		<br/>
	</f:verbatim>
</f:view>
</body>
</html>