package together.news.generator.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup;
import together.news.common.NewsGroup.GroupIdEnum;
import together.news.common.constant.FileSystemConfig;
import together.news.download.WebUtils;
import together.news.generator.GeneratorInitializer;
import together.news.generator.utils.GeneratorFileUtils;

public abstract class GenContentProcessor {
	protected HashMap<String, List<NewsDTO>> pageData;
	protected String result=null;
	protected GroupIdEnum pageName;
	protected String templateFileName;
	protected String encoding="GB2312";
	protected HashMap<GroupIdEnum,NewsGroup> newsGroups;
	
	public GenContentProcessor(GroupIdEnum pageName,HashMap<GroupIdEnum,NewsGroup> newsGroups){
		this.pageName=pageName;
		this.newsGroups=newsGroups;
		init();
	}
	public GenContentProcessor(GroupIdEnum pageName,HashMap<GroupIdEnum,NewsGroup> newsGroups,String encoding){
		this.newsGroups=newsGroups;
		this.pageName=pageName;
		this.encoding=encoding;
		init();
	}
	protected void init() {
		templateFileName=pageName.name()+".html";
	}
	public String getResult() throws IOException{
		if(result!=null) return result;
		
		//load data
		pageData = loadPageData(pageName,newsGroups);
		//load template
		String templateHTML 
				= GeneratorFileUtils.readTemplateFile(templateFileName,encoding);
		//data-->template
		templateHTML=preProcess(templateHTML, pageData);
		this.result=getView(templateHTML, pageData);
		this.result=postProcess(this.result, pageData);
		if(result==null) return "";
		//write to File
		GeneratorFileUtils.writeHtmlGenerated(pageName.name()+".html",result,encoding);

		return result;
	}
	
	private String postProcess(String html,
			HashMap<String, List<NewsDTO>> pageData2) {
		Document doc = Jsoup.parse(html);
		if(doc.select("div.area")!=null && doc.select("div.area").select("a")!=null){
			doc.select("div.area").select("a").attr("target", "_blank");
			if(doc.getElementById("area4")!=null){
				doc.getElementById("area4").select("a").attr("target", "_self");
			}
		}
		doc.select("img").attr("onError", "this.src="+FileSystemConfig.blankPicture);
		return doc.html();
	}
	private String preProcess(String templateHTML,
			HashMap<String, List<NewsDTO>> pageData) {
		Document doc = Jsoup.parse(templateHTML);
		doc.title(NewsGroup.APP_NAME + " - " + pageName.getText());
		doc.getElementById("top_notice").text(GeneratorInitializer.top_notice);
		Element container = doc.getElementById("navi").getElementsByClass("naviL").first();
		container.html("");
		GroupIdEnum[] groups=GroupIdEnum.values();
		int lastNavi=groups.length-1;
		for(int i=0;i<groups.length;i++){
			if(groups[i].isNaviGroup()==false) continue;
			lastNavi=i;
		}
		for(int i=0;i<groups.length;i++){
			if(groups[i].isNaviGroup()==false) continue;
			Element template=new Element(Tag.valueOf("a"),groups[i].name()+".html");
			template.attr("href",groups[i].name()+".html");
			template.text(groups[i].getText());
			if(pageName.name().startsWith(groups[i].name())){
				template.addClass("naviCurrent");
				if(template.text().length()>2){
					template.addClass("naviCurrentWider");
				}
			}
			if(pageName.name().startsWith(groups[i+1].name())){
				template.addClass("naviPreCurrent");
			}
			if(i==lastNavi) template.addClass("naviLast");
			container.appendChild(template);
		}
		return doc.html();
		
	}
	
	
	protected List<NewsDTO> loadPictureData(List<NewsDTO> list, int max) throws IOException {
		int count=0;
		List<NewsDTO> result=new ArrayList<NewsDTO>();
		
		for(NewsDTO item:list){
			if(count>=max) break;
			if(item.getPicURLs().size()>0){
				result.add(item);
				count++;
			}
		}
		return result;
	}
	
	protected abstract HashMap<String, List<NewsDTO>> loadPageData(
			GroupIdEnum pageName, HashMap<GroupIdEnum,NewsGroup> newsGroups) throws IOException;

	protected abstract String getView(String templateHTML,
			HashMap<String, List<NewsDTO>> pageData) throws IOException;
}
