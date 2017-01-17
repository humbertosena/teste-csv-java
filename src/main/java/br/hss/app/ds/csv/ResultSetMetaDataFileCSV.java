package br.hss.app.ds.csv;

import java.util.ArrayList;
import java.util.Iterator;

import br.hss.app.ds.ResultSetMetaData;

public class ResultSetMetaDataFileCSV implements ResultSetMetaData {
	
	private ArrayList<String> metaData;
	
	protected ResultSetMetaDataFileCSV() {
		this.metaData = new ArrayList<>();
	}

	protected void add(String column) {
		this.metaData.add(column);
	}
	
	@Override
	public int getColumnCount() {
		return this.metaData.size();
	}	

	@Override
	public String getColumnName(int column) {
		// TODO Implementar exception referente a indice fora do suportado.
		return this.metaData.get(column);
	}

	@Override
	public Iterator<String> getColumns() {
		return this.metaData.iterator();
	}
	
	@Override
	public int findColumn(String columnName) {
		return this.metaData.indexOf(columnName);
	}

}
