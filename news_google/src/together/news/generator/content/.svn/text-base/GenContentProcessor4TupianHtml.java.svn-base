package together.news.generator.content;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup;
import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.constant.PageData;

public class GenContentProcessor4TupianHtml extends GenContentProcessor{
	
	private int itemPerPage = 10;

	public GenContentProcessor4TupianHtml(GroupIdEnum pageName,HashMap<GroupIdEnum, NewsGroup> newsGroups) {
		super(pageName,newsGroups,"GB2312");
	}
	protected void init() {
		templateFileName="tupian.html";
	}
	protected String getView(String tempHtml,HashMap<String,List<NewsDTO>>  pageData) throws IOException {
		return setValueToTemplate(tempHtml,pageData);
	}

	@Override
	protected HashMap<String, List<NewsDTO>> loadPageData(GroupIdEnum pageName,HashMap<GroupIdEnum, NewsGroup> newsGroups) throws IOException {
		HashMap<String,List<NewsDTO>> newsDataMap=new HashMap<String,List<NewsDTO>>();
		if(newsGroups.get(pageName)==null){
			return newsDataMap;
		}
		newsDataMap.put(pageName.name(), newsGroups.get(pageName).getAllNews());
		return newsDataMap;
	}
	
	private  String setValueToTemplate(String html, HashMap<String,List<NewsDTO>> newsData) throws IOException {
		Document doc = Jsoup.parse(html);
		Element container = doc.select("div.area").first();
		Element contentContainer=container.select("div.box").first();
		if(newsData.get(pageName.name())==null){
			contentContainer.html("no data there");
		}else{
			List<NewsDTO> picDatas = newsData.get(pageName.name());
			Element slideTemplate=contentContainer.select("div.boxcontent").first().clone();
			Element contentTemplate = slideTemplate.select("ul.newslist").first();
			if(contentTemplate.select("li").size()!=0){
				itemPerPage=contentTemplate.select("li").size();
			}
			Element templateLi=contentTemplate.select("li").first().clone();
			
			Element naviContainer=contentContainer.nextElementSibling().select("ul.naviSide").first();
			naviContainer.html("");
			contentContainer.html("");
			slideTemplate.html("");
			contentTemplate.html("");
			
			int picIndex=0;
			boolean isFirstSlide=true;
			while(picDatas.size()>0){
				Element slide = slideTemplate.clone();
				Element content = contentTemplate.clone();
				slide.appendChild(content);
				Element navi = new Element(Tag.valueOf("li"),String.valueOf((picIndex+itemPerPage)/itemPerPage));
				navi.text(String.valueOf((picIndex+itemPerPage)/itemPerPage));
				if(isFirstSlide){
					slide.addClass("on");
					navi.addClass("on");
				}else{
					slide.removeClass("on");
					navi.removeClass("on");
				}

				for(int i=0;i<itemPerPage;picIndex++){
					Element temp=templateLi.clone();
					if(picDatas.size()<1){//no data there
						break;
					}
					NewsDTO newsItemData=picDatas.remove(0);
					temp.select("a").attr("href", newsItemData.getTitleHref());
					temp.select("img").attr("alt", newsItemData.getTitle());
					temp.select("img").attr("title", newsItemData.getTitle());
					if(temp.select("img").attr("width")!=null){
						try{
							PageData.PIC_WIDTH=Integer.parseInt(temp.select("img").attr("width"));
						}catch(Exception e){}
					}
					if(temp.select("img").attr("height")!=null){
						try{
							PageData.PIC_HEIGHT=Integer.parseInt(temp.select("img").attr("height"));
						}catch(Exception e){}
					}
					int width=PageData.PIC_WIDTH;
					int height=PageData.PIC_HEIGHT;
					int ii=0;
					if(newsItemData.getGroup().equals(GroupIdEnum.nvren.name())) {
						ii=1;
					}
					if(newsItemData.getPicWidths().size()>ii){
						width=PageData.getWidth(newsItemData.getPicWidths().get(ii),newsItemData.getPicHeights().get(ii));
						height=PageData.getHeight(newsItemData.getPicWidths().get(ii),newsItemData.getPicHeights().get(ii));
					}
					String imageURL=null;
					if(newsItemData.getPicURLs().size()>ii){
						imageURL=newsItemData.getPicURLs().get(ii);
					}else{
						if(newsItemData.getPicURLs().size()>ii-1 && ii-1>=0){
							imageURL=newsItemData.getPicURLs().get(ii-1);
						}else{
							continue;
							//temp.select("img").first().attr("init_src", FileSystemConfig.blankPicture);
						}
					}
					if(imageURL==null || imageURL.equals("")) continue;
					temp.select("img").attr("init_src", imageURL);
					if(isFirstSlide){
						temp.select("img").attr("src", imageURL);
					}else{
						temp.select("img").attr("src", "");
					}
					temp.select("img").attr("width", ""+width);
					temp.select("img").attr("height", ""+height);
					temp.select("h2").first().select("a").first().text(PageData.trim(newsItemData.getTitle(),12));	

					content.appendChild(temp);
					i++;
				}
				isFirstSlide=false;
				contentContainer.appendChild(slide);
				naviContainer.appendChild(navi);
			}
		}
		return doc.html();
	}


}

