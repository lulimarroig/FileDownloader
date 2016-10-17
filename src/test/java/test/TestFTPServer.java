package test;

import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.WindowsFakeFileSystem;

public class TestFTPServer {
	
	public void createFTPServer(){
		FakeFtpServer fakeFtpServer = new FakeFtpServer();
		fakeFtpServer.addUserAccount(new UserAccount("testUser", "password", "c:\\data"));

		FileSystem fileSystem = new WindowsFakeFileSystem();
		fileSystem.add(new DirectoryEntry("c:\\data"));
		fileSystem.add(new FileEntry("c:\\data\\file1.txt", "This is a test file."));
	
		fakeFtpServer.setFileSystem(fileSystem);
		fakeFtpServer.start();
	}

}
