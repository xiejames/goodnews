package together.news.common.constant;

public class PageData {

	public static final int TOP_NEWS_CONTENT_MAX = 100;
	public static final int NEWS_LIST_CONTENT_MAX = 30;
	public static int PIC_WIDTH = 300;
	public static int PIC_HEIGHT = 225;

	public static String trim(String content, int topNewsContentMax) {
		if (topNewsContentMax < 1) {
			return "";
		} else if (content.length() > topNewsContentMax) {
			return content.substring(0, topNewsContentMax);
		} else
			return content;
	}

	public static int getWidth(String width, String height) {
		try {
			int w = Integer.parseInt(width);
			int h = Integer.parseInt(height);
			if (w!=0 && h!=0 && w < PIC_WIDTH && h<PIC_HEIGHT) {
				if(w*PIC_HEIGHT>h*PIC_WIDTH){
					return w*PIC_HEIGHT/h;
				}
			}
			return PIC_WIDTH;
		} catch (Exception e) {
			return PIC_WIDTH;
		}
	}

	public static int getHeight(String width, String height) {
		try {
			try {
				int w = Integer.parseInt(width);
				int h = Integer.parseInt(height);
				if (w!=0 && h!=0 && w < PIC_WIDTH && h<PIC_HEIGHT) {
					if(w*PIC_HEIGHT<h*PIC_WIDTH){
						return h*PIC_WIDTH/w;
					}
				}
				return PIC_HEIGHT;
			} catch (Exception e) {
				return PIC_HEIGHT;
			}
		} catch (Exception e) {
			return PIC_HEIGHT;
		}

	}
}
