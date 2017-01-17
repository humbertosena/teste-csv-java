package br.hss.app.cache.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.hss.app.cache.JCache;

public class JCacheMemory implements JCache {
	
	
	private HashMap<Integer, String> values;
	private HashMap<String, HashMap<String,RegisterCache>> indexCache;
	private List<String> indexKey;
	
	
	public JCacheMemory() {
		this.values = new HashMap<Integer,String>();
		this.indexCache = new HashMap<String,HashMap<String,RegisterCache>>();
		this.indexKey = new ArrayList<>();
	}

	

	@Override
	public void createIndex(String index) {
		if (!this.indexCache.containsKey(index.toLowerCase())) {
			this.indexCache.put(index.toLowerCase(), new HashMap<String,RegisterCache>());
			this.indexKey.add(index);
		}
	}

	@Override
	public void addIndexCache(String index, String key, String value) {
		createIndex(index);
		
		int hashCode = value.hashCode();
		if (!this.values.containsKey(hashCode)) {
			this.values.put(hashCode, value);
		}
		
		Map<String, RegisterCache> cache = this.indexCache.get(index);
		RegisterCache register = null;
		if (!cache.containsKey(key)) 
			cache.put(key, new RegisterCache(key));
		
		register = cache.get(key);
		register.add(hashCode);
	}
	

	@Override
	public int count() {
		return this.values.size();
	}

	@Override
	public int count(String index) {

		if (this.indexCache.containsKey(index.toLowerCase()))
			return this.indexCache.get(index.toLowerCase()).size();
		else
			return -1;
	}

	@Override
	public Iterator<String> values(String index, String key) {
		ArrayList<String> _values = new ArrayList<String>();
		
		if (this.indexCache.containsKey(index.toLowerCase())) {
			HashMap<String, RegisterCache> registers = this.indexCache.get(index.toLowerCase());
			
			if (registers.containsKey(key)) {
				RegisterCache register = registers.get(key);
				
				Iterator<Integer> list = this.values.keySet().iterator();
				Integer hashCode = null;
				while (list.hasNext()) {
					hashCode = list.next();
					if (register.containsValue(hashCode))
						_values.add(this.values.get(hashCode));
				}				
			}
			
		}
		
		
		return _values.iterator();
	}



	@Override
	public List<String> keys() {
		return this.indexKey;
	}
	
	

}
