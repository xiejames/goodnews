package together.news.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.Unpacker;

import together.news.common.NewsDTO;
import together.news.common.constant.SystemConfig;
import together.news.common.datatype.msgpack.NewsDTOTemplate;

public class ExchangeDataFormatUtils {
	public static String toExchangeDataStr(List<NewsDTO> result){
		if(SystemConfig.DEFAULT_EDTYPE==SystemConfig.EDTypeEnum.MSG_PACK){
			return generateByMsgpackType(result);
		}
		return generateByJSONType(result);
	}

	
	private static String generateByMsgpackType(List<NewsDTO> result) {
   	 	 MessagePack msgpack = new MessagePack();
   	 	 msgpack.register(NewsDTO.class);
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         Packer packer = msgpack.createPacker(out);
         try {
			packer.write(result);
			return out.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			return generateByJSONType(result);
		} 
	}

	private static String generateByJSONType(List<NewsDTO> result) {
		return JSONArray. fromObject(result).toString();
	}

	public static List<NewsDTO> fromExchangeDataStr(String text) {
		if(SystemConfig.DEFAULT_EDTYPE==SystemConfig.EDTypeEnum.MSG_PACK){
			return readByMsgpackType(text);
		}
		return readByJSONType(text);
	}

	private static List<NewsDTO> readByMsgpackType(String text){
		Template<List<NewsDTO>> listTmpl = Templates.tList(NewsDTOTemplate.getInstance());
  	 	 MessagePack msgpack = new MessagePack();
  	 	 msgpack.register(NewsDTO.class);
         ByteArrayInputStream in;
		try {
			in = new ByteArrayInputStream(text.getBytes("UTF-8"));
			Unpacker unpacker = msgpack.createUnpacker(in);
			return unpacker.read(listTmpl);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readByJSONType(text);
	}

	private static List<NewsDTO> readByJSONType(String text) {
		List<NewsDTO> newsList= new ArrayList<NewsDTO>();
		JSONArray jsonArray=JSONArray.fromObject(text);
		Object[] objList = jsonArray.toArray();
		for(Object obj:objList){
			NewsDTO item = null;
			try{
				item = (NewsDTO) JSONObject.toBean((JSONObject) obj, NewsDTO.class);
			}catch(Exception e){
				continue;
			}
			String title = item.getTitle();
			String content = item.getContent();
			String titleLink = item.getTitleHref();
			String updateDate = item.getUpdateDate();
			item.setTitle(title);
			item.setTitleHref(titleLink);
			item.setContent(content);
			item.setUpdateDate(updateDate);
			newsList.add(item);
		}
		return newsList;
	}
}
