package together.news.download;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.threadpool.container.SimpleThreadPoolContainer;
import together.news.download.context.DownloadContext;
import together.news.download.web.URLRegistry;

public class Main {

	/**
	 * @param args
	 * 定时取RSS的订阅数据
	 * 参数在Initializer里面定义，包括需要定时取的RSS URL 列表
	 * timeGap是定时的周期
	 */
	public static void main(String[] args) {
		
		DownloadInitializer.init();
		
		final long timeGap=1*60*1000;//定时周期
		SimpleThreadPoolContainer container = new SimpleThreadPoolContainer(1, 1000,"rss");
		GroupIdEnum[] groups=GroupIdEnum.values();
		for(final GroupIdEnum group:groups){
			ArrayList<String> allURL = URLRegistry.getAllURLByGroup(group);
			for(int i=0;i<allURL.size();i++){
				final String url=allURL.get(i);
				container.addToBatch(new Runnable(){
					public void run() {
						DownloadContext.createContext(url,group.name());
						WebUtils.webGet(timeGap, url);
						DownloadContext.destroyContext();
					}
					
				});
			}
			container.executeBatch();
			if(allURL==null || allURL.size()==0) continue;
			System.out.println(group.name() +" data start to download...");

		}
		try {
			Semaphore block=new Semaphore(0);
			block.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
