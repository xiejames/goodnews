package together.news.rss;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class RSSUtils {
	public static void main(String[] args) throws IOException {
		String baseUrl = "http://news.163.com/special/00011K6L/rss_newstop.xml";
		String fileName="./target/163.news.top.html";
		seleniumOnceGet(baseUrl,fileName);
	}
	public static boolean stop=false;
	private static int maxFailAllow=10;
	public static void seleniumScheduledGet(long timeGap,String url,String fileName) {
		WebDriver driver = new FirefoxDriver();
		Selenium selenium = new WebDriverBackedSelenium(driver, url);
		int failedCount=0;
		while(stop==false){
			selenium.open(url);
			String rssText = selenium.getHtmlSource();
			try{
				rssText=HTMLUtils.filter(rssText);
				FileUtils.saveToFile(rssText,fileName);
				Thread.sleep(timeGap);
			}catch(Exception e){
				e.printStackTrace();
				if(failedCount++>maxFailAllow){
					selenium.close();
					driver.close();
				}
			}
		}
		selenium.close();
		driver.close();
	}
	public static void seleniumOnceGet(String url,String fileName) throws IOException {
		WebDriver driver = new FirefoxDriver();
		Selenium selenium = new WebDriverBackedSelenium(driver, url);
		selenium.open(url);
		String rssText = selenium.getHtmlSource();
		rssText=HTMLUtils.filter(rssText);
		FileUtils.saveToFile(rssText,fileName);
		selenium.close();
		driver.close();
	}
	public static void jsoupGet(String url,String fileName) throws IOException{
		Document doc = Jsoup.connect(url)
				  .userAgent("Firefox") 
				  .timeout(3000)
				  .get();
		FileUtils.saveToFile(doc.html(),fileName);
	}

}
