package together.news.generator.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup;
import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.constant.FileSystemConfig;
import together.news.common.constant.PageData;

public class GenContentProcessor4IndexHtml extends GenContentProcessor{
	protected static Logger logger=Logger.getLogger(GenContentProcessor4IndexHtml.class);

	private static final java.lang.String FILM_PICS = "FILM_PICS";
	private static final java.lang.String TOP_TOPIC = "TOP_TOPIC";
	private static final java.lang.String TOP_NEWS = "TOP_NEWS";
	private static final java.lang.String NEWS_LIST = "NEWS_LIST";

	public GenContentProcessor4IndexHtml(GroupIdEnum pageName,HashMap<GroupIdEnum, NewsGroup> newsGroups) {
		super(pageName,newsGroups,"GB2312");
	}

	protected String getView(String tempHtml,HashMap<String,List<NewsDTO>>  pageData) throws IOException {
		return setValueToTemplate(tempHtml,pageData);
	}

	@Override
	protected HashMap<String, List<NewsDTO>> loadPageData(GroupIdEnum pageName,HashMap<GroupIdEnum, NewsGroup> newsGroups) throws IOException {
		HashMap<String,List<NewsDTO>> newsDataMap=new HashMap<String,List<NewsDTO>>();
		List<NewsDTO> newsList=newsGroups.get(GroupIdEnum.index).getAllNews();
		List<NewsDTO> picNewsList=newsGroups.get(GroupIdEnum.tupian).getAllNews();
		newsDataMap.put(FILM_PICS, picNewsList);
		if(newsList.size()<12){
			newsDataMap.put(TOP_TOPIC,new ArrayList<NewsDTO>() );
			newsDataMap.put(TOP_NEWS, new ArrayList<NewsDTO>() );
			newsDataMap.put(NEWS_LIST, new ArrayList<NewsDTO>() );
		}else{
			newsDataMap.put(TOP_TOPIC,picNewsList);
			int random1=(int)(Math.random()*(newsList.size()-3));
			newsDataMap.put(TOP_NEWS, newsList.subList(0+random1, 3+random1));
			int random2=(int)(Math.random()*(newsList.size()-12));
			newsDataMap.put(NEWS_LIST, newsList.subList(4+random2, 12+random2));
		}

		GroupIdEnum[] groups=GroupIdEnum.values();
		for(GroupIdEnum group:groups){
			if(group.isStatic()) continue;
			if( newsGroups.get(group)==null){
				System.err.println(group.name() + " has no data to generate...");
			}else{
				newsDataMap.put(group.name(), newsGroups.get(group).getAllNews());
			}
		}
		return newsDataMap;
	}


	private static String setValueToTemplate(String html, HashMap<String,List<NewsDTO>> newsData) throws IOException {
		Document doc = Jsoup.parse(html);
		setArea1Left(doc,newsData);
		setArea1Center(doc,newsData);
		setArea1Right(doc,newsData);
		setGroupsSummaryAreas(doc,newsData);
		return doc.html();
	}

	private static void setGroupsSummaryAreas(Document doc, HashMap<String, List<NewsDTO>> newsData) {
		int groupAdded=0;
		Element currentParent=doc.getElementById("area20");
		Element current=null;
		GroupIdEnum[] groups=GroupIdEnum.values();
		for(int i=0;i<groups.length;i++){
			if(groups[i].isStatic()) continue;
			if(groups[i]==GroupIdEnum.index) continue;
			if(groups[i].isNaviGroup()==false) continue;
			Element groupTemplateL=currentParent.select("div.halfAreaL").first();
			Element groupTemplateR=currentParent.select("div.halfAreaR").first();
			if(groupAdded==0){
				current=groupTemplateL;
			}else if(groupAdded==1){
				current=groupTemplateR;
			}else if(groupAdded%2==0){
				Element newSection = currentParent.clone();
				newSection.attr("id", "area2"+groupAdded/2);
				currentParent.after(newSection);
				currentParent=newSection;
				current=currentParent.select("div.halfAreaL").first();
			}else{
				current=currentParent.select("div.halfAreaR").first();
			}
			current.select("div.titleBar").first().select("h2").first().text(groups[i].getText());
			current.select("div.titleBar").first().select("a").first().attr("href",groups[i].name()+".html?"+Math.random());
			List<NewsDTO> newslistData = newsData.get(groups[i].name());
			if(newslistData==null) continue;
			Elements newsNodes = current.select("ul.newslist").first().select("li");
			for(int n=0;n<newsNodes.size() &&  n<newslistData.size();n++){
				newsNodes.get(n).select("a").first().attr("href",newslistData.get(n).getTitleHref());
				newsNodes.get(n).select("a").first().text(newslistData.get(n).getTitle());
				newsNodes.get(n).select("span").first().html(PageData.trim(newslistData.get(n).getContent(),PageData.NEWS_LIST_CONTENT_MAX-newslistData.get(n).getTitle().length()));
			}
			
			groupAdded++;
		}

		
	}

