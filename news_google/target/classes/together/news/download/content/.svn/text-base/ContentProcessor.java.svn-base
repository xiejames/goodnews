package together.news.download.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import together.news.common.NewsDTO;
import together.news.common.utils.ExchangeDataFormatUtils;

public abstract class ContentProcessor {
	protected static Logger logger=Logger.getLogger(ContentProcessor.class);
	
	public ArrayList<NewsDTO> process(String html){
		ArrayList<NewsDTO> result = loadNews(html);
		try {
			loadPictureData(result,3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}


	protected NewsDTO addANewOne(ArrayList<NewsDTO> result, String title,
			String titleLink, String content, String updateDate) {
		content=content.trim();
		title=title.trim();
		titleLink=titleLink.trim();
		updateDate=updateDate.trim();
		NewsDTO item = new NewsDTO(title, titleLink, updateDate, content);
		result.add(item);
		return item;
	}
	
	protected abstract ArrayList<NewsDTO> loadNews(String html) ;
	protected abstract void loadPictureData(List<NewsDTO> list, int max) throws IOException;

}
