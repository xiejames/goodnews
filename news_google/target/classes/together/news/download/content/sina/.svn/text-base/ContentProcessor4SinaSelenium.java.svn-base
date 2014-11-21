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

public class ContentProcessor4SinaSelenium extends ContentProcessor{


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
        Element dataContainer=doc.select("table").last();
        //2
		Elements entryList = dataContainer.select("tr");;
		int currentIndex=-1;
		boolean newOneStarted=false;
		boolean newOneReady=false;
		String title = null,titleLink= null,content= null,updateDate= null;
		while(currentIndex<entryList.size()-1){
			currentIndex++;
			Element currentElement=entryList.get(currentIndex);
			if(currentElement.hasAttr("bgcolor")==false) {
				continue;
			}
			if(newOneStarted==false){
				if(isTitle(currentElement)==false){//不是title，问题很严重，检查下一个
					continue;
				}else{//是title
					title = currentElement.select("td").text(); 
					newOneStarted=true;
				}
			}else{//已经找到过title
				if(isTitle(currentElement)){//找到下一个title，重置
					if(newOneReady==true){
						  addANewOne(result, title, titleLink, content,
								updateDate);
						  newOneReady=false;
					}
					newOneStarted=false;
				}else if(newOneReady==false){
					if(isContent(currentElement)==false){//不是content，问题很严重，检查下一个重置
						newOneStarted=false;
						newOneReady=false;
						continue;
					}else{
						newOneReady=true;
						content=currentElement.select("td").text();
					}
				}else{//找link和updateDate
					if(isLink(currentElement)){
						titleLink = currentElement.select("a").attr("href");
						titleLink=trimLink(titleLink);
					}else if(isUpdateDate(currentElement)){
						  updateDate = currentElement.select("span").text();
						  addANewOne(result, title, titleLink, content,
								updateDate);
						  newOneStarted=false;
						  newOneReady=false;
					}else{
						//什么也不是
					}
				}
			}
		}
		logger.debug(ExchangeDataFormatUtils.toExchangeDataStr(result));
		return result;
	}


	private boolean isUpdateDate(Element element) {
		return element.getElementsByTag("span").size()>0;
	}

	private boolean isLink(Element element) {
		return element.getElementsByTag("a").size()>0;
	}

	private boolean isContent(Element element) {
		return isTitle(element)==false
				&& isLink(element)==false
						&& isUpdateDate(element)==false
								&& isLink(element)==false
				;
	}

	private boolean isTitle(Element element) {
		return element.getElementsByTag("b").size()>0;
	}
	private String trimLink(String linkHref) {
		  return linkHref=(linkHref==null || linkHref.indexOf("?url=http")==-1)?"":linkHref.substring(linkHref.indexOf("?url=")+9);
	}
}

