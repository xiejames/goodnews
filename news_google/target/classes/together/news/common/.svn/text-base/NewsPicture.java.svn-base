package together.news.common;

public class NewsPicture {
	private String url;
	private int height;
	private int width;
	
	
	public NewsPicture(String url, String height, String width) {
		super();
		this.url = url;
		try{
			this.height = Integer.parseInt(height);
		}catch(Exception e){
			this.height=-1;
		}
		try{
			this.width = Integer.parseInt(width);
		}catch(Exception e){
			this.width=-1;
		}
	}
	public NewsPicture(String url) {
		super();
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
}
