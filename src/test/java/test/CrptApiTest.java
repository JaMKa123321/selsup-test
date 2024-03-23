package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import main.CrptApi;

public class CrptApiTest {
	
//	URL для теста https://ismp.crpt.ru/api/v3/lk/documents/create | http://127.0.0.1:5000/post_json
	
	@Test
	void crptApiTest() {
		CrptApi ca = new CrptApi(TimeUnit.MILLISECONDS, 2);
	    Thread[] threads = new Thread[6];
	    for (int i = 0; i < threads.length; i++) {
	    	threads[i] = new Thread() {
	    		@Override
	    		public void run() {
	    			try {
		    			ca.createDoc(new Object(), "Test");
		    		} catch (InterruptedException e) {
		    			System.out.println("Thread Interrupted");
		    			e.printStackTrace();
		    		} catch (IOException e) {
		    			System.out.println("Error working with file");
		    			e.printStackTrace();
		    		}
	    		}
	    	};
	    }
	    for (Thread t : threads) {
	    	t.run();
	    }
	}
	
}
