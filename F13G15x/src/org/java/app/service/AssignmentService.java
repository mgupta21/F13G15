package org.java.app.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.java.app.common.Constants;
import org.java.app.model.Assignment;
import org.java.app.model.AssignmentSolved;
import org.java.app.model.AssignmentTable;
import org.java.app.model.Question;
import org.java.app.model.TableColumn;

@ManagedBean(name="assignmentService")
@SessionScoped
public class AssignmentService {
	
	@ManagedProperty(value = "#{assignmentTable}")
	private AssignmentTable assignmentTable;
	
	@ManagedProperty(value = "#{assignment}")
	private Assignment assignment;
	
	@ManagedProperty(value = "#{assignmentSolved}")
	private AssignmentSolved assignmentSolved;
	
	String selectedTable;
	private boolean renderSet = false;
	private boolean assignmentRendrer = false;
	
	String examSelected;
	
	@ManagedProperty(value = "#{dbService}")
	DatabaseServiceImpl dbService;
	
	@ManagedProperty(value = "#{userService}")
	UserService userService;
	
	Result result;
	private List<String> columnNames;
	
	Result resultE;
	private List<String> columnNamesE;
	
	@ManagedProperty(value="#{rosterService}")
	private RosterService rosterService;
	
	public String setAssignmentTable(){
		
		boolean isNew = dbService.validateAssignmentName(assignment.getAssignmentName(), userService.getUserData().getUserProfile().getCourse());
		
		String tableName = userService.getUserData().getUserLogin().getUserName() + "_" + selectedTable + "_" + Constants.TABLE_TYPES.ASSIGNMENT_TABLE; 
		List<TableColumn> columns = dbService.getColumnsByTableName(tableName);
		
		boolean isValid = inputValidation(isNew, columns);
		if(!isValid){
			return "Error";
		}
		
		assignmentTable.setSourceTableName(tableName);
		assignmentTable.setAssignmentName(assignment.getAssignmentName());
		assignmentTable.setProfessorName(userService.getUserData().getUserLogin().getUserName());
		assignmentTable.setCourse(userService.getUserData().getUserProfile().getCourse());
		assignmentTable.setTableColumns(columns);
		
		assignment.setAssignmentTable(assignmentTable);
		assignment.setQuestions();
		
		this.renderSet = true;
		return "TableSet";
		
	}
	
	public boolean inputValidation(boolean isNew, List<TableColumn> columns){
		
		if(!isNew){
			String err = "Assignment: " + assignment.getAssignmentName() + " is already created for Course: " + userService.getUserData().getUserProfile().getCourse();
			getFacesMessage(FacesMessage.SEVERITY_ERROR, err);
			this.renderSet = false;
			return false ;
		}
		if(columns.size()<1){
			getFacesMessage(FacesMessage.SEVERITY_ERROR, "Warning: Selected Table Doesn't have any numeric Columns");
			this.renderSet = false;
			return false;
		}
		/*Object x = assignment.getQuestionCount();
		if((Integer.parseInt(x))){
			getFacesMessage(FacesMessage.SEVERITY_ERROR, "Number of Questions must be a number");
			this.renderSet = false;
			return false;
		}*/
		if((assignment.getQuestionCount()>100 || assignment.getQuestionCount()<1)){
			getFacesMessage(FacesMessage.SEVERITY_ERROR, "Number of Questions must be a number in range 1 to 100");
			this.renderSet = false;
			return false;
		}
		return true;
	}
	
	public void setSolvedQuestion(){
		String quesTable = assignmentTable.getAssignmentName() + "_" + assignmentTable.getProfessorName() + "_" + assignmentTable.getCourse();
		List<Question> questions = dbService.getQuestionSet(quesTable);
		assignmentSolved.setQuestionsFromDB(questions);
	}
	
	public String createAssignmentTable(){
		
		dbService.uploadAssignment(assignment);
		alotAssignment();
		mapAssignmentWithTable();
		this.renderSet = false;
		this.assignment = new Assignment(); // Reset the assignment
		return "AssignmentCreated";
		
	}
	
	private void mapAssignmentWithTable() {
		dbService.tableAssignmentMap(assignment);
	}

	public void alotAssignment(){
		dbService.alotAssignmentToUser(assignment);
	}
	
	public String takeExamination(){
		assignmentTable = dbService.getAssignmentTable(examSelected, userService.getUserData().getUserProfile().getCourse());
		return "Success";
		
	}
	
