package br.hss.app.view;

import br.hss.app.cache.JCache;

public interface CommandUI {
	
	public void setCache(JCache cache);
	
	public boolean hasNewCommand();

}
