package together.news.generator.content;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup;
import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.constant.FileSystemConfig;
import together.news.common.constant.PageData;

public class GenContentProcessor4SubHtml extends GenContentProcessor{
	
	private int itemPerPage = 15;
	private static final java.lang.String FILM_PICS = "FILM_PICS";

	public GenContentProcessor4SubHtml(GroupIdEnum pageName,HashMap<GroupIdEnum, NewsGroup> newsGroups) {
		super(pageName,newsGroups,"GB2312");
	}
	protected void init() {
		templateFileName="sub.html";
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
		newsDataMap.put(FILM_PICS, loadPictureData(newsGroups.get(pageName).getAllNews(),3));
		return newsDataMap;
	}
	
	private  String setValueToTemplate(String html, HashMap<String,List<NewsDTO>> newsData) throws IOException {
		Document doc = Jsoup.parse(html);
		if(newsData.get(pageName.name())==null){
			Element container = doc.select("div.area").first();
			Element contentContainer=container.select("div.box").first();
			contentContainer.html("no data there");
		}else{
			setArea1Left(doc,newsData);
			if(newsData.get(FILM_PICS).size()==0){
				doc.select("div.rightArea").first().remove();
				doc.select("div.leftArea").first().removeClass("leftArea");
			}else{
				setArea1Right(doc,newsData);
			}
		}
		return doc.html();
	}

	private  void setArea1Left(Document doc,
			HashMap<String, List<NewsDTO>> newsDataMap) {
		Element container = doc.select("div.leftArea").first();
		container.select("div#subnavi").first().select("span").first().html(pageName.getText());
		container.select("div.titleBar").first().select("h2").first().text(pageName.getText());
		container.select("div.titleBar").first().select("a").first().attr("href",pageName+".html?"+Math.random());
		Element contentContainer=container.select("div.box").first();
		Element contentTemplate=contentContainer.child(0).clone();
		Element naviContainerTemplate=contentContainer.child(0).lastElementSibling().clone();
		Element naviTemplate=naviContainerTemplate.select("ul").first();
		if(contentTemplate.select("li").size()!=0){
			itemPerPage=contentTemplate.select("li").size();
		}
		contentContainer.html("");
		naviTemplate.html("");
		List<NewsDTO> newsData=newsDataMap.get(pageName.name());

		for(int i=0;i<newsData.size();i+=itemPerPage){
			Element content = contentTemplate.clone();
			Element navi = new Element(Tag.valueOf("li"),String.valueOf((i+itemPerPage)/itemPerPage));
			navi.text(String.valueOf((i+itemPerPage)/itemPerPage));
			
			if(i==0){
				content.addClass("on");
				navi.addClass("on");
			}else{
				content.removeClass("on");
				navi.removeClass("on");
			}
			int stepIndex=i+itemPerPage>newsData.size()?newsData.size():i+itemPerPage;
			if(i>stepIndex) continue;
			List<NewsDTO> newslistData = newsData.subList(i, stepIndex);
			Element newsNodeContainer = content.select("ul.newslist").first();
			Element newsNodeTemplate = newsNodeContainer.child(0).clone();
			newsNodeContainer.html("");
			for(int n=0;n<newslistData.size();n++){
				Element newsNode=newsNodeTemplate.clone();
				newsNode.select("a").attr("href",newslistData.get(n).getTitleHref());
				newsNode.select("a").first().text(newslistData.get(n).getTitle());
//				newsNode.select("span").first().text(PageData.trim(newslistData.get(n).getContent(),NEWS_LIST_CONTENT_MAX-newslistData.get(n).getTitle().length()));
				newsNode.select("span").first().html(newslistData.get(n).getUpdateDate());
				newsNode.select("span").last().html(newslistData.get(n).getContent());
				newsNodeContainer.appendChild(newsNode);
			}
			for(int nn=0;nn<i+itemPerPage-newsData.size();nn++){
				Element newsNode=newsNodeTemplate.clone();
				newsNode.select("a").attr("href","");
				newsNode.select("a").first().text("");
				newsNode.select("a").last().text("");
				newsNode.select("span").first().html("");
				newsNode.select("span").last().html("");
				newsNode.removeClass("licontent");
				newsNodeContainer.appendChild(newsNode);
			}
			contentContainer.appendChild(content);
			naviTemplate.appendChild(navi);
		}
		contentContainer.appendChild(naviContainerTemplate);
	}

	private  void setArea1Right(Document doc,
			HashMap<String, List<NewsDTO>> newsData) {
		Element container = doc.select("div.rightArea").first();
		Element titleBar = container.select("div.titleBar").first();
		titleBar.select("h2").first().select("span").first().text(pageName.getText());
		titleBar.select("a").first().attr("href",GroupIdEnum.tupian.name()+".html?"+Math.random());
		Elements filmPics = container.select("li");
		List<NewsDTO> filmPicsData = newsData.get(FILM_PICS);
		for(int i=0;i<filmPics.size();i++){
			if(i<filmPicsData.size()){
				filmPics.get(i).select("a").attr("href", filmPicsData.get(i).getTitleHref());
				filmPics.get(i).select("img").attr("alt", filmPicsData.get(i).getTitle());
				filmPics.get(i).select("img").attr("title", filmPicsData.get(i).getTitle());
				if(filmPics.get(i).select("img").attr("width")!=null){
					try{
						PageData.PIC_WIDTH=Integer.parseInt(filmPics.get(i).select("img").attr("width"));
					}catch(Exception e){}
				}
				if(filmPics.get(i).select("img").attr("height")!=null){
					try{
						PageData.PIC_HEIGHT=Integer.parseInt(filmPics.get(i).select("img").attr("height"));
					}catch(Exception e){}
				}
				int width=PageData.PIC_WIDTH;
				int height=PageData.PIC_HEIGHT;
				int picIndex=0;
				if(pageName==GroupIdEnum.nvren) {
					picIndex=1;
				}
				if(filmPicsData.get(i).getPicWidths().size()>picIndex){
					width=PageData.getWidth(filmPicsData.get(i).getPicWidths().get(picIndex),filmPicsData.get(i).getPicHeights().get(picIndex));
					height=PageData.getHeight(filmPicsData.get(i).getPicWidths().get(picIndex),filmPicsData.get(i).getPicHeights().get(picIndex));
				}
				if(filmPicsData.get(i).getPicURLs().size()>picIndex){
					filmPics.get(i).select("img").attr("src", filmPicsData.get(i).getPicURLs().get(picIndex));
				}else{
					filmPics.get(i).select("img").attr("src", FileSystemConfig.blankPicture);
				}
				filmPics.get(i).select("img").attr("width", ""+width);
				filmPics.get(i).select("img").attr("height", ""+height);
				filmPics.get(i).select("h2").first().select("a").first().text(filmPicsData.get(i).getTitle());	
			}else{
				filmPics.get(i).remove();
			}

		}
	}


}

