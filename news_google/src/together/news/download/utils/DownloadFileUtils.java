package together.news.download.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import together.news.common.constant.FileSystemConfig;
import together.news.common.utils.DateUtils;
import together.news.common.utils.TogetherURLUtils;
import together.news.download.context.DownloadContext;
import together.news.download.web.URLRegistry;

public class DownloadFileUtils {
	public static int maxFileCacheForAURL=10;
	
	public static void saveDownLoadText(String text) throws IOException {
		File saveFile=maintainDownloadFileSystem();
		FileOutputStream stream = new FileOutputStream(saveFile);
		OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
		writer.write(text);
		writer.flush();
		writer.close();
	}

	private static File maintainDownloadFileSystem() throws IOException {
		String url=DownloadContext.getContext().getURL();
		String site=URLRegistry.getSite(url).name();
		String group=DownloadContext.getContext().getGroup();
		File siteFile = new File(FileSystemConfig.getDownloadFolderStructure(url,group,site));
		if (siteFile.exists() == false) {
			siteFile.mkdirs();
		}
		File[] dataFiles=siteFile.listFiles();
		for(int i=0;dataFiles!=null && i<dataFiles.length-maxFileCacheForAURL;i++){
			dataFiles[i].delete();
		}
		String currentFileName=getCurrentDownloadFileName();
		File saveFile = new File(siteFile.getAbsolutePath()+"/"+currentFileName);
		if (saveFile.exists() == false) {
			saveFile.createNewFile();
		}
		return saveFile;
	}

	private static String getCurrentDownloadFileName() {
		String url=DownloadContext.getContext().getURL();
		if(url==null) return "empty.url.txt";
		String site=URLRegistry.getSite(url).name();
		return FileSystemConfig.getDownloadFileName(site,url);
	}

}
