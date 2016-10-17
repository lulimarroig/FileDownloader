package downloader;

import java.util.Properties;

/** 
 * @author lucia
 *
 *This class is a generic definition that allows to have different extensions and implementations
 *to use different protocols to download the sources
 */
public abstract class Downloader {
	
	public static final int MAX_TRANSFER_BYTES = 10240;
	public static final int BLOCK_SIZE = 1024;
	public static final String PROPERTIES_LOCATION = "/configuration.properties";

	public static String getFileName(final String url){		
		int start = url.lastIndexOf('/');		
		String fileName = url.substring(start);
		
		return fileName;
	}
	
	public String getLocalFolder() throws Exception{
		Properties properties = new Properties();		
		properties.load(getClass().getResourceAsStream(PROPERTIES_LOCATION));				

		return properties.getProperty("fileLocation");
		
	}
	
	public abstract boolean downloadFile(final String source) throws Exception;		

}
