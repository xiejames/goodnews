package together.news.common;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class NewsDTO {
	public String group;
	public String title;
	public String titleHref;
	public String updateDate;
	public String content;
	public ArrayList<String> picURLs=new ArrayList<String>();
	public ArrayList<String> picWidths=new ArrayList<String>();
	public ArrayList<String> picHeights=new ArrayList<String>();
	private static Logger logger=Logger.getLogger(NewsDTO.class);
	
	public NewsDTO(){}
	public NewsDTO(String title, String titleHref, String updateDate,
			String content) {
		super();
		this.title = title;
		this.titleHref = titleHref;
		this.updateDate = updateDate;
		this.content = content;
	}

	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public ArrayList<String> getPicWidths() {
		return picWidths;
	}
	public void setPicWidths(ArrayList<String> picWidths) {
		this.picWidths = picWidths;
	}
	public ArrayList<String> getPicHeights() {
		return picHeights;
	}
	public void setPicHeights(ArrayList<String> picHeights) {
		this.picHeights = picHeights;
	}
	public ArrayList<String> getPicURLs() {
		return picURLs;
	}

	public void setPicURLs(ArrayList<String> picURLs) {
		this.picURLs = picURLs;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleHref() {
		return titleHref;
	}

	public void setTitleHref(String titleHref) {
		this.titleHref = titleHref;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsDTO other = (NewsDTO) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!similar(title,other.title))
			return false;
		return true;
	}
	private boolean similar(String text1, String text2) {
		int similarCount=0;
		for(int i=0;i<text2.length();i++){
			if(text1.contains(text2.substring(i,i+1))){
				similarCount++;
			}
		}
		int similarCount2=0;
		for(int i=0;i<text1.length();i++){
			if(text2.contains(text1.substring(i,i+1))){
				similarCount2++;
			}
		}
		if(similarCount*100/text2.length() >50  && similarCount2*100/text1.length() >50 ){//more than half is similar
			logger.info(text1);
			logger.info(text2);
			return true;
		}
		else
			return false;
	}
	@Override
	public String toString() {
		return "NewsDTO [title=" + title + ", titleHref=" + titleHref
				+ ", updateDate=" + updateDate + ", content=" + content
				+ ", picURLs=" + picURLs + "]";
	}

	
}
