package together.news.download.content.netease;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import together.news.common.NewsDTO;
import together.news.download.WebUtils;
import together.news.download.content.ContentProcessor;

public class ContentProcessor4SOUP extends ContentProcessor{
	

	protected void loadPictureData(List<NewsDTO> list, int max) throws IOException {
		int count=0;
		for(NewsDTO item:list){
			if(count>=max) break;
			Document doc=WebUtils.jsoupGet(item.getTitleHref());
			if(doc ==null) continue;
			Element content = doc.getElementById("endText");
			if(content ==null) continue;
			Elements images = content.getElementsByTag("img");
			while(images!=null && images.size()>0){
				if(images.first().attr("src").endsWith("gif")){
					images.remove(0);
					continue;
				}else{
					logger.debug("find image "+count+images.first());
					item.getPicURLs().add(images.first().attr("src"));
					item.getPicWidths().add(images.first().attr("width"));
					item.getPicHeights().add(images.first().attr("height"));
					images.remove(0);
				}
			}
			count++;
		}
	}
	protected ArrayList<NewsDTO> loadNews(String html) {
		ArrayList<NewsDTO> result=new ArrayList<NewsDTO>();
		String str=html;
        Document doc = null;
        //1
        doc = Jsoup.parse(str);  
        str=doc.getElementById("feedContent").html();
        //2
        doc = Jsoup.parse(str);  
		Elements entryList = doc.select("div.entry");;
		for(Element entry:entryList){
			  String titleLink = entry.select("a").attr("href"); 
			  String title = entry.select("a").text(); 
			  String updateDate = entry.select("div.lastUpdated").text();
			  String content=entry.select("div.feedEntryContent").html();
			  if(content.contains("if(")){
				  content=fiter(content);
			  }
			  this.addANewOne(result, title, titleLink, content, updateDate);
		}
		return result;
	}
	
	private String fiter(String content) {
		String ascii="";
		for(int i=1;i<128;i++){
			ascii+=(char)i;
		}
		int trimIndex=content.indexOf("if(");
		if(trimIndex!=-1){
			String part1=content.substring(0,trimIndex);
			String part2=content.substring(trimIndex+1);
			while(true){
				if(ascii.contains(part2.substring(0,1))){
					part2=part2.substring(1);
				}else{
					break;
				}
			}
			content=part1+part2;
		}
		return content;
	}

}

