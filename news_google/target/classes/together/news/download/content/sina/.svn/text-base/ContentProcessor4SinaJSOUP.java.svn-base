package together.news.download.content.sina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import together.news.common.NewsDTO;
import together.news.common.utils.ExchangeDataFormatUtils;
import together.news.download.content.ContentProcessor;

public class ContentProcessor4SinaJSOUP extends ContentProcessor{

	protected ArrayList<NewsDTO> loadNews(String html) {
		ArrayList<NewsDTO> result=new ArrayList<NewsDTO>();
		String str=html;
        Document doc = null;
        //1
        doc = Jsoup.parse(str);  
        //2
		Elements entryList = doc.select("item");
		String title = null,titleLink= null,content= null,updateDate= null;
		for(Element item:entryList){
			title=trimTitle(item.select("title").first().text());
			titleLink=trimTitleLink(item.select("guid").first().text());
			content=trimContent(item.select("description").first().text());
			updateDate=trimUpdateDate(item.select("pubdate").first().text());
			addANewOne(result, title, titleLink, content,updateDate);
		}
		logger.debug(ExchangeDataFormatUtils.toExchangeDataStr(result));
		return result;
	}


	@Override
	protected void loadPictureData(List<NewsDTO> list, int max)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	private String trimUpdateDate(String text) {
		return text;
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

