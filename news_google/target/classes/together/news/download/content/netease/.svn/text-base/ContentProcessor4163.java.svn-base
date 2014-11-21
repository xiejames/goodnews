package together.news.download.content.netease;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import together.news.common.NewsDTO;
import together.news.download.WebUtils;
import together.news.download.content.common.JsoupContentProcessorWithRSSAtom;

public class ContentProcessor4163 extends JsoupContentProcessorWithRSSAtom{
	

	protected void loadPictureData(List<NewsDTO> list, int max) throws IOException {
		int count=0;
		for(NewsDTO item:list){
			if(count>=max) break;
			Document doc=WebUtils.jsoupGet(item.getTitleHref());
			if(doc ==null) continue;
			Element content = doc.getElementById("endText");
			if(content ==null) continue;
			Elements images = content.getElementsByTag("img");
			while(images!=null && images.size()>0){
				if(images.first().attr("src").endsWith("gif")){
					images.remove(0);
					continue;
				}else{
					logger.debug("find image "+count+images.first());
					item.getPicURLs().add(images.first().attr("src"));
					item.getPicWidths().add(images.first().attr("width"));
					item.getPicHeights().add(images.first().attr("height"));
					images.remove(0);
				}
			}
			count++;
		}
	}
}

