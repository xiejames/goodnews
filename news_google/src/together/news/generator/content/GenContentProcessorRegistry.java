package together.news.generator.content;

import java.util.HashMap;

import together.news.common.NewsGroup.GroupIdEnum;

public class GenContentProcessorRegistry {
	private static HashMap<GroupIdEnum,GenContentProcessor> 
						repository=new HashMap<GroupIdEnum,GenContentProcessor>();
	
	public  static synchronized void register(GroupIdEnum groupId,GenContentProcessor processClz){
		repository.put(groupId,processClz);
	}
	public static GenContentProcessor getContentProcessor(GroupIdEnum groupId){
		return repository.get(groupId);
	}
}

