package br.hss.test.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.Test;

import br.hss.app.ds.DataSource;
import br.hss.app.ds.DataSourceFactory;
import br.hss.app.ds.DataSourceProperties;
import br.hss.app.ds.csv.DataSourceFileCSV;

public class DataSourceTest {

	@Test
	public void testDataSourceFactoryExceptionWithNullProperty() {
		try {
			DataSourceFactory.dataSource(null);
			fail("Exception não lançada.");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testDataSourceFactoryExceptionWithIncorrectProperty() {
		try {
			DataSourceFactory.dataSource(null);
			fail("Exception não lançada.");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testDataSourceFileCSV() {
		String nameClass = DataSourceFileCSV.class.getName();
		Properties prop = new Properties();
		prop.setProperty(DataSourceProperties.APP_DATA_SOURCE_DRIVE.getKey(), nameClass);
		
		try {
			DataSource ds = DataSourceFactory.dataSource(prop);
			assertEquals(DataSourceFileCSV.class, ds.getClass());
		} catch (Exception e) {
			fail("Não carregou a classe: " + nameClass + " : " + e.getMessage());
		}
	}
	
	

}
