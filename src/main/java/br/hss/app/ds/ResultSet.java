package br.hss.app.ds;

public interface ResultSet {
	
	public String getString(String columnName);
	
	public String getString(int columnIndex);
	
	public String currentResult();
	
	public boolean next();
	
	public ResultSetMetaData getMetaData();

}
