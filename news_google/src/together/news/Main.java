package together.news;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.constant.SystemConfig;
import together.news.download.DownloadInitializer;
import together.news.download.WebUtils;
import together.news.download.context.DownloadContext;
import together.news.download.web.URLRegistry;
import together.news.generator.GeneratorInitializer;
import together.news.generator.content.GenContentProcessorRegistry;
import together.news.generator.utils.GeneratorFileUtils;
import together.news.merge.MergeInitializer;
import together.news.merge.content.MergeContentProcessorRegistry;

public class Main {
	public static boolean toBeContinued=false;
	private static Logger logger=Logger.getLogger(Main.class);
	/*
	 * 模板内容download+merge+generator
	 */
	public static void main(String[] args) throws Exception {
		SystemConfig.DEFAULT_EDTYPE=SystemConfig.EDTypeEnum.JSON;
		SystemConfig.init();
		
		
//		String firefox="C:/james2/firefox366/firefox.exe";
		String firefox="C:/james/Mozilla Firefox/firefox.exe";
//		System.setProperty("http.proxyHost", "sjc-vts-388");
//		System.setProperty("http.proxyPort", "8088");;
		System.setProperty("webdriver.firefox.bin", firefox);

		if(args!=null && args.length>0){
			firefox=args[0];
		}
		if(args!=null && args.length>1){
			SystemConfig.DEFAULT_EDTYPE=SystemConfig.EDTypeEnum.valueOf(args[1]);
		}

		if(args!=null && args.length>3){
			System.setProperty("http.proxyHost", args[2]);
			System.setProperty("http.proxyPort", args[3]);;
		}
		while(true){
			DownloadInitializer.init();
			processDownload();
			
			MergeInitializer.init();
			processMerge(GroupIdEnum.values());
	
			GeneratorInitializer.init();	
			GeneratorInitializer.top_notice="系统提示：正在内部测试中。。。。。。";
			processGenerator(GroupIdEnum.values());
			if(toBeContinued){
				long timeGap = (long) (Math.random()*10*60*1000);
				if(timeGap<5*60*1000) timeGap=timeGap+10*60*1000;
				logger.info("start to wait for time Gap:"+(double)(timeGap/(60.0*1000.0)) +" minutes" );
				Thread.sleep(timeGap);				
			}else{
				break;
			}
		}
		WebUtils.closeSelenium();

	}

	private static void processDownload() throws InterruptedException {
		//			SimpleThreadPoolContainer container = new SimpleThreadPoolContainer(1, 100,"rss");
					GroupIdEnum[] groups=GroupIdEnum.values();
					for(final GroupIdEnum group:groups){
						if(group.isStatic()) continue;
						ArrayList<String> allURL = URLRegistry.getAllURLByGroup(group);
						DownloadContext.getGlobalContext().addEndStatus(allURL.size());
						for(int i=0;i<allURL.size();i++){
							final String url=allURL.get(i);
		//					container.addToBatch(new Runnable(){
		//						public void run() {
									DownloadContext.createContext(url,group.name());
									WebUtils.webGet(url);
									DownloadContext.getGlobalContext().stepStatus();
									DownloadContext.destroyContext();
		//						}
		//					});
						}
		//				container.executeBatch();
						if(allURL==null || allURL.size()==0) continue;
						logger.info(group.name() +" data end to download...");
					}
					while(DownloadContext.getGlobalContext().getStatus()< DownloadContext.getGlobalContext().getEndStatus()){
						logger.info("total url should download: "+DownloadContext.getGlobalContext().getEndStatus());
						logger.info("current downloaded URL NO: "+DownloadContext.getGlobalContext().getStatus());
						Thread.sleep(5000);
					}
	}
	public static void processGenerator(GroupIdEnum ...pageNames) throws Exception {
		GeneratorFileUtils.copy("img");
		GeneratorFileUtils.copy("js");
		for(GroupIdEnum pageName:pageNames){
			GenContentProcessorRegistry.getContentProcessor(pageName).getResult();
		}
	}
	
	public static void processMerge(GroupIdEnum ...pageNames) throws Exception {
		for(GroupIdEnum pageName:pageNames){
			if(pageName.isStatic()) continue;
			MergeContentProcessorRegistry.getContentProcessor(pageName).merge();
		}
	}
}
