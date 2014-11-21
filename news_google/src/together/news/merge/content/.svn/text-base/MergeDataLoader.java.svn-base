package together.news.merge.content;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.constant.FileSystemConfig;
import together.news.common.utils.ExchangeDataFormatUtils;
import together.news.common.utils.TogetherFileUtils;
import together.news.download.web.URLRegistry;

public class MergeDataLoader {
	protected static Logger logger=Logger.getLogger(MergeDataLoader.class);
	public static List<NewsDTO> load(GroupIdEnum group, File pageName,String dataType) throws IOException{
		String text=TogetherFileUtils.read(pageName,"UTF-8");
		List<NewsDTO> newsList=ExchangeDataFormatUtils.fromExchangeDataStr(text);
		List<NewsDTO> selectedNewsList= new ArrayList<NewsDTO>();
		for(NewsDTO newsItem:newsList){
			String title = trimTitle(newsItem.getTitle());
			String titleLink = trimLink(newsItem.getTitleHref());
			String updateDate = trimDate(newsItem.getUpdateDate());
			String content = trimDate(newsItem.getContent());
			
			if(group.isPage()){
				if(content.equals("")
					||content.length()<title.length()*3
					||content.length()<20){
						continue;
					}
				if(title!=null && title.equals("")==false){
					title=title.trim();
					if( title.contains("......")){
						continue;
					}
				}else{
					continue;
				}
				if(content!=null && content.equals("")==false){

					content=content.trim();
					if(content.length()<10 && content.contains("......"))
						continue;
					if(content.trim().substring(0, 10).contains("a href")){
						content=content.substring(content.indexOf("/a>")+3);
					}
					content=trimContent(content);
					if(content.length()<10 ){
						continue;
					}
					
					if(content.trim().length()>20 && content.trim().substring(0, 20).contains("版权声明")){
						content=content.substring(content.indexOf("日报道")+3);
					}
					content=trimContent(content);
					if(content.length()<10 ){
						continue;
					}
					if(content.trim().length()>20 && content.substring(0,20).indexOf(" ")>0){
						content=content.substring(content.substring(0,20).indexOf(" ")+1);
					}
					content=trimContent(content);
					if(content.length()<10 ){
						continue;
					}
					if(content.trim().length()>20 && content.length()>20 && content.substring(0,20).indexOf(":")>0){
						content=content.substring(content.substring(0,20).indexOf(":")+1);
					}
					content=trimContent(content);
					if(content.length()<10 ){
						continue;
					}
					if(content.length()>20 &&content.substring(0,20).indexOf(",")>0){
						content=content.substring(content.substring(0,20).indexOf(",")+1);
					}
					content=trimContent(content);
					if(content.length()<10 ){
						continue;
					}
					if(title.length()*2>content.length()){
						continue;
					}
				}else{
					continue;
				}			
				if(content.endsWith("......")==false){
					content+="......";
				}
			}else{
				
			}
			newsItem.setTitle(title);
			newsItem.setTitleHref(titleLink);
			newsItem.setContent(content);
			newsItem.setUpdateDate(updateDate);
			selectedNewsList.add(newsItem);
		}
		return selectedNewsList;
	}
	
	
	protected static String number="0123456789";
	protected static String ascii="";
	static{
		for(int i=1;i<128;i++){
			ascii+=(char)i;
		}
	}
	protected static String blockstart="<[{【";
	protected static String blockend=">]}】";
	private static String trimContent(String content) {
//		content.replaceAll("......", "");
		while(true){
			if(content==null || content.equals("")){
				break;
			}else if(ascii.contains(content.substring(0,1))){
				if(number.contains(content.substring(0,1)) && content.length()>2 && ascii.contains(content.substring(1,2))==false){
					break;
				}else{
					content=content.substring(1);
				}
			}else if(blockstart.contains(content.substring(0,1))){
				int index=blockstart.indexOf(content.substring(0,1));
				int end=content.indexOf(blockend.toCharArray()[index]);
				if(end==-1){
					end=0;
				}
				content=content.substring(end+1);
			}
//			else if(blockend.contains(content.substring(content.length()-1))){
//				int index=blockend.indexOf(content.substring(content.length()-1));
//				content=content.substring(0,content.indexOf(blockstart.toCharArray()[index])-1);
//			}
			else if(content.contains("【") && content.contains("】")){
				int index=content.indexOf("【");
				content=content.substring(0,index)+content.substring(content.indexOf("】")+1);
			}else if(ascii.contains(content.substring(content.length()-1))){
				content=content.substring(0,content.length()-1);
			}
			else{
				break;
			}
		}
		
		return content;
	}
	private static String trimDate(String updateDate) {
//		if(updateDate.length()>7)
//			updateDate=updateDate.substring(7);
//		if(updateDate.length()>10)
//			updateDate=updateDate.substring(0,updateDate.length()-10);
		return updateDate;
	}

	private static String trimTitle(String linkText) {
		int start=linkText.indexOf("[");
		int end=linkText.indexOf("]");
		if(start!=-1 && end!=-1 && start<end){
			linkText=linkText.substring(0,start)+linkText.substring(end+1);
		}
		start=linkText.indexOf("(");
		end=linkText.indexOf(")");
		if(start!=-1 && end!=-1 && start<end){
			linkText=linkText.substring(0,start)+linkText.substring(end+1);
		}
		start=linkText.indexOf("【");
		end=linkText.indexOf("】");
		if(start!=-1 && end!=-1 && start<end){
			linkText=linkText.substring(0,start)+linkText.substring(end+1);
		}
		return linkText;
	}

	private static String trimLink(String linkHref) {
		  return linkHref;
	}
	public static File getDownloadFilePath(GroupIdEnum group, String url) {
			String site=URLRegistry.getSite(url).name();
			File baseFolder= new File(FileSystemConfig.getDownloadFolderStructure(url, group.name(), site));
			File[] allFiles = baseFolder.listFiles();
			if(allFiles!=null && allFiles.length>0){
				return allFiles[allFiles.length-1];
			}else{
				return null;
			}
		}
}
