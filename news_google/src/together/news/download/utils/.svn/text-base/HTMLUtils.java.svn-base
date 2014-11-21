package together.news.download.utils;

import java.util.ArrayList;

import together.news.common.NewsDTO;
import together.news.common.utils.ExchangeDataFormatUtils;
import together.news.download.content.ContentProcessor;
import together.news.download.content.ContentProcessorRegistry;
import together.news.download.context.DownloadContext;

public class HTMLUtils {
	public static String filter(String html) throws InstantiationException, IllegalAccessException  {
        ArrayList<NewsDTO> result = siteContentProcessor(html);
        return ExchangeDataFormatUtils.toExchangeDataStr(result);
	}

	private static ArrayList<NewsDTO> siteContentProcessor(String html) throws InstantiationException, IllegalAccessException {
		Class<? extends ContentProcessor> processor = ContentProcessorRegistry.getContentProcessor(DownloadContext.getContext().getURL());
		return processor.newInstance().process(html);
		
	}
	
}
