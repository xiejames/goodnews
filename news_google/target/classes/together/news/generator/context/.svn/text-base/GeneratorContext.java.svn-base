package together.news.generator.context;


public class GeneratorContext {
	private static ThreadLocal<GeneratorContext> contextPool=new ThreadLocal<GeneratorContext>();
	public static boolean createContext(String fileName){
		contextPool.set(new GeneratorContext(fileName));
		return true;
	}
	public static GeneratorContext getContext(){
		if(contextPool.get()==null){
			createContext(null);
		}
		return contextPool.get();
	}
	public static boolean destroyContext(){
		contextPool.remove();
		return true;
	}
	public GeneratorContext(String fileName){
		this.fileName=fileName;
	}
	private String fileName;
	public String getFileName(){return fileName;}
	public void setFileName(String fileName){this.fileName=fileName;}
}
