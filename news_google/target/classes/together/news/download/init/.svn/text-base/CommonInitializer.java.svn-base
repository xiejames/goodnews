package together.news.download.init;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.download.content.ContentProcessor;
import together.news.download.content.ContentProcessorRegistry;
import together.news.download.web.URLRegistry;
import together.news.download.web.URLRegistry.SiteEnum;
import together.news.download.web.URLRegistry.URLType;

public class CommonInitializer {

	public static void register(GroupIdEnum group, SiteEnum site,String url,
			URLType urlType, Class<? extends ContentProcessor> processClz) {
		URLRegistry.register(site, url,urlType,group);
		ContentProcessorRegistry.register(url, processClz);
	}
}
