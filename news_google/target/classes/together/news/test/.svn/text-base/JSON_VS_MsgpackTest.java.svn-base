package together.news.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.time.DateUtils;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.Unpacker;

public class JSON_VS_MsgpackTest {

	public static void main(String[] args) throws Exception {
		MessagePack msgpack = new MessagePack();

		// Create templates for serializing/deserializing List and Map objects
		Template<List<String>> listTmpl = Templates.tList(Templates.TString);
		Template<Map<String, String>> mapTmpl = Templates.tMap(
				Templates.TString, Templates.TString);

		//
		// Serialization
		//

		// Serialize List object
		List<String> list = new ArrayList<String>();
		StringBuffer buf = new StringBuffer();
		for (int s = 0; s < 100; s++) {
			buf.append("message");
		}
		for (int i = 1; i < 4096; i++) {
			list.add(buf.toString());
		}

		
		startLogger("msgpack");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Packer packer = msgpack.createPacker(out);
		packer.write(list); // List object

		byte[] bytes = out.toByteArray();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		Unpacker unpacker = msgpack.createUnpacker(in);
		List<String> dstList = unpacker.read(listTmpl);
		endLogger();

		startLogger("json");
		String text = JSONArray.fromObject(list).toString();
		JSONArray.fromObject(text);
		endLogger();
	}

	private static ThreadLocal<Date> time = new ThreadLocal<Date>();

	private static void startLogger(String title) {
		System.out.println(title + " start to test:");
		time.set(new Date());
	}

	private static void endLogger() {
		System.out.println(new Date().getTime()-(time.get().getTime()));
		time.remove();
	}

}
