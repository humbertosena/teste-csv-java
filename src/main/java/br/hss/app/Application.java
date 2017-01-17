package br.hss.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import br.hss.app.cache.JCache;
import br.hss.app.cache.JCacheFactory;
import br.hss.app.ds.DataSource;
import br.hss.app.ds.DataSourceFactory;
import br.hss.app.ds.ResultSet;
import br.hss.app.ds.ResultSetMetaData;
import br.hss.app.view.CommandUI;
import br.hss.app.view.CommandUIFactory;

public class Application {
	
	public static String PROP_FILE_PROPERTIES_CONFIG = "config";
	public static String VALUE_FILE_PROPERTIES_DEFAULT = "/application.properties";
	
	public static Properties properties = new Properties();
	
	public static void defaultProperties() {
		if (!Application.properties.containsKey(PROP_FILE_PROPERTIES_CONFIG))
			Application.properties.setProperty(PROP_FILE_PROPERTIES_CONFIG, VALUE_FILE_PROPERTIES_DEFAULT);
		
	}
	
	public static void loadProperties() {
		defaultProperties();
		
		Properties propDefault = new Properties();
		try {
			InputStream in;
			if (Application.properties.getProperty(PROP_FILE_PROPERTIES_CONFIG).equals(VALUE_FILE_PROPERTIES_DEFAULT))
				in = Application.class.getResourceAsStream(Application.properties.getProperty(PROP_FILE_PROPERTIES_CONFIG));
			else
				in = new FileInputStream(Application.properties.getProperty(PROP_FILE_PROPERTIES_CONFIG));
			
			propDefault.load(in);
		} catch (IOException e) {
			System.out.print("Não foi possível carregar o arquivo de propriedades: ");
			System.out.println(Application.properties.getProperty(PROP_FILE_PROPERTIES_CONFIG));
			System.out.println("Utilizando as configurações padrões da aplicação.");
		}
		
		Iterator<String> list = propDefault.stringPropertyNames().iterator();
		
		while (list.hasNext()) {
			String key = list.next();
			if (!Application.properties.containsKey(key))
				Application.properties.setProperty(key, propDefault.getProperty(key));
		}
		
	}
	
	
	private JCache cache = null;
	
	public Application() {
		this.cache = JCacheFactory.get();
	}
	
	public void execute() {
		
		DataSource dataSource = null;
		try {
			dataSource = loadDataSource();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		
		createCached(dataSource);
		
		dataSource.closed();
		
		readCommand();

	}
	
	
	private DataSource loadDataSource() throws Exception {
		DataSource dataSource = DataSourceFactory.dataSource(Application.properties);

		dataSource.loadProperties(Application.properties);
		dataSource.loaderData();			
		
		return dataSource;
	}

	private void createCached(DataSource dataSource) {
		
		ResultSet rs = dataSource.getResultSet();
		ResultSetMetaData metaData = rs.getMetaData();
		
		String index;
		String key;
		String value;
		
		do {
			for (int i=0; i<metaData.getColumnCount(); i++) {
				index = metaData.getColumnName(i);
				key = rs.getString(index);
				value = rs.currentResult();
				this.cache.addIndexCache(index, key, value);
			}
		} while (rs.next());
	}


	private void readCommand() {
	
		CommandUI ui = CommandUIFactory.get();
		
		ui.setCache(this.cache);
		
		while (ui.hasNewCommand()) {
			// Aguarda comando...
		}
	}


	public static void main(String[] args) {		
		
		for (String arg : args) {
			String[] values = arg.split("=");
			if (values.length == 2) {
				Application.properties.setProperty(values[0], values[1]);
			} else {
				System.out.println("O argumento '" + arg + "' é inválido, encerrando aplicação.");
				return;
			}
		}

		Application.loadProperties();
		
		Application app = new Application();
		
		app.execute();
		
	}

}
