package br.hss.app.cache;

import java.util.Iterator;
import java.util.List;

public interface JCache {
	
	public void createIndex(String index);
	
	public void addIndexCache(String index, String key, String value);
	
	public int count();
	
	public int count(String index);
	
	public Iterator<String> values(String key, String value);
	
	public List<String> keys();

}
