package main;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


import communication.Messages;

/**
 * 
 * @author lucia
 *
 *Main execution of App program
 */
public class App {
	
	public static final int TASK_POOL_WAIT = 200;
	
	public static void main(final String[] args){
		Messages.welcomeMessage();
		
		if(args.length == 0){			
			Messages.noSources();
		}
		
		//Concurrent download of sources
		ForkJoinPool pool = new ForkJoinPool();
	       
		int maxSize = args.length;
		int i = 0;		
		
        do {
            if (i < maxSize && (pool.getParallelism() > pool.getQueuedSubmissionCount())) {
                String source = args[i];
            	ProcessDownload task =
                        new ProcessDownload(source);
                pool.execute(task);
                
                i++;
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(TASK_POOL_WAIT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (i < maxSize || (pool.getActiveThreadCount() > 0) || (pool.getQueuedSubmissionCount() > 0));	
	
		
		Messages.goodbyeMessage();		
	}
	
}
