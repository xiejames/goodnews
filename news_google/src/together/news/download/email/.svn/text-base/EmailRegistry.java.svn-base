package together.news.download.email;

import java.util.ArrayList;
import java.util.HashMap;

import together.news.common.NewsGroup.GroupIdEnum;

public class EmailRegistry {
	
	public static class Email{
		private String site;
		private String username;
		private String password;
		public String getSite() {return site;}
		public String getUsername() {return username;}
		public String getPassword() {return password;}
		public Email(String site,String username, String password) {this.site=site;this.username = username;this.password = password;}
		
	}
	
	private static HashMap<GroupIdEnum,ArrayList<Email>> groupEmailMapping=new HashMap<GroupIdEnum,ArrayList<Email>>();
	public  static synchronized void register(GroupIdEnum group,Email email){
		if(groupEmailMapping.get(group)==null) groupEmailMapping.put(group, new ArrayList<Email>());
		groupEmailMapping.get(group).add(email);
	}
	public static ArrayList<Email> getEmailList(GroupIdEnum group){
		return groupEmailMapping.get(group);
	}
}
