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
	<center>
		<h:panelGrid rendered="#{!empty userService.userAssignmentTables}">
			<t:messages layout="list" showDetail="true" showSummary="false" style="color: #FF0000;"/>
				<h:form>
				<h:panelGrid style="background-color: Beige; border: 3px solid; padding:30px;">
					<f:facet name="header">
			    		<h:outputText value="Select Assignment Table"/>
			 		 </f:facet>
			 		<br/>
			 		<h:panelGrid columns="2">
				 		 <h:outputText value="#{msg.selectTable}" />
							<h:selectOneMenu value="#{assignmentService.selectedTable}">
								<f:selectItems value="#{userService.userAssignmentTables}" var="userTable" itemLabel="#{userTable.tableName}" itemValue="#{userTable.tableName}"/>
						</h:selectOneMenu>
					</h:panelGrid>
						
					<h:panelGrid columns="2">
						<h:outputLabel value="#{msg.assignmentName}" />
						<h:selectOneMenu value="#{assignment.assignmentName}">
							<f:selectItems value="#{ddList.assignments}" var="assignment" itemValue="#{assignment}" itemLabel="#{assignment}" />
						</h:selectOneMenu>
					</h:panelGrid>
					
					<h:panelGrid columns="2">
						<h:outputLabel value="#{msg.QuestionCount}" />
							<h:inputText value="#{assignment.questionCount}" required="true" size="15" maxlength="15" />
					</h:panelGrid>
					
					<h:panelGrid columns="2">
						<h:outputText value="" />
						<h:commandButton value="Select Table" action="#{assignmentService.setAssignmentTable}"/>
					</h:panelGrid>
			 	</h:panelGrid>
					<br/>
				</h:form>
			</h:panelGrid>
			
			<h:panelGrid rendered="#{empty userService.userAssignmentTables}">
				<h:outputText value="#{msg.noTable}"/>
			</h:panelGrid>
		</center>
			
			<h:form rendered="#{assignmentService.renderSet}">
				<h:panelGrid style="background-color: Beige; border: 3px solid; padding:30px;">
				
		 		 <h:panelGrid columns="8" >
					
					<t:dataTable value="#{assignment.questions}" var="question" border="1" cellspacing="0" cellpadding="1" width="800">
		
						<h:column>
							<f:facet name="header">
								<h:outputText value="#{msg['QuestionNumber']}" style="font-size: 20px; font-weight: bold" />
							</f:facet>
							<h:outputText value="#{question.questionID}" style="font-size: 20px; font-weight: bold" />
						</h:column>
						
						<h:column>
							<f:facet name="header">
								<h:outputText value="#{msg['QuestionColName']}" style="font-size: 20px; font-weight: bold" />
							</f:facet>
							<h:outputText value="#{question.quesColName}" style="font-size: 20px; font-weight: bold" />
							<h:selectOneMenu value="#{question.quesColName}">
								<f:selectItems value="#{assignmentTable.tableColumns}" var="tableColumn" itemValue="#{tableColumn.colName}" itemLabel="#{tableColumn.colName}" />
							</h:selectOneMenu>
						</h:column>
						
						<h:column>
							<f:facet name="header">
								<h:outputText value="#{msg['QuestionAnalysisType']}" style="font-size: 20px; font-weight: bold" />
							</f:facet>
							<h:outputText value="#{question.analysisType}" style="font-size: 20px; font-weight: bold" />
							<h:selectOneMenu value="#{question.analysisType}">
								<f:selectItems value="#{ddList.analysisTypes}" var="analysisType" itemValue="#{analysisType}" itemLabel="#{analysisType}" />
							</h:selectOneMenu>
						</h:column>
						
						<h:column>
							<f:facet name="header">
								<h:outputText value="#{msg['QuestionPoint']}" style="font-size: 20px; font-weight: bold" />
							</f:facet>
							<h:selectOneMenu value="#{question.point}">
								<f:selectItems value="#{ddList.points}" var="point" itemValue="#{point}" itemLabel="#{point}" />
							</h:selectOneMenu>
						</h:column>
		
					</t:dataTable>
						
				</h:panelGrid>
				<br/>
				<br/>
			
			<br/>
		 		<center>
			 		<h:panelGrid columns="2">
			 				<h:commandButton value="#{msg.create}" action="#{assignmentService.createAssignmentTable}"/>
							<h:commandButton value="Cancel" action="home.jsp" />
					</h:panelGrid>
				</center>
				
			</h:panelGrid>
			</h:form>	
		
</f:view>
</body>
</html>