package br.hss.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
		br.hss.test.cache.CacheTest.class,
		br.hss.test.ds.DataSourceTest.class
	})
public class RunTests {

}
