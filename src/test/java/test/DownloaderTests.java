package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import main.App;

import org.junit.Test;

/**
 * 
 * @author lucia
 *
 *Main testing class for general sources downloads
 */
public class DownloaderTests {
	
	private static final String EOL =
		      System.getProperty("line.separator");

	@Test
	public void testEmptyParameters(){
		String[] emptyParam = new String[0];	
		AuxiliarFunctions aux = new AuxiliarFunctions();
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream console = System.out;
		try {
			System.setOut(new PrintStream(bytes));	         
			App.main(emptyParam);
		} finally {
			System.setOut(console);
		}
		assertEquals(aux.welcomeMessage() + EOL 
				+ "No sources to download" 
				+ aux.goodByeMessage(),
				bytes.toString());
	}
	
	
	@Test
	public void testHTTPSource(){
		String[] httpUrl = new String[1];	
		httpUrl[0] = "http://speedtest.ftp.otenet.gr/files/test100k.db";
		
		AuxiliarFunctions aux = new AuxiliarFunctions();
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	      PrintStream console = System.out;
	      try {
	         System.setOut(new PrintStream(bytes));	         
	         App.main(httpUrl);
	      } finally {
	         System.setOut(console);
	      }
	      assertEquals("The output was not the expected one",aux.welcomeMessage() + EOL 
	    		    + "Starting to download http://speedtest.ftp.otenet.gr/files/test100k.db" + EOL
					+ "http://speedtest.ftp.otenet.gr/files/test100k.db download was Successfull!" 
	    		  + aux.goodByeMessage(),
	            bytes.toString());
	}
	
	@Test
	public void testConcurrentExecution(){
		String[] httpUrl = new String[2];	
		httpUrl[0] = "http://speedtest.ftp.otenet.gr/files/test100k.db";
		httpUrl[1] = "http://my.file.com/file";
		
		AuxiliarFunctions aux = new AuxiliarFunctions();
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	      PrintStream console = System.out;
	      try {
	         System.setOut(new PrintStream(bytes));	         
	         App.main(httpUrl);
	      } finally {
	         System.setOut(console);
	      }
	      
	      String textOne = aux.welcomeMessage() + EOL 
	    		    + "Starting to download http://speedtest.ftp.otenet.gr/files/test100k.db" + EOL
	    		    + "Starting to download http://my.file.com/file" + EOL
	    		    + "http://speedtest.ftp.otenet.gr/files/test100k.db download was Successfull!" + EOL
	    		    + "http://my.file.com/file download was Successfull!"			
	    		  + aux.goodByeMessage();
	      
	      String textTwo = aux.welcomeMessage() + EOL 
	    		    + "Starting to download http://speedtest.ftp.otenet.gr/files/test100k.db" + EOL
	    		    + "Starting to download http://my.file.com/file" + EOL	    		    
	    		    + "http://my.file.com/file download was Successfull!" +EOL	
	    		    + "http://speedtest.ftp.otenet.gr/files/test100k.db download was Successfull!"
	    		  + aux.goodByeMessage();
	      
	      assertTrue("The output was not the expected one",
	    		  textOne.equals(bytes.toString()) || textTwo.equals(bytes.toString()));
	}
	
	@Test	
	public void testBigInputFile(){		
		String[] httpUrl = new String[1];	
		httpUrl[0] = "http://speedtest.ftp.otenet.gr/files/test100Mb.db";		
		
		AuxiliarFunctions aux = new AuxiliarFunctions();
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	      PrintStream console = System.out;
	      try {
	         System.setOut(new PrintStream(bytes));	         
	         App.main(httpUrl);
	      } finally {
	         System.setOut(console);
	      }
	      assertEquals("The output was not the expected one",aux.welcomeMessage() + EOL 
	    		    + "Starting to download http://speedtest.ftp.otenet.gr/files/test100Mb.db" + EOL
					+ "http://speedtest.ftp.otenet.gr/files/test100Mb.db download was Successfull!" 
	    		  + aux.goodByeMessage(),
	            bytes.toString());
	}
	
	//Is deactivated for practical reasons, but it can be activated it is working
	//@Test	
	public void testHugeInputFile(){		
		String[] httpUrl = new String[1];	
		//1GB
		httpUrl[0] = "http://speedtest.ftp.otenet.gr/files/test1Gb.db";
		
		AuxiliarFunctions aux = new AuxiliarFunctions();
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	      PrintStream console = System.out;
	      try {
	         System.setOut(new PrintStream(bytes));	         
	         App.main(httpUrl);
	      } finally {
	         System.setOut(console);
	      }
	      assertEquals("The output was not the expected one",aux.welcomeMessage() + EOL 
	    		    + "Starting to download http://speedtest.ftp.otenet.gr/files/test1Gb.db" + EOL
					+ "http://speedtest.ftp.otenet.gr/files/test1Gb.db download was Successfull!" 
	    		  + aux.goodByeMessage(),
	            bytes.toString());
	}
	
	//TODO: Finish FTP Testing implementation
	//@Test
	public void testFTPSource(){
		String[] ftpParam = new String[1];	
		ftpParam[0] = "";		
		
		String[] connectionParams = new String[1];			
		connectionParams[0] = "localhost";
		connectionParams[1] = "9187";
		connectionParams[2] = "testUser";
		connectionParams[3] = "password";		
		
		AuxiliarFunctions aux = new AuxiliarFunctions();
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream console = System.out;
		try {
			System.setOut(new PrintStream(bytes));	         
			App.main(ftpParam);
		} finally {
			System.setOut(console);
		}
		assertEquals(aux.welcomeMessage() + EOL 				
				+ aux.goodByeMessage(),
				bytes.toString());
	}
	
	

}
