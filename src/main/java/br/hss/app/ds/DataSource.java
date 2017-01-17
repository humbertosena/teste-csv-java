package br.hss.app.ds;

import java.util.Properties;

public interface DataSource {
	
	public void loadProperties(Properties prop);
	
	public void loaderData();
	
	public ResultSetMetaData getMetaData();
	
	public ResultSet getResultSet();
	
	public void closed();
	
}
