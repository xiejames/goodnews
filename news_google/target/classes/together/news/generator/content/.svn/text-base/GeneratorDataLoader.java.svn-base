package together.news.generator.content;

import java.io.IOException;
import java.util.List;

import together.news.common.NewsDTO;
import together.news.common.utils.ExchangeDataFormatUtils;
import together.news.generator.utils.GeneratorFileUtils;

public class GeneratorDataLoader {
	public static List<NewsDTO> load(String pageName) throws IOException{
		String text=GeneratorFileUtils.readPageDataFile(pageName,"UTF-8");
		return ExchangeDataFormatUtils.fromExchangeDataStr(text);
	}
}
