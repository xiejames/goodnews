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

public class MsgpackTest5 {
    
    public static void main(String[] args) throws Exception {
    	 MessagePack msgpack = new MessagePack();
    	 msgpack.register(MyMessage.class);
         // Create templates for serializing/deserializing List and Map objects
         Template<List<MyMessage>> listTmpl = Templates.tList(MyMessageTemplate.getInstance());
         Template<Map<String, String>> mapTmpl = Templates.tMap(Templates.TString, Templates.TString);
  
         //
         // Serialization
         //
  
 		String fileName="src/"
				 		+MsgpackTest5.class.getPackage().getName().replace(".", "/")
				 		+"/test4.msgpack";
		 FileOutputStream out = new FileOutputStream(fileName);
         Packer packer = msgpack.createPacker(out);
  
         // Serialize List object
         List<MyMessage> list = new ArrayList<MyMessage>();
         MyMessage src=new MyMessage();
         src.name = "msgpack";
         src.version = 0.6;
         list.add(src);
         list.add(src);
         list.add(src);
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
         List<MyMessage> dstList = unpacker.read(listTmpl);
         System.out.println(dstList);
         // to Map object
         Map<String, String> dstMap = unpacker.read(mapTmpl);
         System.out.println(dstMap);
    }

}
