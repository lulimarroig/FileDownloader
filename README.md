# FileDownloader

/*******************************************************************************/
This is a Java Maven project.


    1. The program should extensible to support different protocols 
The HTML protocol is supported now and the program is extensible to add more protocol implementations.
HTML protocol for downloading sources is up and running and is tested using JUnit.
FTP implementation was started as a way of showing the extensibility, but it is not fully implemented and tests are not finished for FTP.

    2.some sources might very big (more than memory)
The program reads from the source gradually and saves to disk after a configurable amount of bytes have been read. This was tested using JUnit with a 100Mb file, also it was tested with a 1Gb file (this test is deactivated for practical reasons, but it is available in the solution to be activated at any time).

    3.some sources might be very slow, while others might be fast
The solutions uses a concurrent implementation were all the sources (passed as urls) are downloaded concurrently so if the first source is too big, you do not have to wait until it is finished to have the second source available. This is tested (very basic testing) using JUnit.

    4.some sources might fail in the middle of download and 5.we don't want to have partial data in the final location in any case.
A fallback mecanism is implemented so that if the download fails, the corrupted file is deleted from the file system. This functionallity was tested manually by simulating network issues but no automated tests were written.

IMPORTANT! The destination folder can be configured in the 'configuration.properties' file. Default one is "/home" you SHOULD change it for one that works in your local machine.

NOTE: Only basic tests were written, load or stress tests were not included.


/*******************************************************************************/
