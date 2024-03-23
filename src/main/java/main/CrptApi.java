package main;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CrptApi {
	
	ExecutorService executor;
	
	public CrptApi(TimeUnit timeUnit, int requestLimit) {
		executor = new ThreadPoolExecutor(1, requestLimit, 0L, timeUnit, new LinkedBlockingQueue<Runnable>());
	}
	
	public void createDoc(Object doc, String signature) throws InterruptedException, IOException {
		
		executor.submit(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection con;
				try {
					con = (HttpURLConnection) new URL("https://ismp.crpt.ru/api/v3/lk/documents/create").openConnection();
					con.setRequestProperty("Content-Type", "application/json");
					con.setDoOutput(true);
					
//					С Вашего позволения я не буду писать сюда JSON-строку, а просто сохраню её в файл и буду считывать
					OutputStream os = con.getOutputStream();
					String text = readString(new FileReader("test.json"));
					os.write(text.getBytes());
					os.flush();
					os.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
	}
	
	private static String readString(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	    	sb.append((char) cp);
	    }
	    return sb.toString();
	}
}
