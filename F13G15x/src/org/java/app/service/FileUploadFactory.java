package org.java.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.java.app.common.Constants;
import org.java.app.exceptions.DuplicateTableException;
import org.java.app.exceptions.InsertionException;

import org.java.app.interfaces.IDatabaseService;
import org.java.app.interfaces.IDatabaseService.TableColumnMeta;
import org.java.app.model.ColumnDataType;
import org.java.app.model.TableColumn;

import au.com.bytecode.opencsv.CSVReader;

@ManagedBean(name="fileUploadFactory")
@SessionScoped
public class FileUploadFactory {
	
		private int rowCount;
		private String dbTableName;
	
		@ManagedProperty(value = "#{dbService}")
		private DatabaseServiceImpl dbService;
		
		@ManagedProperty(value = "#{userService}")
		private UserService userService;
		
		public void dataBaseUpload(String tableName, String tableType, TableColumn[] columns, InputStream dataStream) throws DuplicateTableException, InsertionException {
			
			// Creates DB Table 
			uploadDBTable(tableName, tableType, columns);
			
			// Insert Records in Uploaded Table
			saveDataInDB(dbTableName, dataStream);
			
			// Update User Table Map
			updateUserTableMap(tableName, tableType);
		}

		private void uploadDBTable(String tableName, String tableType, TableColumn[] columns) throws DuplicateTableException {

			this.dbTableName = createTableName(tableName, tableType);
			
			List<TableColumnMeta> colMetaList = new LinkedList<TableColumnMeta>();
			
			// Create Primary Key Column
			if(!tableType.equalsIgnoreCase(Constants.TABLE_TYPES.ROOSTER)){
				TableColumnMeta primaryKeyCol = createIndexColumnMeta();
				colMetaList.add(primaryKeyCol);
			}
			
			// User Uploaded Columns are non-primary
			for (TableColumn column : columns) {

				TableColumnMeta colMeta = new TableColumnMeta();
				colMeta.tableColName = column.getColName();
				colMeta.colDataType = column.getColDataType().getDBDataType();
				colMeta.isAutoincrement = false;
				colMeta.isNotNull = false;
				if(tableType.equalsIgnoreCase(Constants.TABLE_TYPES.ROOSTER)){
					if(column.getColName().equalsIgnoreCase(Constants.USER_TABLE_COLUMNS.USERNAME)){
						colMeta.isPrimaryKey = true;
					}
				}else{
					colMeta.isPrimaryKey = false;
				}
				colMetaList.add(colMeta);
			}
			
			// Creates DB Table
			dbService.createDBTable(dbTableName, colMetaList);
		}
		
		public String createTableName(String tableName, String tableType){
			
			if(tableType.equalsIgnoreCase(Constants.TABLE_TYPES.ROOSTER)){
				return userService.getUserData().getUserLogin().getUserName() + "_" + userService.getUserData().getUserProfile().getCourse();
			}
			return userService.getUserData().getUserLogin().getUserName() + "_" + tableName + "_"  + tableType;
		}
		
		private TableColumnMeta createIndexColumnMeta() {
			
			TableColumnMeta tColMeta = new TableColumnMeta();
			tColMeta.tableColName = Constants.INDEX_COLUMN_NAME;
			tColMeta.colDataType = ColumnDataType.Integer.getDBDataType();
			tColMeta.isAutoincrement = true;
			tColMeta.isNotNull = false;
			tColMeta.isPrimaryKey = true;
			
			return tColMeta;
		}

		private void saveDataInDB(String tableName, InputStream inputStream) throws InsertionException {

			CSVReader reader = null;
			String[] data = null;
			int rowCount = 0;

			try {

				reader = new CSVReader(new InputStreamReader(inputStream));

				StringBuffer query = new StringBuffer();

				query.append("INSERT INTO " + tableName);
				query.append(" (");
				query.append(getColumnHeaders(reader));
				query.append(") ");

				// Put Data Values
				data = reader.readNext();

				while (data != null) {
					if (rowCount != 0){
						query.append(" UNION ALL ");
					}

					query.append(" SELECT ");

					for (int i = 0; i < data.length; ++i) {
						String str = data[i];

						if (str.isEmpty()) {
							query.append("NULL");
						} else {
							query.append("'");
							query.append(data[i]);
							query.append("'");
						}

						if (i < data.length - 1) {
							query.append(",");
						}
					}

					data = reader.readNext();
					rowCount++;
				}

				if (rowCount != 0) {
					dbService.updateUploadedTable(query.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			finally {
				try {
					if (null != reader) {
						reader.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			this.rowCount = rowCount;
		}

		private String getColumnHeaders(CSVReader reader) throws IOException {

			StringBuffer buffer = new StringBuffer();
			String[] data = reader.readNext();

			for (int i = 0; i < data.length; ++i) {
				
				buffer.append(data[i].replaceAll(" ", "").replaceAll("[^a-zA-Z0-9]", "0"));

				if (i < data.length - 1){
					buffer.append(",");
				}
			}
			return buffer.toString();
		}

		private void updateUserTableMap(String tableName, String tableType) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			
			if(tableType.equalsIgnoreCase(Constants.TABLE_TYPES.ROOSTER)){
				map.put(Constants.PROFESSOR_ROSTER_COLUMNS.PROFESSORNAME, userService.getUserData().getUserLogin().getUserName());
				map.put(Constants.PROFESSOR_ROSTER_COLUMNS.COURSE, userService.getUserData().getUserProfile().getCourse());
				dbService.updateTableMapper(Constants.USER_DB_TABLES.PROFESSOR_ROSTER_MAP, map);
			}else{
				map.put(Constants.USER_TABLE_COLUMNS.USERNAME, userService.getUserData().getUserLogin().getUserName());
				map.put(Constants.USER_TABLE_COLUMNS.TABLENAME, tableName);
				map.put(Constants.USER_TABLE_COLUMNS.TABLETYPE, tableType);
				dbService.updateTableMapper(Constants.USER_DB_TABLES.USER_TABLE_MAPPER, map);
			}
		}

		public DatabaseServiceImpl getDbService() {
			return dbService;
		}

		public void setDbService(DatabaseServiceImpl dbService) {
			this.dbService = dbService;
		}

		public int getRowCount() {
			return rowCount;
		}

		public void setRowCount(int rowCount) {
			this.rowCount = rowCount;
		}

		public String getDbTableName() {
			return dbTableName;
		}

		public void setDbTableName(String dbTableName) {
			this.dbTableName = dbTableName;
		}

		public UserService getUserService() {
			return userService;
		}

		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		

}


