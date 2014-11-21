package together.news.merge.content;

import java.util.HashMap;

import together.news.common.NewsGroup.GroupIdEnum;

public class MergeContentProcessorRegistry {
	private static HashMap<GroupIdEnum,MergeContentProcessor> 
						repository=new HashMap<GroupIdEnum,MergeContentProcessor>();
	
	public  static synchronized void register(GroupIdEnum groupId,MergeContentProcessor processClz){
		repository.put(groupId,processClz);
	}
	public static MergeContentProcessor getContentProcessor(GroupIdEnum groupId){
		return repository.get(groupId);
	}
}

