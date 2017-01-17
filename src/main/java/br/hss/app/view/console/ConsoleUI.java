package br.hss.app.view.console;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import br.hss.app.cache.JCache;
import br.hss.app.view.CommandUI;

public class ConsoleUI implements CommandUI {
	
	public static String CMD_EXIT = "exit";
	
	private JCache cache = null;
	private Scanner console = null;
	
	public ConsoleUI() {
		this.console = new Scanner(System.in);
	}
	
	@Override
	public void setCache(JCache cache) {
		this.cache = cache;
	}	

	@Override
	public boolean hasNewCommand() {
		String line = this.console.nextLine();
		line = line.replaceAll("\\s+"," ");
		
		ConsoleUIEnum command = validaCommand(line);
		
		execute(command,command.argsCommand(line));
		
		return !isExitCommand(line);
	}

	private boolean isExitCommand(String command) {
		return CMD_EXIT.equals(command);
	}
	
	private ConsoleUIEnum validaCommand(String line) {		
		if (line.equals(ConsoleUIEnum.EXIT.getCommnad()))
			return ConsoleUIEnum.EXIT;
		else if (line.startsWith(ConsoleUIEnum.HELP.getCommnad()))
			return ConsoleUIEnum.HELP;
		else if (line.startsWith(ConsoleUIEnum.FILTER.getCommnad()))
			return ConsoleUIEnum.FILTER;
		else if (line.startsWith(ConsoleUIEnum.COUNT_DISTINCT.getCommnad()))
			return ConsoleUIEnum.COUNT_DISTINCT;
		else if (line.startsWith(ConsoleUIEnum.COUNT.getCommnad()))
			return ConsoleUIEnum.COUNT;
		else
			return ConsoleUIEnum.INVALID;
	}
	
	private void printHelp() {
		for (ConsoleUIEnum cmd : ConsoleUIEnum.values()) {
			System.out.println(cmd.getHelpText());
		}
	}
	
	private void execute(ConsoleUIEnum command, String[] args) {
		if (command == null) {
			printHelp();
			return;
		}
		
		switch (command) {
		case EXIT:
			System.out.println("Encerrando consulta.");
			break;
		case HELP:
			printHelp();
			break;
		case COUNT:
			System.out.println(this.cache.count());
			break;
		case COUNT_DISTINCT:
			System.out.println(this.cache.count(args[0]));
			break;
		case FILTER:
			List<String> keys = this.cache.keys();
			for (int i=0;i<keys.size();i++) {
				if (i==keys.size()-1)
					System.out.println(keys.get(i));
				else
					System.out.print(keys.get(i) + ",");
			}
			
			Iterator<String> list = this.cache.values(args[0], args[1]);
			while (list.hasNext())
				System.out.println(list.next());
			break;
		default:
			System.out.println("Comando inv�lido, digite 'help' para exibir os comandos suportados.");
			break;
		}
	}

}
