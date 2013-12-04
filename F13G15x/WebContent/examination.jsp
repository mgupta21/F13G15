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
<title>Examination</title>
</head>
	<body>
		<f:view>
		<f:loadBundle basename="messages" var="msg" />
			<f:verbatim>
				<center>
					<h3>Welcome, Best of Luck..!! </h3>
				</center>
				<br />
				<center><a title="Home" href="home.jsp">Home</a></center>
				<hr />
			</f:verbatim>
			<h:form>
				<center>
					<h3>Assignment Table</h3>
					<h:panelGrid columns="2" >
							<t:dataTable value="#{assignmentService.result}" var="row" border="1" cellspacing="0" cellpadding="1" width="800">
								<t:columns var="col" value="#{assignmentService.columnNames}">
									<f:facet name="header">
										<t:outputText value="#{col}"></t:outputText>
									</f:facet>
									<t:outputText value="#{row[col]}" />
								</t:columns>
							</t:dataTable>
					</h:panelGrid>
				</center>
				
				<center>
					<h:panelGrid rendered="#{loginService.admin}">
						<h3>Admin Cheat Sheet</h3>
						<h:panelGrid columns="2" >
								<t:dataTable value="#{assignmentService.resultE}" var="row" border="1" cellspacing="0" cellpadding="1" width="800">
									<t:columns var="col" value="#{assignmentService.columnNamesE}">
										<f:facet name="header">
											<t:outputText value="#{col}"></t:outputText>
										</f:facet>
										<t:outputText value="#{row[col]}" />
									</t:columns>
								</t:dataTable>
						</h:panelGrid>
					</h:panelGrid>
				</center>
				
				<center>
					<h3>Examination</h3>
					<h:panelGrid columns="2" >
						<t:dataTable value="#{assignmentSolved.questions}" var="question" styleClass="order-table" border="1" cellspacing="0" cellpadding="1" width="800">
	
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{msg['QuestionNumber']}" style="font-size: 20px; font-weight: bold" />
								</f:facet>
								<h:outputLabel value="#{question.questionID}" style="font-size: 20px; font-weight: bold" />
							</h:column>
							
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{msg['QuestionDesc']}" style="font-size: 20px; font-weight: bold" />
								</f:facet>
								<h:outputLabel value="#{question.quesDesc}" style="font-size: 20px; font-weight: bold" />
							</h:column>
							
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{msg['QuestionPoint']}" style="font-size: 20px; font-weight: bold" />
								</f:facet>
								<h:outputLabel value="#{question.point}" style="font-size: 20px; font-weight: bold" />
							</h:column>
							
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{msg['QuestionAnswer']}" style="font-size: 20px; font-weight: bold" />
								</f:facet>
								<h:inputText value="#{question.studentAnswer}" style="font-size: 20px; font-weight: bold" />
							</h:column>
					
						</t:dataTable>
								
						<center>
					 		<h:panelGrid columns="2">
					 				<h:commandButton value="#{msg.submit}" action="#{assignmentService.submitExam}"/>
									<h:commandButton value="Cancel" action="home.jsp" />
							</h:panelGrid>
						</center>		
											
					</h:panelGrid>
				</center>
				
				
			</h:form>	
		</f:view>
	</body>
</html>