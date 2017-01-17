package br.hss.app.view.console;

public enum ConsoleUIEnum {
	
	EXIT 			("exit",			0,"exit,                         Finaliza a leitura de commando."),
	HELP 			("help",			0,"help,                         Apresenta este help."),
	COUNT 			("count *",			0,"count *,                      Informa o n�mero total de registro carregados."),
	COUNT_DISTINCT 	("count distinct",	1,"count distinct [propriedade], Informa o n�mero de ocorr�ncias carregadas da propriedade informada."),
	FILTER			("filter",          2,"filter [propriedade] [valor], Informa os registros filtrados pela propriedade informada."),
	INVALID 		("",				0,"");
	
	private String command;
	private int numberArgs = 0;
	private String helpText;
	
	private ConsoleUIEnum(String command, int args, String help) {
		this.command = command;
		this.numberArgs = args;
		this.helpText = help;
	}
	
	public String getHelpText() {
		return this.helpText;
	}
	
	public String getCommnad() {
		return this.command;
	}
	
	public String[] argsCommand(String line) {
		if (this.numberArgs == 0 || line == null)
			return null;

		String str = line.substring(this.getCommnad().length()).trim();
		
		String[] args = str.split("\u0020");
		
		if (args.length == this.numberArgs)
			return args;
		
		return null;
	}

}
