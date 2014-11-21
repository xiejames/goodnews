package together.news.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.Unpacker;

import together.news.common.NewsDTO;
import together.news.common.datatype.msgpack.NewsDTOTemplate;

public class MsgpackTest6 {
    
    public static void main(String[] args) throws Exception {
    	 MessagePack msgpack = new MessagePack();
    	 msgpack.register(NewsDTO.class);
         // Create templates for serializing/deserializing List and Map objects
         Template<List<NewsDTO>> listTmpl = Templates.tList(NewsDTOTemplate.getInstance());
         Template<Map<String, String>> mapTmpl = Templates.tMap(Templates.TString, Templates.TString);
  
         //
         // Serialization
         //
  
 		String fileName="test4.msgpack";
		 FileOutputStream out = new FileOutputStream(fileName);
         Packer packer = msgpack.createPacker(out);
  
         // Serialize List object
         List<NewsDTO> list = new ArrayList<NewsDTO>();
         NewsDTO src=new NewsDTO();
         src.setTitle("msgpack");
         src.setTitleHref("www.baidu.com");
         list.add(src);
         packer.write(list); // List object
  
         // Serialize Map object
         Map<String, String> map = new HashMap<String, String>();
         map.put("sadayuki", "furuhashi");
         map.put("muga", "nishizawa");
         packer.write(map); // Map object
         
         packer.flush();
         out.flush();
         //
         // Deserialization
         //
 		FileInputStream in = new FileInputStream(fileName);
         Unpacker unpacker = msgpack.createUnpacker(in);
         // to List object
         List<NewsDTO> dstList = unpacker.read(listTmpl);
         System.out.println(dstList);
         // to Map object
         Map<String, String> dstMap = unpacker.read(mapTmpl);
         System.out.println(dstMap);
    }

}
