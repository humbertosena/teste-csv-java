package br.hss.app.ds.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import br.hss.app.ds.DataSource;
import br.hss.app.ds.DataSourceProperties;
import br.hss.app.ds.ResultSet;
import br.hss.app.ds.ResultSetMetaData;

public class DataSourceFileCSV implements DataSource {
	
	public static final String DEFAULT_SEPARATOR = ",";
	public static final boolean DEFAULT_HEADERLINE = false;
	public static final String DEFAULT_LABELCOLUMN = "column_";
	
	private Properties properties = null;
	private BufferedReader file = null;
	private ResultSetMetaDataFileCSV metaData = null;
	
	@Override
	public void loadProperties(Properties prop) {
		if (prop.contains(DataSourceProperties.APP_DATA_SOURCE_DRIVE.getKey()))
			prop.setProperty(DataSourceProperties.APP_DATA_SOURCE_DRIVE.getKey(), DataSourceProperties.APP_DATA_SOURCE_DRIVE.getValueDefault());
		
		if (prop.contains(DataSourceProperties.APP_FILE_SOURCE.getKey()))
			prop.setProperty(DataSourceProperties.APP_FILE_SOURCE.getKey(), DataSourceProperties.APP_FILE_SOURCE.getValueDefault());
		
		if(prop.contains(DataSourceProperties.APP_HEADER_LINE.getKey()))
			prop.setProperty(DataSourceProperties.APP_HEADER_LINE.getKey(), DataSourceProperties.APP_HEADER_LINE.getValueDefault());
		
		this.properties = prop;
	}

	@Override
	public ResultSetMetaData getMetaData() {
		return this.metaData;
	}

	@Override
	public ResultSet getResultSet() {
		return new ResultSetFileCSV(this, hasHeaderLineProperty());
	}
	
	@Override
	public void closed() {
		if (this.file != null)
			try {
				this.file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		this.file = null;
	}	

	@Override
	public void loaderData() {
		this.closed();
		
		try {
			InputStream in;
			if (isDefaultDataSource())
				in = DataSourceFileCSV.class.getResourceAsStream(getFileSourceProperty());
			else
				in = new FileInputStream(getFileSourceProperty());	
			this.file = new BufferedReader(new InputStreamReader(in));
		} catch (Exception e) {
			System.out.print("Não foi possível carregar o arquivo fonte de dados: ");
			System.out.println(getFileSourceProperty());
		}				
		
		loadHeaders();
	}
	
	protected BufferedReader getDataSource() {
		return this.file;
	}
	
	
	//** private methods **//
	private boolean isDefaultDataSource() {
		return DataSourceProperties.APP_FILE_SOURCE.getValueDefault().equals(this.getFileSourceProperty());
	}	
	
	private String getFileSourceProperty() {
		return this.properties.getProperty(DataSourceProperties.APP_FILE_SOURCE.getKey());
	}
	
	private boolean hasHeaderLineProperty() {
		String value = this.properties.getProperty(DataSourceProperties.APP_HEADER_LINE.getKey());
		
		if (value != null)
			return Boolean.valueOf(value);
		else 
			return DEFAULT_HEADERLINE;
	}
	
	private void loadHeaders() {
		String firstLine = getFirstLine();
		
		String[] columns = firstLine.split(DEFAULT_SEPARATOR);
		
		this.metaData = new ResultSetMetaDataFileCSV();
		for (int index = 0; index < columns.length; index++ ) {
			if (hasHeaderLineProperty())
				this.metaData.add(columns[index]);
			else
				this.metaData.add(DEFAULT_LABELCOLUMN + Integer.toString(index));
		}
		
	}
	
	private String getFirstLine() {
		
		String sCurrentLine = null;
		
		try {
			sCurrentLine = this.file.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sCurrentLine;
	}
	
	
}
