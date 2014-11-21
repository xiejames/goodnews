package together.news.download.content.common;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import together.news.common.NewsDTO;
import together.news.common.utils.ExchangeDataFormatUtils;
import together.news.download.content.ContentProcessor;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ContentProcessor4Rome extends ContentProcessor{

	protected ArrayList<NewsDTO> loadNews(String url){
		ArrayList<NewsDTO> result=new ArrayList<NewsDTO>();
		SyndFeedInput input = new SyndFeedInput();
		try{
			URL feedUrl = new URL(url);
			SyndFeed feed = input.build(new XmlReader(feedUrl));
			List<SyndEntry> items = feed.getEntries();
			String title = null,titleLink= null,content= null,updateDate= null;
			for(SyndEntry item:items){
				title=trimTitle(item.getTitle());
				titleLink=trimTitleLink(item.getLink());
				content=trimContent(item.getDescription().getValue());
				updateDate=trimUpdateDate(item.getPublishedDate());
				addANewOne(result, title, titleLink, content,updateDate);
			}
			logger.debug(ExchangeDataFormatUtils.toExchangeDataStr(result));
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	@Override
	protected void loadPictureData(List<NewsDTO> list, int max)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	private String trimUpdateDate(Date text) {
		return text==null ? new Date().toString():text.toString();
	}


	private String trimContent(String text) {
		return text;
	}


	private String trimTitle(String text) {
		int start=text.indexOf("[CDATA[[");
		if(start!=-1){
			text=text.substring(start+8);
		}
		start=text.indexOf("[CDATA[");
		if(start!=-1){
			text=text.substring(start+7);
		}
		int end=text.indexOf("]]");
		if(end!=-1){
			text=text.substring(0,end);
		}
		return text;
	}


	private String trimTitleLink(String linkHref) {
		  return linkHref=(linkHref==null || linkHref.indexOf("?url=http")==-1)?"":linkHref.substring(linkHref.indexOf("?url=")+5);
	}

}

