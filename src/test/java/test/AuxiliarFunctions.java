package test;

public class AuxiliarFunctions {
	private static final String EOL =
			System.getProperty("line.separator");
	
	public String welcomeMessage(){
		String message = "##########################################" + EOL;
		message += "## SOURCE DOWNLOADER                    ##" + EOL;
		message += "##########################################" + EOL;
		message += "## Version: 0.0.1                       ##" + EOL;
		message += "##########################################" + EOL;	
		
		return message;
	}
	
	public String goodByeMessage(){
		String message = EOL + EOL + "##########################################" + EOL;
		message += "## GOOD BYE!                            ##" + EOL;
		message += "##########################################" + EOL + EOL;
		
		return message;
	}
}
