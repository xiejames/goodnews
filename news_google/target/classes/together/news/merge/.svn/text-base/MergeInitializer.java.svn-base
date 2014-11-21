package together.news.merge;

import java.io.IOException;
import java.lang.reflect.Constructor;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.merge.content.MergeContentProcessor;
import together.news.merge.content.MergeContentProcessor4IndexHtml;
import together.news.merge.content.MergeContentProcessor4SubHtml;
import together.news.merge.content.MergeContentProcessorRegistry;


public class MergeInitializer {
	
	
	public static void init() throws IOException{
		registerProcessor(GroupIdEnum.index,MergeContentProcessor4IndexHtml.class);
		
		GroupIdEnum[] groups=GroupIdEnum.values();
		for(final GroupIdEnum group:groups){
			if(group==GroupIdEnum.index) continue;
			if(group.isStatic()) continue;
			registerProcessor(group,MergeContentProcessor4SubHtml.class);
		}		

	}
	
	private static void registerProcessor(GroupIdEnum pageKey, Class<? extends MergeContentProcessor> processClz){
		try{
		Constructor<? extends MergeContentProcessor> processConstructor = processClz.getConstructor(GroupIdEnum.class);
		MergeContentProcessor process = processConstructor.newInstance(pageKey);
		MergeContentProcessorRegistry.register(pageKey, process);
		}catch(Exception e){throw new RuntimeException(e);}
	}

}
