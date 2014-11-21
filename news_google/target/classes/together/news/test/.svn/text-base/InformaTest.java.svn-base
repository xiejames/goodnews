package together.news.test;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.parsers.FeedParser;

public class InformaTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {
		System.setProperty("http.proxyHost", "sjc-vts-388");
		System.setProperty("http.proxyPort", "8088");
		;
		String url = "http://news.163.com/special/00011K6L/rss_newstop.xml";
		Document doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10000)
				.get();
		ChannelIF channel = FeedParser.parse(new ChannelBuilder(), doc.html());
		Collection<ItemIF> items = channel.getItems();
		Iterator<ItemIF> itemsIt = items.iterator();
		while (itemsIt.hasNext()) {
			ItemIF item = itemsIt.next();
			System.out.print(
					item.getTitle() 
					+ "-" + item.getLink() 
					+ "-" + item.getDescription() 
					+ "-" + item.getDate());
		}

	}

}
