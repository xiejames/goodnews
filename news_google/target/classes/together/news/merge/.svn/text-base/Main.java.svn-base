package together.news.merge;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.download.DownloadInitializer;
import together.news.generator.utils.GeneratorFileUtils;
import together.news.merge.content.MergeContentProcessorRegistry;

public class Main {
	/*
	 * 模板内容归并
	 */
	public static void main(String[] args) throws Exception {
		process(GroupIdEnum.index);
		process(GroupIdEnum.guonei);
		process(GroupIdEnum.guoji);
	}
	public static void process(GroupIdEnum pageName) throws Exception {
		DownloadInitializer.init();
		MergeInitializer.init();
		
		MergeContentProcessorRegistry.getContentProcessor(pageName).merge();
	}


}