	private static void setArea1Right(Document doc,
			HashMap<String, List<NewsDTO>> newsData) {
		Element area1 = doc.getElementById("area1");
		Element container= area1.select("div.rightArea").first();
		//set first content data
		
		List<NewsDTO> firstHotsData = newsData.get(GroupIdEnum.hotword.name());
		Element firstTitleBar = container.select("div.titleBar").first();
		firstTitleBar.select("h2").first().text(GroupIdEnum.hotword.getText());
		int random1=(int) (Math.random()*firstHotsData.size());
		int tryTime1=0;
		while(tryTime1++<firstHotsData.size() && firstHotsData.get(random1).getTitle().length()>5){
			random1=(int) (Math.random()*firstHotsData.size());
		}
		if(firstHotsData==null || firstHotsData.size()==0){
			firstTitleBar.select("a").first().text("");
			firstTitleBar.select("a").first().attr("href","");
		}else{
			firstTitleBar.select("a").first().text(firstHotsData.get(random1).getTitle());
			firstTitleBar.select("a").first().attr("href",firstHotsData.get(random1).getTitleHref());
			firstTitleBar.select("a").first().attr("title",firstHotsData.get(random1).getTitle());
		}
		Elements firstHots = container.select("div.box").first().select("li");
		for(int i=0;i<firstHots.size() && i<firstHotsData.size();i++){
			firstHots.get(i).select("a").attr("href",firstHotsData.get(i).getTitleHref());
			firstHots.get(i).select("a").attr("title",firstHotsData.get(i).getTitle());
			firstHots.get(i).select("a").first().text(PageData.trim(firstHotsData.get(i).getTitle(),10));
		}
		//set second content data
		List<NewsDTO> secondHotsData = newsData.get(GroupIdEnum.hotword_selected.name());
		Element secondTitleBar = container.select("div.titleBar").last();
		secondTitleBar.select("h2").first().text(GroupIdEnum.hotword_selected.getText());
		int random2=(int) (Math.random()*secondHotsData.size());
		int tryTime2=0;
		while(tryTime2++<secondHotsData.size() && secondHotsData.get(random2).getTitle().length()>5){
			random2=(int) (Math.random()*secondHotsData.size());
		}
		if(secondHotsData==null || secondHotsData.size()==0){
			secondTitleBar.select("a").first().text("");
			secondTitleBar.select("a").first().attr("href","");
		}else{
			secondTitleBar.select("a").first().text(secondHotsData.get(random2).getTitle());
			secondTitleBar.select("a").first().attr("href",secondHotsData.get(random2).getTitleHref());
			secondTitleBar.select("a").first().attr("title",secondHotsData.get(random2).getTitle());
		}

		Elements secondHots = container.select("div.box").last().select("li");
		for(int i=0;i<secondHots.size() && i<secondHotsData.size();i++){
			secondHots.get(i).select("a").attr("href",secondHotsData.get(i).getTitleHref());
			secondHots.get(i).select("a").attr("title",secondHotsData.get(i).getTitle());
			secondHots.get(i).select("a").first().text(PageData.trim(secondHotsData.get(i).getTitle(),10));
		}
	}

