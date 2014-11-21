package together.news.download;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import together.news.download.utils.DownloadFileUtils;
import together.news.download.utils.HTMLUtils;
import together.news.download.web.URLRegistry;
import together.news.download.web.URLRegistry.URLType;

import com.thoughtworks.selenium.Selenium;

public class WebUtils {
	public static boolean stop=false;
	private static int maxFailAllow=10;
	private static HashMap<String,WebDriver> seleniumDriverCache = new HashMap<String,WebDriver>();
	private static HashMap<String,Selenium> seleniumCache = new HashMap<String,Selenium>();
	private static HashMap<String,Semaphore> seleniumCacheLock = new HashMap<String,Semaphore>();
	private static Logger logger=Logger.getLogger(WebUtils.class);

	public static void init() {
		
	}	
	
	public static void webGet(long timeGap,String url){
		try {
			seleniumScheduledGet(timeGap,url);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(url);
		}
	}
	public static void webGet(String url){
		try {
			if(URLRegistry.getURLType(url)==URLType.HTML ){
				Document doc = WebUtils.jsoupGet(url);
				String text = HTMLUtils.filter(doc.html());
				DownloadFileUtils.saveDownLoadText(text);
			}else if(URLRegistry.getURLType(url)==URLType.RSS){
				String text = HTMLUtils.filter(url);
				DownloadFileUtils.saveDownLoadText(text);
			}else{
				seleniumOnceGet(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(url);
		}
	}
	public static void seleniumScheduledGet(long timeGap,String url) throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseURL=getBaseURL(url);
		String relativePath=url.substring(baseURL.length());
		Selenium selenium = new WebDriverBackedSelenium(driver, baseURL);
		int failedCount=0;
		while(stop==false){
			selenium.open(relativePath);
			String text = selenium.getHtmlSource();
			try{
				text=HTMLUtils.filter(text);
				DownloadFileUtils.saveDownLoadText(text);
				Thread.sleep(timeGap);
			}catch(Exception e){
				e.printStackTrace();
				if(failedCount++>maxFailAllow){
					selenium.close();
					driver.close();
					throw e; 
				}
			}
		}
		selenium.close();
		driver.close();
	}
	private static String getBaseURL(String url) {
		return url.trim().substring(0,url.indexOf("/",7));
	}
	public static void seleniumOnceGet(String url) throws IOException {
		String baseURL=getBaseURL(url);
		String relativePath=url.substring(baseURL.length());
		Selenium selenium = createASeleniumInstance(baseURL);
		try {
			seleniumCacheLock.get(baseURL).acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		selenium.open(relativePath);
		String rssText = selenium.getHtmlSource();
		try {
			rssText=HTMLUtils.filter(rssText);
		} catch (Exception e) {
			e.printStackTrace();
			//rssText=reTry(selenium,url,3);
		}
		if(rssText==null){
			logger.error(url);
		}else{
			logger.info(url);
		}
		DownloadFileUtils.saveDownLoadText(rssText);
		seleniumCacheLock.get(baseURL).release();
	}
	private static String seleniumReTry(Selenium selenium, String url,int retry) {
		if(retry<1){
			return null;
		}
		selenium.open(url);
		selenium.waitForPageToLoad("60000");
		String rssText = selenium.getHtmlSource();
		try {
			rssText=HTMLUtils.filter(rssText);
		} catch (Exception e) {
			rssText=seleniumReTry(selenium,url,retry--);
		}
		return rssText;
		
	}

	private static synchronized Selenium createASeleniumInstance(String url) {
		Selenium selenium = seleniumCache.get(url);
		if(selenium==null){
			WebDriver driver = new FirefoxDriver();
			seleniumDriverCache.put(url, driver);
			seleniumCache.put(url, new WebDriverBackedSelenium(driver, url));
			seleniumCacheLock.put(url, new Semaphore(1));
		}
		return seleniumCache.get(url);
	}
	public static void closeSelenium(){
//		for(String url:seleniumCacheLock.keySet()){
//			try {
//				seleniumCacheLock.get(url).acquire(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		for(String url:seleniumCache.keySet()){
			seleniumDriverCache.get(url).close();
			seleniumCache.get(url).close();
		}
	}
	public static Document jsoupGet(String url) throws IOException{
		Document doc = Jsoup.connect(url)
				  .userAgent("Mozilla")
				  .timeout(30000)
				  .get();
		return doc;
	}

}
