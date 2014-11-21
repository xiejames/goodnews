package together.news.download.init;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.download.content.ifeng.ContentProcessor4ifeng;
import together.news.download.web.URLRegistry.SiteEnum;
import together.news.download.web.URLRegistry.URLType;

public class IfengInitializer extends CommonInitializer{

	public static void init() {
		register(GroupIdEnum.index,SiteEnum.SITE_IFENG,"http://news.ifeng.com/rss/index.xml",URLType.HTML,ContentProcessor4ifeng.class);
		register(GroupIdEnum.guonei,SiteEnum.SITE_IFENG,"http://news.ifeng.com/rss/mainland.xml",URLType.HTML,ContentProcessor4ifeng.class);
		register(GroupIdEnum.guonei,SiteEnum.SITE_IFENG,"http://news.ifeng.com/rss/taiwan.xml",URLType.HTML,ContentProcessor4ifeng.class);
		register(GroupIdEnum.guoji,SiteEnum.SITE_IFENG,"http://news.ifeng.com/rss/world.xml",URLType.HTML,ContentProcessor4ifeng.class);
		register(GroupIdEnum.shehui,SiteEnum.SITE_IFENG,"http://news.ifeng.com/rss/society.xml",URLType.HTML,ContentProcessor4ifeng.class);
	}

}
