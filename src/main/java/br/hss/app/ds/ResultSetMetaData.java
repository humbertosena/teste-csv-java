package br.hss.app.ds;

import java.util.Iterator;

public interface ResultSetMetaData {
	
	public String getColumnName(int column);
	
	public int findColumn(String columnName);
	
	public int getColumnCount();
	
	public Iterator<String> getColumns();

}
