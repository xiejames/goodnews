package together.news.test;

import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;

public class MsgpackTest {

    @Message // Annotation
    public static class MyMessage {
        // public fields are serialized.
        public String name;
        public double version;
		@Override
		public String toString() {
			return "MyMessage [name=" + name + ", version=" + version + "]";
		}
        
    }
 
    public static void main(String[] args) throws Exception {
        MyMessage src = new MyMessage();
        src.name = "msgpack";
        src.version = 0.6;
 
        MessagePack msgpack = new MessagePack();
        // Serialize
        byte[] bytes = msgpack.write(src);
        // Deserialize
        MyMessage dst = msgpack.read(bytes, MyMessage.class);
        System.out.println(dst);
    }

}
