package together.news.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class RSS {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String baseUrl = "http://news.163.com/special/00011K6L/rss_newstop.xml";
		String fileName="./163.news.top.html";
		seleniumGet(baseUrl,fileName);
	}
	public static void seleniumGet(String url,String fileName) throws IOException {
		WebDriver driver = new FirefoxDriver();
		Selenium selenium = new WebDriverBackedSelenium(driver, url);
		selenium.open(url);
		String rssText = selenium.getHtmlSource();
		rssText=filter(rssText);
		saveToFile(rssText,fileName);
		selenium.close();
		driver.close();
	}
	public static void jsoupGet(String url,String fileName) throws IOException{
		Document doc = Jsoup.connect(url)
				  .userAgent("Firefox") 
				  .timeout(3000)
				  .get();
		saveToFile(doc.html(),fileName);
	}
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
	private static void saveToFile(String rssText,String fileName) throws IOException {
		File saveFile = new File(fileName);
		if (saveFile.exists() == false) {
			saveFile.createNewFile();
		}
		FileOutputStream stream = new FileOutputStream(saveFile);
		OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
		writer.write(rssText);
		writer.flush();
		writer.close();
	}

}
