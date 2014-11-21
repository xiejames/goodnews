package together.news.download;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.download.content.ContentProcessor;
import together.news.download.content.ContentProcessorRegistry;
import together.news.download.content.baidu.ContentProcessor4Baidu;
import together.news.download.content.baidu.ContentProcessor4Baidu_shehui;
import together.news.download.content.baidu.ContentProcessor4Baidu_yule;
import together.news.download.email.EmailRegistry;
import together.news.download.email.EmailRegistry.Email;
import together.news.download.init.IfengInitializer;
import together.news.download.init.NeteaseInitializer;
import together.news.download.init.SinaInitializer;
import together.news.download.init.SohuInitializer;
import together.news.download.web.URLRegistry;
import together.news.download.web.URLRegistry.SiteEnum;
import together.news.download.web.URLRegistry.URLType;

public class DownloadInitializer {
	public static void init(){
		WebUtils.init();
		initWeb();
		initEmail();
		
	}

	private static void initEmail() {
		EmailRegistry.register(GroupIdEnum.index, new Email("126","together_index","Service0001"));
		EmailRegistry.register(GroupIdEnum.index, new Email("126","together_admin","Service0001"));
	}

	private static void initWeb() {
//		//baidu Initializer
		register(GroupIdEnum.hotword,SiteEnum.SITE_BAIDU,"http://news.baidu.com/z/hotquery/roll/",URLType.HTML,ContentProcessor4Baidu.class);
		register(GroupIdEnum.hotword_selected,SiteEnum.SITE_BAIDU,"http://news.baidu.com/n?cmd=1&class=socianews&pn=1&from=tab",URLType.HTML,ContentProcessor4Baidu_shehui.class);
		register(GroupIdEnum.hotword_selected,SiteEnum.SITE_BAIDU,"http://news.baidu.com/n?cmd=1&class=enternews&pn=1&from=tab",URLType.HTML,ContentProcessor4Baidu_yule.class);
		NeteaseInitializer.init();
		SohuInitializer.init();
		SinaInitializer.init();
		IfengInitializer.init();
		
	}

	public static void register(GroupIdEnum group, SiteEnum site,String url,
			URLType urlType, Class<? extends ContentProcessor> processClz) {
		URLRegistry.register(site, url,urlType,group);
		ContentProcessorRegistry.register(url, processClz);
	}
}
