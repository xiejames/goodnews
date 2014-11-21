package together.news.download.content.sohu;

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

public class ContentProcessor4Sohu_youxi extends ContentProcessor{

	@Override
	protected void loadPictureData(List<NewsDTO> list, int max)
			throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	protected ArrayList<NewsDTO> loadNews(String html) {
		ArrayList<NewsDTO> result=new ArrayList<NewsDTO>();
		String str=html;
        Document doc = null;
        //1
        doc = Jsoup.parse(str);  
        //2
		Elements entryList = doc.getElementById("list_leftcontent").select("div.list_left1lan");
		String title = null,titleLink= null,content= null,updateDate= null;
		for(Element item:entryList){
			if(item==null 
					|| item.select("div.lan1_title")==null 
					||item.select("div.lan1_xtitle")==null 
					|| item.select("div.list_lan1_content_right")==null 
					){
					continue;
				}
			title=trimTitle(item.select("div.lan1_title").first().select("a").first().text());
			titleLink=trimTitleLink(item.select("div.lan1_title").first().select("a").first().attr("href"));
			content=trimContent(item.select("div.list_lan1_content_right").first().text());
			updateDate=trimUpdateDate(item.select("div.lan1_xtitle").first().text());
			NewsDTO itemNews = addANewOne(result, title, titleLink, content,updateDate);
			Elements imgs = item.select("img");
			for(Element img:imgs){
				itemNews.getPicURLs().add(img.attr("src"));
				itemNews.getPicHeights().add(img.attr("height"));
				itemNews.getPicWidths().add(img.attr("width"));
				
			}
		}
		logger.debug(ExchangeDataFormatUtils.toExchangeDataStr(result));
		return result;
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
		  return linkHref;
	}


}

