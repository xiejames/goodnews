package together.news.generator.content;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup;
import together.news.common.NewsGroup.GroupIdEnum;

public class StaticGenContentProcessor extends GenContentProcessor{
	
	public StaticGenContentProcessor(GroupIdEnum pageName,HashMap<GroupIdEnum, NewsGroup> newsGroups) {
		super(pageName,newsGroups,"GB2312");
	}

	@Override
	protected String getView(String tempHtml,HashMap<String,List<NewsDTO>>  pageData) throws IOException {
		return tempHtml;
	}

	@Override
	protected HashMap<String, List<NewsDTO>> loadPageData(GroupIdEnum pageName,HashMap<GroupIdEnum, NewsGroup> newsGroups) throws IOException {
		return null;
	}
	
}

