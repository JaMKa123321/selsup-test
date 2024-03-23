package main;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;

public class CrptApi {
	
	private int requestLimit;
	private int currentRequests;
	
	public CrptApi(TimeUnit timeUnit, int requestLimit) {
		this.requestLimit = requestLimit;
		currentRequests = 0;
	}
	
	public void createDoc(Object doc, String signature) throws InterruptedException, IOException {
		
		currentRequests++;
		HttpURLConnection con = (HttpURLConnection) new URL("http://127.0.0.1:5000/post_json").openConnection();
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
		
		OutputStream os = con.getOutputStream();
		String text = readString(new FileReader("test.json"));
		os.write(text.getBytes());
		os.flush();
		os.close();
		
		System.out.println(con.getResponseCode());
		
		currentRequests--;
		
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

class MainClass {

	public static void main(String[] args) throws JSONException, IOException {
		
	}

}
