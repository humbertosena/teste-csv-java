package br.hss.test.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.hss.app.cache.JCache;
import br.hss.app.cache.JCacheFactory;
import br.hss.app.cache.memory.JCacheMemory;

public class CacheTest {
	
	@Test
	public void testJCacheMemory() {
		
		try {
			JCache object = JCacheFactory.get();
			assertEquals(JCacheMemory.class, object.getClass());
		} catch (Exception e) {
			fail("Não carregou a classe: " + JCacheMemory.class.getName() + " : " + e.getMessage());
		}
	}

}