	private static void setArea1Center(Document doc,
			HashMap<String, List<NewsDTO>> newsData) {
		Element area1 = doc.getElementById("area1");
		Elements leftNodes= area1.select("div.middArea");
		//set first content data
		Elements topnews = leftNodes.select("div.topnews");
		List<NewsDTO> topnewsData = newsData.get(TOP_NEWS);
		for(int i=0;i<topnews.size() && i<topnewsData.size();i++){
			topnews.get(i).select("a").attr("href",topnewsData.get(i).getTitleHref());
			topnews.get(i).select("a").first().text(PageData.trim(topnewsData.get(i).getTitle(),21));
			topnews.get(i).select("span.topnewsctnt").first().html(PageData.trim(topnewsData.get(i).getContent(),PageData.TOP_NEWS_CONTENT_MAX));
		}
		//set second content data
		Elements newslist = leftNodes.select("ul.newslist").first().select("li");
		List<NewsDTO> newslistData = newsData.get(NEWS_LIST);
		for(int i=0;i<newslist.size() && i<newslistData.size();i++){
			newslist.get(i).select("a").attr("href",newslistData.get(i).getTitleHref());
			newslist.get(i).select("a").first().text(newslistData.get(i).getTitle());
			newslist.get(i).select("span").first().html(PageData.trim(newslistData.get(i).getContent(),PageData.NEWS_LIST_CONTENT_MAX-newslistData.get(i).getTitle().length()));
		}
		
	}

	private static void setArea1Left(Document doc, HashMap<String,List<NewsDTO>> newsData) {
		Element area1 = doc.getElementById("area1");
		Elements leftNodes= area1.select("div.leftArea");
		//set the film picture data
		List<NewsDTO> filmPicsData = newsData.get(FILM_PICS);
		Elements filmPics = leftNodes.first().select("div.focusPic").first().select("div.normal");
		for(int i=0;i<filmPics.size() && i< filmPicsData.size();i++){
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
			if(filmPicsData.get(i).getPicWidths().size()>0){
				width=PageData.getWidth(filmPicsData.get(i).getPicWidths().get(0),filmPicsData.get(i).getPicHeights().get(0));
				height=PageData.getHeight(filmPicsData.get(i).getPicWidths().get(0),filmPicsData.get(i).getPicHeights().get(0));
			}
			if(filmPicsData.get(i).getPicURLs().size()>0){
				filmPics.get(i).select("img").attr("src", filmPicsData.get(i).getPicURLs().get(0));
			}else{
				filmPics.get(i).select("img").attr("src", FileSystemConfig.blankPicture);
			}
			filmPics.get(i).select("img").attr("width", ""+width);
			filmPics.get(i).select("img").attr("height", ""+height);
			filmPics.get(i).select("h2").first().select("a").first().text(filmPicsData.get(i).getTitle());
		}
		//set the top topic
		Element topTopic=leftNodes.select("div.box").first();
		List<NewsDTO> topTopicData = newsData.get(TOP_TOPIC);
		if(topTopicData==null || topTopicData.size()==0) return;
		int random1=(int)(Math.random()*(topTopicData.size()-1));
		int reTry=topTopicData.size();
		while(topTopicData.get(random1).getContent().length()<100 && reTry-->0){
			random1=(int)(Math.random()*(topTopicData.size()-1));
		}
		topTopic.select("img").attr("alt", topTopicData.get(random1).getTitle());
		filmPics.select("img").attr("title", topTopicData.get(random1).getTitle());
		if(topTopicData.get(random1).getPicURLs().size()>0){
			topTopic.select("img").attr("src", topTopicData.get(random1).getPicURLs().get(0));
		}else{
			topTopic.select("img").attr("src", FileSystemConfig.blankPicture);
		}
		topTopic.getElementsByTag("h2").first().select("a").first().text(PageData.trim(topTopicData.get(random1).getTitle(),20));
		topTopic.select("span.boxtext").first().html(PageData.trim(topTopicData.get(random1).getContent(),100));
		topTopic.select("a").attr("href",topTopicData.get(random1).getTitleHref());
	}
}

