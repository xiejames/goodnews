package together.news.test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RomeTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FeedException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws IOException,
			IllegalArgumentException, FeedException {
		test1();
	}

	private static void test1() throws MalformedURLException, FeedException,
			IOException {
		System.setProperty("http.proxyHost", "sjc-vts-388");
		System.setProperty("http.proxyPort", "8088");
		URL feedUrl = new URL("http://rss.sina.com.cn/news/marquee/ddt.xml");
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedUrl));
		List<SyndEntry> items = feed.getEntries();
		for (SyndEntry item : items) {
			System.out.println(item.getTitle() + "-----------" + item.getLink()
					+ "-----------" + item.getDescription().getValue()
					+ "-----------" + item.getPublishedDate());
		}
	}

}
