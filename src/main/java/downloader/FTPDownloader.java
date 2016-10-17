package downloader;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * 
 * @author lucia
 *
 *FTP Protocol implementation for downloading sources
 */
public class FTPDownloader extends Downloader {
	
		private FTPClient ftpClient = null;
	
		public boolean connectToServer(final String server, final int port, final String user, final String pass){
			boolean success = true;
	        ftpClient = new FTPClient();
	        try {	 
	            ftpClient.connect(server, port);
	            ftpClient.login(user, pass);
	        } catch (IOException e) {	            
	            e.printStackTrace();
	            success = false;
	        }
	        
	        return success;
		}

		/**
		 * PreCondition: connecting to FTP server by calling connectToServer function
		 */
		public boolean downloadFile(final String source) throws Exception {
			boolean success = true;	 
			
			if(ftpClient == null){				
				return false;
			}
	        
	        try {
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
	            
	            String filepath = getLocalFolder() + getFileName(source);       
	           
	            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(filepath));
	            success = ftpClient.retrieveFile(filepath, outputStream1);
	            outputStream1.close();
			} catch (IOException e) {	            
	            e.printStackTrace();
	            success = false;
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        return success;
		}
}
