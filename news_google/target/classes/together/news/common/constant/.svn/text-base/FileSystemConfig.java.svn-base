package together.news.common.constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.utils.DateUtils;
import together.news.common.utils.TogetherURLUtils;

public class FileSystemConfig {
	public final static String BASE_FOLDER = "FDownload";
	public final static String template_FOLDER = "html";
	public final static String generate_FOLDER = "htmlGenerated";
	public final static String blankPicture = "img/blank.jpg";


	public static String getDownloadFolderStructure(String url,String group, String site) {
		return BASE_FOLDER+"/"+group+"/"+site+"/"+TogetherURLUtils.MD5(url);
	}
	public static String getDownloadFileName(String site, String url) {
		try {
			url=URLEncoder.encode(url,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String fileName = site+"_"
							+url.substring(url.lastIndexOf("/")+1)
							+"_"
							+DateUtils.formatDate2Str(new Date(), "yyyy_MM_dd_HH_mm_ss")
							+SystemConfig.EXCHANGE_DATA_TYPE;
		return fileName;
	}
	public static String getMergeFilePath(GroupIdEnum group) {
		return BASE_FOLDER+"/"+group.name()+"/"+group.name()+SystemConfig.EXCHANGE_DATA_TYPE;
	}
	public static String getMergeFilePath(String group) {
		return BASE_FOLDER+"/"+group+"/"+group+SystemConfig.EXCHANGE_DATA_TYPE;
	}
}
