package downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * 
 * @author lucia
 *
 *HTTP Protocol implementation for downloading sources
 */
public class HTTPDownloader extends Downloader {

	public boolean downloadFile(final String source) throws Exception {
		String filepath = getLocalFolder() + getFileName(source);
		FileOutputStream file = null;
        ReadableByteChannel rbc = null;
        
        try {
        	file = new FileOutputStream(filepath); 
        	
        	//Allows files bigger than memory to be read gradually
            URL documentURL = new URL(source);
            rbc = Channels.newChannel(documentURL.openStream());
            ByteBuffer byteBuffer = ByteBuffer.allocate(MAX_TRANSFER_BYTES);
            
            int bytesread = 0, bytesBuffered = 0;
            while( (bytesread = rbc.read(byteBuffer)) > 0 ) {
            	file.write( byteBuffer.array(), 0, bytesread );
                bytesBuffered += bytesread;                
                if (bytesBuffered > BLOCK_SIZE * BLOCK_SIZE) { //flush after 1MB
                    bytesBuffered = 0;
                    file.flush();
                }
                byteBuffer.clear();
            }
            return true;
        } catch (Exception e) {        	
        	//If connection is lost or an error occurs the file is deleted           
            
            if (file != null) {
	            file.close();
	            File realFile = new File(filepath);
	            
	            if(realFile!=null){
	            	realFile.delete();
	            }
            }
            
            return false;
        } finally{
            if (file != null) {
            	file.flush();               
            }
            
            if (rbc != null) {
                rbc.close();
            } 
        }        
    }
}
