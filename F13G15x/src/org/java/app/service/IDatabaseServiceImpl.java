package org.java.app.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.java.app.interfaces.IDatabaseService;

public class IDatabaseServiceImpl implements IDatabaseService {

	@Override
	public void connect(String serverName, String dbName, String userName,
			String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createTable(String tableName, Map<String, String> columnInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTable(String tableName, List<String> columnMetaList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertRecord(String tableName, HashMap<String, Object> map) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet executeQuery(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement createPrepareStatement(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commitTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object> getColumnData(String dbTableName, int rowCount,
			List<Object> columnList) {
		// TODO Auto-generated method stub
		return null;
	}

}
