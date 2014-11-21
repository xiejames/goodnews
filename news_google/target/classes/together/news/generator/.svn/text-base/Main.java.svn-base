package together.news.generator;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.download.DownloadInitializer;
import together.news.generator.content.GenContentProcessorRegistry;
import together.news.generator.utils.GeneratorFileUtils;

public class Main {
	/*
	 * 模板内容定义和替换
	 */
	public static void main(String[] args) throws Exception {
		process(GroupIdEnum.index);
		process(GroupIdEnum.guonei);
		process(GroupIdEnum.guoji);
	}
	public static void process(GroupIdEnum pageName) throws Exception {
		DownloadInitializer.init();
		GeneratorInitializer.init();
		
		GeneratorFileUtils.copy("img");
		GeneratorFileUtils.copy("js");
		
		GenContentProcessorRegistry.getContentProcessor(pageName).getResult();
	}


}
