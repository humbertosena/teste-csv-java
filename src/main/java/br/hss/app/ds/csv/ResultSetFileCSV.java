package br.hss.app.ds.csv;

import java.io.BufferedReader;
import java.io.IOException;

import br.hss.app.ds.ResultSet;
import br.hss.app.ds.ResultSetMetaData;

public class ResultSetFileCSV implements ResultSet {
	
	private ResultSetMetaData metaData = null;
	private BufferedReader sourceData = null;
	private String sCurrentData[] = null;
	private String sCurrentLine = null;
	
	protected ResultSetFileCSV(DataSourceFileCSV dataSource, boolean hasHeaderInFile) {
		this.metaData = dataSource.getMetaData();
		
		this.sourceData = dataSource.getDataSource();
		
		try {
			this.sCurrentLine = this.sourceData.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.sCurrentData = this.sCurrentLine.split(DataSourceFileCSV.DEFAULT_SEPARATOR);
	}

	@Override
	public ResultSetMetaData getMetaData() {
		return this.metaData;
	}

	@Override
	public String getString(int columnIndex) {
		return this.sCurrentData[columnIndex];
	}

	@Override
	public String getString(String columnName) {
		int index = this.metaData.findColumn(columnName);
		return this.sCurrentData[index];
	}

	@Override
	public boolean next() {
		try {
			this.sCurrentLine = this.sourceData.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		if (this.sCurrentLine != null)
			this.sCurrentData = this.sCurrentLine.split(DataSourceFileCSV.DEFAULT_SEPARATOR);
		else 
			this.sCurrentData = null;
		
		
		return this.sCurrentData != null;
	}

	@Override
	public String currentResult() {
		return this.sCurrentLine;
	}

}
