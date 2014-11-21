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

public class MsgpackTest4 {

    public static void main(String[] args) throws Exception {
    	 MessagePack msgpack = new MessagePack();
    	 
         // Create templates for serializing/deserializing List and Map objects
         Template<List<String>> listTmpl = Templates.tList(Templates.TString);
         Template<Map<String, String>> mapTmpl = Templates.tMap(Templates.TString, Templates.TString);
  
         //
         // Serialization
         //
  
 		String fileName="src/"
				 		+MsgpackTest4.class.getPackage().getName().replace(".", "/")
				 		+"/test4.msgpack";
		 FileOutputStream out = new FileOutputStream(fileName);
         Packer packer = msgpack.createPacker(out);
  
         // Serialize List object
         List<String> list = new ArrayList<String>();
         list.add("msgpack");
         list.add("for");
         list.add("java");
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
         List<String> dstList = unpacker.read(listTmpl);
         System.out.println(dstList);
         // to Map object
         Map<String, String> dstMap = unpacker.read(mapTmpl);
         System.out.println(dstMap);
    }

}
