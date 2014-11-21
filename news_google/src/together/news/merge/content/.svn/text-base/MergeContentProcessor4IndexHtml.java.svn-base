package together.news.merge.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import together.news.common.NewsDTO;
import together.news.common.NewsGroup.GroupIdEnum;

public class MergeContentProcessor4IndexHtml extends MergeContentProcessor{
	
	public MergeContentProcessor4IndexHtml(GroupIdEnum pageName) {
		super(pageName);
	}

	@Override
	protected List<NewsDTO> process(HashMap<String, List<NewsDTO>> pageData) throws IOException {
		 List<NewsDTO> result=new  ArrayList<NewsDTO>();
		 Iterator<List<NewsDTO>> dataIterator = pageData.values().iterator();
		 ArrayList<List<NewsDTO>> allDatas=new ArrayList<List<NewsDTO>>();
		 while(dataIterator.hasNext()){
			 allDatas.add(dataIterator.next());
		 }
		 while(allDatas.size()!=0){
			 int i=(int)(Math.random()*allDatas.size());
			 if(i>=allDatas.size()){
				 continue;
			 }
			 List<NewsDTO> currentArray = allDatas.get(i);
			 if(currentArray.size()==0){
				 allDatas.remove(i);
				 continue;
			 }
			 NewsDTO data = currentArray.remove(0);
			 if(data!=null){
				 addOne(result,data);
			 }
		 }
		 
		return result;
	}

	private void addOne(List<NewsDTO> result, NewsDTO data) {
		if(result.contains(data)==false){
			result.add(data);
		}
	}

}

