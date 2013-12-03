package org.java.app.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.java.app.exceptions.CorruptFileException;
import org.java.app.exceptions.DuplicateTableException;

import au.com.bytecode.opencsv.CSVReader;

public class CSVUploader {

	public static TableColumn[] getColumns(InputStream inputStream) throws CorruptFileException {

		// Input Stream must be CSV
		if (inputStream == null) {
			throw new RuntimeException("Empty Input Stream");
		}

		TableColumn[] columns = null;
		CSVReader reader = null;

		String[] headers;
		String[] firstDataRow;

		try {

			reader = new CSVReader(new InputStreamReader(inputStream));

			headers = reader.readNext();

			firstDataRow = reader.readNext();

			int columnCount = headers.length;

			columns = new TableColumn[columnCount];

			for (int i = 0; i < columnCount; i++) {
				columns[i] = createTableColumn(headers[i], firstDataRow[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CorruptFileException("Error While uploading table. Either duplicate table exist in database for this user or more than one table column had same name");
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

		return columns;
	}

	private static TableColumn createTableColumn(String header, String data)
			throws Exception {

		if (data == null || header == null) {
			throw new Exception("Either Data or Header is Empty");
		}

		TableColumn column = new TableColumn();

		column.setColName(header.replaceAll(" ", "").replaceAll("[^a-zA-Z0-9]", "0"));

		if (data.isEmpty()) {
			column.setColDataType(ColumnDataType.Undefined);
			return column;
		}

		if (TryParseInt(data)) {
			column.setColDataType(ColumnDataType.Integer);
		} else if (TryParseFloat(data)) {
			column.setColDataType(ColumnDataType.Double);
		} else {
			column.setColDataType(ColumnDataType.String);
		}

		return column;
	}

	private static boolean TryParseInt(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private static boolean TryParseFloat(String str) {
		try {
			Float.parseFloat(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
