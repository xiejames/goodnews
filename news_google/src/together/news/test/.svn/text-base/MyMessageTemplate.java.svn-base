package together.news.test;

import java.io.IOException;

import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;

public  class MyMessageTemplate extends AbstractTemplate<MyMessage> {
    private MyMessageTemplate() { }

    public void write(Packer pk, MyMessage target, boolean required) throws IOException {
        if (target == null) {
            if (required) {
                throw new MessageTypeException("Attempted to write null");
            }
            pk.writeNil();
            return;
        }
        pk.write(target);
    }

    public MyMessage read(Unpacker u, MyMessage to, boolean required) throws IOException {
        if (!required && u.trySkipNil()) {
            return null;
        }
        to=new MyMessage();
        u.read(to);
        return to;
    }
    static public MyMessageTemplate getInstance() {
        return instance;
    }
    static final MyMessageTemplate instance = new MyMessageTemplate();
}