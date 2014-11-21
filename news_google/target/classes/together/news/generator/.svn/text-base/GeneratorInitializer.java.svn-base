package together.news.generator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup;
import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.constant.FileSystemConfig;
import together.news.common.utils.ExchangeDataFormatUtils;
import together.news.common.utils.TogetherFileUtils;
import together.news.generator.content.GenContentProcessor;
import together.news.generator.content.GenContentProcessor4IndexHtml;
import together.news.generator.content.GenContentProcessor4SubHtml;
import together.news.generator.content.GenContentProcessor4TupianHtml;
import together.news.generator.content.GenContentProcessorRegistry;
import together.news.generator.content.GeneratorDataLoader;
import together.news.generator.content.StaticGenContentProcessor;


public class GeneratorInitializer {
	
	protected static Logger logger=Logger.getLogger(GeneratorInitializer.class);
	private static HashMap<GroupIdEnum,NewsGroup> newsGroups=new HashMap<GroupIdEnum,NewsGroup>();
	public static String top_notice="";
	
	public static void init(){
		GroupIdEnum[] groups=GroupIdEnum.values();
		ArrayList<NewsDTO> tupianData=new ArrayList<NewsDTO>();
		for(GroupIdEnum group:groups){
			if(group.isStatic()) continue;
			try {
				List<NewsDTO> data = addANewsGroup(group);
				tupianData.addAll(loadPictureData(data,group));
			} catch (IOException e) {
				newsGroups.put(group,new NewsGroup(group.name(),group.name()+".html",new ArrayList<NewsDTO>()));
				logger.error(group.name() +" data do not existed.");
			}
		}
		newsGroups.put(GroupIdEnum.tupian,
				new NewsGroup(GroupIdEnum.tupian.name(),GroupIdEnum.tupian.name()+".html",tupianData));
		
		try {
			String text=ExchangeDataFormatUtils.toExchangeDataStr(tupianData);
			File saveFile=new File(new File(FileSystemConfig.getMergeFilePath(GroupIdEnum.tupian)).getParent());
			if(saveFile.exists()==false){
				saveFile.mkdirs();
			}
			TogetherFileUtils.write(FileSystemConfig.getMergeFilePath(GroupIdEnum.tupian),text,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(GroupIdEnum group:groups){
			if(group==GroupIdEnum.index) continue;
			if(group.isStatic()) continue;
			registerProcessor(group,GenContentProcessor4SubHtml.class);
		}
		registerProcessor(GroupIdEnum.index,GenContentProcessor4IndexHtml.class);
		registerProcessor(GroupIdEnum.contactus,StaticGenContentProcessor.class);
		registerProcessor(GroupIdEnum.tupian,GenContentProcessor4TupianHtml.class);
	}
	
	private static void registerProcessor(GroupIdEnum pageKey, Class<? extends GenContentProcessor> processClz){
		try{
		Constructor<? extends GenContentProcessor> processConstructor = processClz.getConstructor(GroupIdEnum.class,HashMap.class);
		GenContentProcessor process = processConstructor.newInstance(pageKey,newsGroups);
		GenContentProcessorRegistry.register(pageKey, process);
		}catch(Exception e){throw new RuntimeException(e);}
	}
	private static List<NewsDTO> addANewsGroup(GroupIdEnum key) throws IOException {
		List<NewsDTO> data = GeneratorDataLoader.load(key.name());
		newsGroups.put(key,new NewsGroup(key.name(),key.name()+".html",data));
		return data;
	}
	private static List<NewsDTO> loadPictureData(List<NewsDTO> list, GroupIdEnum group) throws IOException {
		List<NewsDTO> result=new ArrayList<NewsDTO>();
		
		for(NewsDTO item:list){
			if(item.getPicURLs().size()>0){
				result.add(item);
				item.setGroup(group.name());
			}
		}
		return result;
	}

}
