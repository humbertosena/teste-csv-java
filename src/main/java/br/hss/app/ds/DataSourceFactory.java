package br.hss.app.ds;

import java.util.Properties;

public abstract class DataSourceFactory {
	
	@SuppressWarnings("unchecked")
	public static DataSource dataSource(Properties prop) throws Exception {
		if (!prop.contains(DataSourceProperties.APP_DATA_SOURCE_DRIVE.getKey()))
			prop.setProperty(DataSourceProperties.APP_DATA_SOURCE_DRIVE.getKey(), DataSourceProperties.APP_DATA_SOURCE_DRIVE.getValueDefault());
		
		try {
			Class<DataSource> clazz = (Class<DataSource>) Class.forName(prop.getProperty(DataSourceProperties.APP_DATA_SOURCE_DRIVE.getKey()));
			return clazz.newInstance();
			
		} catch (Exception e1) {
			throw new Exception("N�o foi poss�vel carregar o DataSource definido, verifique as configura��es.", e1.getCause());
		}	
	}
	
}