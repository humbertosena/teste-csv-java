package br.hss.app.cache.memory;

import java.util.ArrayList;
import java.util.List;

class RegisterCache {
	
	private String id;
	private List<Integer> values;
	
	public RegisterCache(String id) {
		this.id = id;
		this.values = new ArrayList<Integer>();
	}
	
	public String getId() {
		return id;
	}

	public void add(Integer hashCode) {
		this.values.add(hashCode);
	}
	
	public int count() {
		return this.values.size();
	}
	
	public boolean containsValue(Integer hashCode) {
		return this.values.contains(hashCode);
	}

}
