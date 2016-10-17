package main;

import java.net.URL;
import java.util.concurrent.RecursiveTask;

import communication.Messages;
import downloader.HTTPDownloader;

/**
 * 
 * @author lucia
 *
 *Class that contains the logic for chossing the correct downloading protocol and triggers the download for a given source url
 *This class allows concurrent execution
 */
public class ProcessDownload extends RecursiveTask<Void> {	
	
	private static final long serialVersionUID = 1L;

	public static int MAX_TRIES = 3;
	
	String source;
	
	public ProcessDownload(String source){
		this.source = source;		
	}

	@Override
	protected Void compute() {
		Messages.startDownload(source);	
		boolean success = true;
		
		//Mecanism for supporting different protocols
		try {
			URL url = new URL(source);
			String protocol = url.getProtocol().toUpperCase();
			
			if(protocol.equals("HTTP") || protocol.equals("HTTPS")){
				HTTPDownloader downloader = new HTTPDownloader();	
				boolean successfullDownload = downloader.downloadFile(source);
				if(!successfullDownload){
					Messages.downloadFail(source);
					success = false;
				}
				
				if(success){
					Messages.downloadSuccess(source);
				}
			}else{
				Messages.unsupportedProtocol(source, protocol);
				
				//TODO: Finish and test FTP Protocol implementation.					
				/*Messages.enterFTPParams(source);
				String input = System.console().readLine();
				String[] params = processFTPInput(input, source);
				
				if(params == null){
					Messages.downloadFail(source);
					success = false;
				}
				
				FTPDownloader downloader = new FTPDownloader();
				boolean successfullConnection = downloader.connectToServer(params[0], Integer.parseInt(params[1]), params[2], params[3]);
				if(!successfullConnection){
					Messages.noFTPServerConnection(params[0]);	
					success = false;
				}
				
				boolean successfulldownload = downloader.downloadFile(source);
				if(!successfulldownload){
					Messages.downloadFail(source);
					success = false;
				}
				
				if(success){
					Messages.downloadSuccess(source);		
				}*/
			}			
			
		} catch (Exception e) {
			Messages.downloadFail(source);			
		}	
		
		return null;
	}
	
	/*private static String[] processFTPInput(String input, String source){
		String[] ftpServerParams = null;
		
		for(int i=0; i<MAX_TRIES; i++){
			ftpServerParams = input.split(",");
			
			if(ftpServerParams.length != 4){
				Messages.wrongFTPParams(source);
				input = System.console().readLine();
			}else{
				break;
			}
		}
		
		for(int i=0;i<4;i++){
			ftpServerParams[i] = ftpServerParams[i].trim();			
		}
		
		return ftpServerParams;		
	}*/

}
