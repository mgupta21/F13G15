package org.java.app.interfaces;

import java.util.HashMap;
import java.util.List;

import org.java.app.exceptions.DuplicateTableException;
import org.java.app.exceptions.InsertionException;

public interface IDatabaseService {

	public class TableColumnMeta{
		
		public String tableColName;
		public String colDataType;
		public boolean isAutoincrement;
		public boolean isNotNull;
		public boolean isPrimaryKey;
		public boolean isDefault;
		
	}

	public void createDBTable(String dbTableName, List<TableColumnMeta> colMetaList) throws DuplicateTableException;
	
	void updateUploadedTable(String query) throws InsertionException;

	public void updateTableMapper(String uSER_TABLE_MAPPER, HashMap<String, Object> data);

}