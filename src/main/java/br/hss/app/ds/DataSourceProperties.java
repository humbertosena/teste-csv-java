package br.hss.app.ds;

public enum DataSourceProperties {
	
	APP_DATA_SOURCE_DRIVE ("app.dataSourceDrive","br.hss.app.ds.csv.DataSourceFileCSV"),
	APP_FILE_SOURCE ("app.fileSource","/cidades.csv"),
	APP_HEADER_LINE ("app.headerLine","false");

	private String key;
	private String value;
	
	DataSourceProperties(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getValueDefault() {
		return this.value;
	}
	
	public String getKey() {
		return this.key;
	}
	
}
