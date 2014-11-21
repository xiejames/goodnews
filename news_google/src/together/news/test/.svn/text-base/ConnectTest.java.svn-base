package together.news.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConnectTest {
	public static void main(String[] args) throws Exception {
//        jdkGet();  
        jsoupGet();
	}

	private static void jsoupGet() throws IOException {
		System.setProperty("http.proxyHost", "sjc-vts-388");
		System.setProperty("http.proxyPort", "8088");;
		String url="http://news.163.com/special/00011K6L/rss_newstop.xml";
		Document doc = Jsoup.connect(url)
				  .userAgent("Mozilla") 
				  .timeout(10000)
				  .get();
		System.out.println(doc.html());
		
	}

	private static void jdkGet() throws IOException, MalformedURLException {
		String url="http://news.163.com/special/00011K6L/rss_newstop.xml";
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		InputStream dataStream=con.getInputStream();
		BufferedInputStream bufDataStream=new BufferedInputStream(dataStream);
		InputStreamReader reader=new InputStreamReader(bufDataStream);
		BufferedReader breader=new BufferedReader(reader);  
        String s = breader.readLine();  
        while(s!=null){  
            System.out.println(s);  
            s = breader.readLine();  
        }
	}
}
