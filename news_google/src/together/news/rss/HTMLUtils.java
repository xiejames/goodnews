package together.news.rss;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLUtils {
	public static String filter(String html) throws IOException {
        String str=html;
        Document doc = Jsoup.parse(str);  
       //filter 1
        doc = Jsoup.parse(str);  
        str=doc.getElementById("feedContent").html();
        //filter 2
        doc = Jsoup.parse(str);  
        
        return str;
	}
}