	public void getSourceAssignmentTable(){
		assignmentTable = dbService.getAssignmentTable(examSelected, userService.getUserData().getUserProfile().getCourse());
		ResultSet rs = dbService.getSourceTable(assignmentTable.getSourceTableName());
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			result = ResultSupport.toResult(rs);
			int numberColumns = rsmd.getColumnCount();
			int numberRows = result.getRowCount();
			String columnNameList [] = result.getColumnNames();
			columnNames = new ArrayList<String>();
			for(int i=0; i<numberColumns;i++){
				if(i!=0){
					columnNames.add(columnNameList[i]);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/*// get Assignment Table for the current exam selected based on student course
	public AssignmentTable getSAssignmentTable(){
		return dbService.getAssignmentTable(examSelected, userService.getUserData().getUserProfile().getCourse());
	}*/
	
	public void getExamination(){
		String tableName = assignmentTable.getAssignmentName() + "_" + assignmentTable.getProfessorName() + "_" + assignmentTable.getCourse();
		ResultSet rs = dbService.getSourceTable(tableName);
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			resultE = ResultSupport.toResult(rs);
			int numberColumns = rsmd.getColumnCount();
			int numberRows = resultE.getRowCount();
			String columnNameList [] = resultE.getColumnNames();
			columnNamesE = new ArrayList<String>();
			for(int i=0; i<numberColumns;i++){
				columnNamesE.add(columnNameList[i]);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public String submitExam(){
		int examScore = assignmentSolved.verifySolution();
		dbService.updateStudentAssignment(userService.getUserData().getUserLogin().getUserName(), this.examSelected, examScore);
		rosterService.getRosterManager().setExamSelected(examSelected);
		rosterService.getRosterManager().setScore(examScore);
		rosterService.updateRoster();
		return "ExamSubmitted";
	}
	
	public void getFacesMessage(Severity severity, String message){
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage(severity, message, null));
	}
	
/*	public String getRoster(){
		
		System.out.println("In Get Roster");
		
		String rosterTableName = rosterTable.getProfessorName() + "_" + rosterTable.getCourse();
		ResultSet rs = dbService.getSourceTable(rosterTableName);
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			result = ResultSupport.toResult(rs);
			int numberColumns = rsmd.getColumnCount();
			int numberRows = result.getRowCount();
			String columnNameList [] = result.getColumnNames();
			columnNames = new ArrayList<String>();
			for(int i=0; i<numberColumns;i++){
				columnNames.add(columnNameList[i]);
			}
			
			setRosterTable();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return "ViewRoster";
		
	}
	
	// Professor name should be set from DB to make it work in case of Admin login as well
	public void setRosterTable(){
		String UserName = dbService.getProfessorName(userService.getUserData().getUserProfile().getCourse());
		rosterTable.setProfessorName(UserName);
		rosterTable.setCourse(userService.getUserData().getUserProfile().getCourse());
	}*/
	
	public boolean isRenderSet() {
		return renderSet;
	}

	public void setRenderSet(boolean renderSet) {
		this.renderSet = renderSet;
	}

	public AssignmentTable getAssignmentTable() {
		return assignmentTable;
	}

	public void setAssignmentTable(AssignmentTable assignmentTable) {
		this.assignmentTable = assignmentTable;
	}

	public String getSelectedTable() {
		return selectedTable;
	}

	public void setSelectedTable(String selectedTable) {
		this.selectedTable = selectedTable;
	}

	public DatabaseServiceImpl getDbService() {
		return dbService;
	}

	public void setDbService(DatabaseServiceImpl dbService) {
		this.dbService = dbService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public String getExamSelected() {
		return examSelected;
	}

	public void setExamSelected(String examSelected) {
		this.examSelected = examSelected;
		getSourceAssignmentTable();
		getExamination();
		setSolvedQuestion();
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public Result getResultE() {
		return resultE;
	}

	public void setResultE(Result resultE) {
		this.resultE = resultE;
	}

	public List<String> getColumnNamesE() {
		return columnNamesE;
	}

	public void setColumnNamesE(List<String> columnNamesE) {
		this.columnNamesE = columnNamesE;
	}

	public AssignmentSolved getAssignmentSolved() {
		return assignmentSolved;
	}

	public void setAssignmentSolved(AssignmentSolved assignmentSolved) {
		this.assignmentSolved = assignmentSolved;
	}

	public RosterService getRosterService() {
		return rosterService;
	}

	public void setRosterService(RosterService rosterService) {
		this.rosterService = rosterService;
	}

	public boolean isAssignmentRendrer() {
		return assignmentRendrer;
	}

	public void setAssignmentRendrer(boolean assignmentRendrer) {
		this.assignmentRendrer = assignmentRendrer;
	}

}
