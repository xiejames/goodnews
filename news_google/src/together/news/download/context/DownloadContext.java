package together.news.download.context;


public class DownloadContext {
	private static ThreadLocal<DownloadContext> contextPool=new ThreadLocal<DownloadContext>();
	private static DownloadContext globalContext=new DownloadContext();
	
	public static DownloadContext getGlobalContext(){
		return globalContext;
	}
	public static boolean createContext(String url){
		contextPool.set(new DownloadContext(url));
		return true;
	}

	public static boolean createContext(String url,String group){
		contextPool.set(new DownloadContext(url,group));
		return true;
	}
	public static DownloadContext getContext(){
		if(contextPool.get()==null){
			createContext(null);
		}
		return contextPool.get();
	}
	public static boolean destroyContext(){
		contextPool.remove();
		return true;
	}
	private DownloadContext(){
		status=0;
	}
	private DownloadContext(String url){
		this.url=url;
	}
	private DownloadContext(String url,String group){
		this.url=url;
		this.group=group;
	}
	private int status=0;
	private int endStatus=0;
	
	public int getEndStatus() {
		return endStatus;
	}
	public void addEndStatus(int endStatus) {
		this.endStatus += endStatus;
	}
	private String url;
	private String group;
	
	public int getStatus() {
		return status;
	}
	public synchronized void stepStatus() {
		this.status++;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getURL(){return url;}
	public void setURL(String url){this.url=url;}
}
