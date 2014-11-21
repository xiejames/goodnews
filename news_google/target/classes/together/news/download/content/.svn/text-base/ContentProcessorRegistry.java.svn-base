package together.news.download.content;

import java.util.HashMap;

import together.news.download.web.URLRegistry.SiteEnum;

public class ContentProcessorRegistry {
	private static HashMap<String,Class<? extends ContentProcessor>> repository=new HashMap<String,Class<? extends ContentProcessor>>();
	public  static synchronized void register(String url,Class<? extends ContentProcessor> processClz){
		repository.put(url,processClz);
	}
	public static Class<? extends ContentProcessor> getContentProcessor(String url){
		return repository.get(url);
	}
}

