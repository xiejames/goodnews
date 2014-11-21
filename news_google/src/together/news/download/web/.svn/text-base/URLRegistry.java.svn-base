package together.news.download.web;

import java.util.ArrayList;
import java.util.HashMap;

import together.news.common.NewsGroup.GroupIdEnum;

public class URLRegistry {
	
	public static enum SiteEnum{SITE_163, SITE_SOHU, SITE_SINA, SITE_BAIDU, SITE_IFENG}
	public static enum URLType{RSS,HTML,SELENIUM}
	
	private static HashMap<String,SiteEnum> siteURLMapping=new HashMap<String,SiteEnum>();
	private static HashMap<String,URLType> URLTypeMapping=new HashMap<String,URLType>();
	private static HashMap<GroupIdEnum,ArrayList<String>> siteGroupMapping=new HashMap<GroupIdEnum,ArrayList<String>>();
	public  static synchronized void register(SiteEnum site,String url,URLType urlType, GroupIdEnum group){
		siteURLMapping.put(url, site);
		URLTypeMapping.put(url, urlType);
		if(siteGroupMapping.get(group)==null) siteGroupMapping.put(group, new ArrayList<String>());
		if(siteGroupMapping.get(group).contains(url)==false){
			siteGroupMapping.get(group).add(url);
		}
	}
	public static SiteEnum getSite(String url){
		return siteURLMapping.get(url);
	}
	public static URLType getURLType(String url){
		return URLTypeMapping.get(url);
	}
	public static ArrayList<String> getAllURLByGroup(GroupIdEnum group){
		return siteGroupMapping.get(group)==null?new ArrayList<String>():siteGroupMapping.get(group);
	}
}
