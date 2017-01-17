package br.hss.app.cache;

import br.hss.app.cache.memory.JCacheMemory;

public abstract class JCacheFactory {
	
	public static JCache get() {
		return new JCacheMemory();
	}

}
