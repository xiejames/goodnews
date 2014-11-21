package together.news.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.Unpacker;

public class MsgpackTest3 {

    public static void main(String[] args) throws Exception {
    	 MessagePack msgpack = new MessagePack();
    	 
         // Create templates for serializing/deserializing List and Map objects
         Template<List<String>> listTmpl = Templates.tList(Templates.TString);
         Template<Map<String, String>> mapTmpl = Templates.tMap(Templates.TString, Templates.TString);
  
         //
         // Serialization
         //
  
         ByteArrayOutputStream out = new ByteArrayOutputStream();
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
  
         //
         // Deserialization
         //
  
         byte[] bytes = out.toByteArray();
         ByteArrayInputStream in = new ByteArrayInputStream(bytes);
         Unpacker unpacker = msgpack.createUnpacker(in);
  
         // to List object
         List<String> dstList = unpacker.read(listTmpl);
         System.out.println(dstList);
         // to Map object
         Map<String, String> dstMap = unpacker.read(mapTmpl);
         System.out.println(dstMap);
    }

}
