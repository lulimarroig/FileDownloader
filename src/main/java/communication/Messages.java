package communication;

public class Messages {
	
	public static void welcomeMessage(){
		StringBuilder text = new StringBuilder();        
        text.append("##########################################\n");
        text.append("## SOURCE DOWNLOADER                    ##\n");
        text.append("##########################################\n");
        text.append("## Version: 0.0.1                       ##\n");
        text.append("##########################################\n");           

        System.out.println(text.toString());        
	}
	
	public static void goodbyeMessage(){
		StringBuilder text = new StringBuilder();
        text.append("\n");
        text.append("##########################################\n");
        text.append("## GOOD BYE!                            ##\n");
        text.append("##########################################\n");
      
        System.out.println(text.toString());        
	}
	
	public static void noSources(){
		System.out.println("No sources to download");
	}
	
	public static void startDownload(String source){				
		System.out.println("Starting to download " + source);
	}
	
	public static void downloadFail(String source){
		System.out.println("The source " + source + " could not be downloaded");
	}
	
	public static void downloadSuccess(String source){
		System.out.println(source + " download was Successfull!");
	}
	
	public static void enterFTPParams(String source){
		System.out.print("Please enter Server Name, Port, User and Password for "+ source +": /n(**Use , to separate. Do not enter press enter until you fullfilled the four parameters.");
	}
	
	public static void wrongFTPParams(String source){
		System.out.print("Wrong number of parameters! Please enter Server Name, Port, User and Password for "+ source +": /n(**Use , to separate. Do not enter press enter until you fullfilled the four parameters.");
	}
	
	public static void noFTPServerConnection(String serveName){
		System.out.print("Could not connect to " + serveName + "server");
	}
	
	public static void unsupportedProtocol(String source, String protocol){
		System.out.print("Sorry! For downloading "+source+", protocol "+protocol+" is required, but is not yet supported.");
	}

}
