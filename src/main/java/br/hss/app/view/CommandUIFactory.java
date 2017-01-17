package br.hss.app.view;

import br.hss.app.view.console.ConsoleUI;

public abstract class CommandUIFactory {
	
	public static CommandUI get() {
		return new ConsoleUI();
	}

}
