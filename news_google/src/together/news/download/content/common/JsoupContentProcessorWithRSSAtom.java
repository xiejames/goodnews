package together.news.download.content.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import together.news.common.NewsDTO;
import together.news.common.utils.ExchangeDataFormatUtils;
import together.news.download.content.ContentProcessor;

public class JsoupContentProcessorWithRSSAtom extends ContentProcessor{

	protected ArrayList<NewsDTO> loadNews(String html) {
		ArrayList<NewsDTO> result=new ArrayList<NewsDTO>();
		String str=html;
        Document doc = null;
        //1
        doc = Jsoup.parse(str);  
        //2
		Elements entryList = doc.select("item");//RSS
        if(doc.select("entry").size()>3){//Atom1.0
        	entryList= doc.select("entry");
        }
		String title = null,titleLink= null,content= null,updateDate= null;
		for(Element item:entryList){
			for(int i=0;i<item.childNodes().size();i++){
				Node child=item.childNodes().get(i);
				if(child.outerHtml().trim().equals("")) continue;
				if(child.nodeName().equals("title")){
					title=trimTitle(trickRealData(item, i));
				}else if(child.nodeName().equals("link")
						||child.nodeName().equals("guid")
						||child.nodeName().equals("id")){
					titleLink=trimTitleLink(trickRealData(item, i));
				}else if(child.nodeName().equals("description")
						||child.nodeName().equals("summary")
						||child.nodeName().equals("content")){
					content=trimContent(trickRealData(item, i));
				}else if(child.nodeName().equals("pubdate")
						||child.nodeName().equals("published ")){
					updateDate=trimUpdateDate(trickRealData(item, i));
				}
			}
			addANewOne(result, title, titleLink, content,updateDate);
		}
		logger.debug(ExchangeDataFormatUtils.toExchangeDataStr(result));
		return result;
	}


	private String trickRealData(Element item, int i) {
		Node child=item.childNodes().get(i);
		String text;
		if(child.childNodes().size()==0){
			if(item.childNodes().size()-1==i){
				return "";
			}
			child=item.childNodes().get(i+1);
			text=child.outerHtml();
		}else{
			text=child.childNode(0).outerHtml();
		}
		return text;
	}


	@Override
	protected void loadPictureData(List<NewsDTO> list, int max)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	private String trimUpdateDate(String text) {
		if(text.equals("")){
			text=new Date().toString();
		}
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
		  return linkHref=(linkHref==null || linkHref.indexOf("?url=http")==-1)?linkHref:linkHref.substring(linkHref.indexOf("?url=")+5);
	}

}

