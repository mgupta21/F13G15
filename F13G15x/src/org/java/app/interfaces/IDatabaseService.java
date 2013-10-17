package org.java.app.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDatabaseService {

	public void connect(String serverName, String dbName, String userName, String password);

	public void disconnect();
	
	public boolean isConnected();

	public void createTable(String tableName, Map<String, String> columnInfo);
	
	public void createTable(String tableName, List<String> columnMetaList);
		
	public void insertRecord(String tableName, HashMap<String, Object> map);
	
	public ResultSet executeQuery(String queryString);

	public PreparedStatement createPrepareStatement(String query);

	public void commitTransaction();
	
	public List<Object> getColumnData(String dbTableName, int rowCount, List<Object> columnList);
	
}