package together.news.download.content.baidu;

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

public class ContentProcessor4Baidu extends ContentProcessor{
	


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
        Element contentContainer=doc.getElementById("content");
		Elements entryList =contentContainer .select("li");
		for(Element entry:entryList){
			  String titleLink = entry.select("a").attr("href"); 
			  String title = entry.select("a").text(); 
			  String updateDate = contentContainer.select("h3").first().text();
			  String content="";
			  this.addANewOne(result, title, titleLink, content, updateDate);
		}
		logger.debug(ExchangeDataFormatUtils.toExchangeDataStr(result));
		return result;
	}
	
}

