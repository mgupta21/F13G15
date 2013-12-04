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
<title>Welcome</title>
</head>
	<body>
		<f:view>
				<f:loadBundle basename="messages" var="msg" />
				<h:form>
					<h:panelGrid>
						<p class="right">
							<%-- <h:outputLabel value=" "/>
							<h:commandButton value="#{msg.logout}" action="#{loginService.logout}"/> --%>
							<h:commandLink value="#{msg.logout}" action="#{loginService.logout}" /><!-- <a title="Welcome" href="index.jsp">Logout</a> -->
							<h:outputText value="Welcome #{uData.userProfile.lastName}, #{uData.userProfile.firstName}" style="font-size: 20px; font-weight: bold" > </h:outputText>						
						</p>
					</h:panelGrid>
				<f:verbatim>
					<center><h3>Student Data Analysis</h3></center><br />
					<center><a href="pdf/documentation.pdf" target="_blank">Documentation</a></center><br/>
					<hr />
					<center>
						<h:panelGrid rendered="#{loginService.student}">
							<a title="Uplaod Data" href="uploadFile.jsp">Upload Data Sheet</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Data Analysis" href="analysis.jsp">Data Analysis</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Student Assignment" href="studentAssignment.jsp">Student Assignment</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Download Data" href="download.jsp">Download Data</a>
						</h:panelGrid>
						<h:panelGrid rendered="#{loginService.prof}">
							<a title="Uplaod Data" href="uploadFile.jsp">Upload Data Sheet</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Uplaod Roster" href="uploadRoster.jsp">Upload Roster</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Download Data" href="download.jsp">Download Data</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<%-- <h:commandLink value="View Rooster" action="#{rosterService.viewRoster}" />&nbsp;&nbsp;|&nbsp;&nbsp; --%>
							<a title="View Rooster" href="roster.jsp">View Roster</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Rooster Data Analysis" href="rosterAnalysis.jsp">Roster Data Analysis</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Edit Document" href="edit.jsp">Edit Document</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Create Assignment" href="createAssignment.jsp">Create Assignment</a>
						</h:panelGrid>
						<h:panelGrid rendered="#{loginService.admin}">
							<a title="Uplaod Data" href="uploadFile.jsp">Upload Data Sheet</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Uplaod Roster" href="uploadRoster.jsp">Upload Roster</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							
							<a title="Download Data" href="download.jsp">Download Data</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Data Analysis" href="analysis.jsp">Data Analysis</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Edit Document" href="edit.jsp">Edit Document</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<!-- <a title="View Report" href="studentAssignment.jsp">Student Assignment</a>&nbsp;&nbsp;|&nbsp;&nbsp; -->
							<a title="Student Assignment" href="studentAssignment.jsp">Take Assignment</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Create Assignment" href="createAssignment.jsp">Create Assignment</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="View Rooster" href="roster.jsp">View Roster</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a title="Rooster Data Analysis" href="rosterAnalysis.jsp">Roster Data Analysis</a>
						</h:panelGrid>
					<!-- <a title="Assignment" href="createAssignment.jsp">Create Assignment</a> -->
					</center>
					<br/>
				</f:verbatim>
			</h:form>
		</f:view>
	</body>
</html>