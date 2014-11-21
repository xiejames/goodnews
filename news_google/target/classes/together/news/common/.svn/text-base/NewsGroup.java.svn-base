package together.news.common;

import java.util.ArrayList;
import java.util.List;

public class NewsGroup{
	public static String APP_NAME="聚合（新闻）";
	public static enum GroupIdEnum {
		index("首页"),
		guonei("国内"),guoji("国际"),junshi("军事"),
		caijing("财经"),fangchan("房产"),qiche("汽车"),
		shehui("社会"),nvren("女人"),jiaoyu("教育"),
		yule("娱乐"),tiyu("体育"),
		keji("科技"),youxi("游戏"),hulianwang("互联网"),
		contactus("联系我们",true,false), 
		tupian("图片",true,true,true), 
		hotword("热门搜词",false,false,false), 
		hotword_selected("精选热搜词",false,false,false), 
		;
		private String text;
		private boolean isPage=true;
		private boolean isStatic=false;
		private boolean isNaviGroup=true;
		GroupIdEnum(String text){
			this.text=text;
		}
		GroupIdEnum(String text,boolean isStatic,boolean isNaviGroup){
			this.text=text;
			this.isStatic=isStatic;
			this.isNaviGroup=isNaviGroup;
		}
		GroupIdEnum(String text,boolean isStatic,boolean isNaviGroup,boolean isPage){
			this.text=text;
			this.isPage=isPage;
			this.isStatic=isStatic;
			this.isNaviGroup=isNaviGroup;
		}
		public String getText(){return text;}
		public boolean isPage(){return isPage;}
		public boolean isStatic(){return isStatic;}
		public boolean isNaviGroup(){return isNaviGroup;}
	}
	
	private String groupName;
	private String groupPageLink;
	private List<NewsDTO> topNews=new ArrayList<NewsDTO>();
	private List<NewsDTO> allNews=new ArrayList<NewsDTO>();
	public int topNewsSize=8;
	
	public NewsGroup(){}

	public NewsGroup(String groupName, String groupPageLink,List<NewsDTO> allNews) {
		super();
		this.groupName = groupName;
		this.groupPageLink = groupPageLink;
		if(allNews!=null && allNews.size()>0){
			topNewsSize=topNewsSize>allNews.size()-1?allNews.size()-1:topNewsSize;
			this.topNews = allNews.subList(0, topNewsSize);
			this.allNews = allNews;
		}

	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupPageLink() {
		return groupPageLink;
	}

	public void setGroupPageLink(String groupPageLink) {
		this.groupPageLink = groupPageLink;
	}

	public List<NewsDTO> getTopNews() {
		return topNews;
	}

	public void setTopNews(List<NewsDTO> topNews) {
		this.topNews = topNews;
	}

	public List<NewsDTO> getAllNews() {
		return allNews;
	}

	public void setAllNews(List<NewsDTO> allNews) {
		this.allNews = allNews;
	}

}
