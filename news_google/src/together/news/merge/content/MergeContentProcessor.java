package together.news.merge.content;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.constant.FileSystemConfig;
import together.news.common.utils.ExchangeDataFormatUtils;
import together.news.common.utils.TogetherFileUtils;
import together.news.download.web.URLRegistry;

public abstract class MergeContentProcessor {
	protected static Logger logger=Logger.getLogger(MergeContentProcessor.class);
	protected HashMap<String, List<NewsDTO>> pageData;
	protected String result=null;
	protected GroupIdEnum pageName;
	public int maxItemPerGroup=100;
	
	public MergeContentProcessor(GroupIdEnum pageName){
		this.pageName=pageName;
	}
	public String merge() throws IOException{
		if(result!=null) return result;
		
		//load data
		pageData = orgnizePageData(pageName);
		//data and data-->merge
		List<NewsDTO> mergeData = process(pageData);
		maxItemPerGroup=maxItemPerGroup>mergeData.size()-1?mergeData.size()-1:maxItemPerGroup;
		if(maxItemPerGroup<0) return result;
		this.result=ExchangeDataFormatUtils.toExchangeDataStr(mergeData.subList(0, maxItemPerGroup));
		//write to File
		logger.debug(result);
		TogetherFileUtils.write(FileSystemConfig.getMergeFilePath(pageName.name()),result,"UTF-8");
		return result;
	}
	
	protected HashMap<String, List<NewsDTO>> orgnizePageData(GroupIdEnum group) throws IOException{
		HashMap<String, List<NewsDTO>> result=new HashMap<String, List<NewsDTO>>();
		ArrayList<String> urls = URLRegistry.getAllURLByGroup(group);
		for(String url:urls){
			File latestFile = MergeDataLoader.getDownloadFilePath(group,url);

			if(latestFile!=null){
				if(group==GroupIdEnum.tupian){
					result.put(latestFile.getName(), processDownload4Tupian(MergeDataLoader.load(group,latestFile, "UTF-8")));
				}else if(group==GroupIdEnum.qiche){
					result.put(latestFile.getName(), MergeDataLoader.load(group,latestFile, "UTF-8"));
				}else{
					result.put(latestFile.getName(), MergeDataLoader.load(group,latestFile, "UTF-8"));
				}
			}
		}
		return result;
	}

	private static List<NewsDTO> processDownload4Tupian(List<NewsDTO> list) throws IOException {//TODO find a way to download image
//		for(NewsDTO item:list){
//			String link=item.getTitleHref();
//			if(link==null || link.equals("")) continue;
//			String htmlStr=WebUtils.jsoupGet(link);
////			Document doc = Jsoup.parse(htmlStr);
////			images=doc.getElementsByTag("img");
//		}
		return list;
	}
	
	protected abstract List<NewsDTO> process(HashMap<String, List<NewsDTO>> pageData) throws IOException;
}
