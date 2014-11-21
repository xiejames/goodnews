package together.news.common.datatype.msgpack;

import java.io.IOException;

import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;

import together.news.common.NewsDTO;

public class NewsDTOTemplate extends AbstractTemplate<NewsDTO> {
	    private NewsDTOTemplate() { }

	    public void write(Packer pk, NewsDTO target, boolean required) throws IOException {
	        if (target == null) {
	            if (required) {
	                throw new MessageTypeException("Attempted to write null");
	            }
	            pk.writeNil();
	            return;
	        }
	        pk.write(target);
	    }

	    public NewsDTO read(Unpacker u, NewsDTO to, boolean required) throws IOException {
	        if (!required && u.trySkipNil()) {
	            return null;
	        }
	        to=new NewsDTO();
	        u.read(to);
	        return to;
	    }
	    static public NewsDTOTemplate getInstance() {
	        return instance;
	    }
	    static final NewsDTOTemplate instance = new NewsDTOTemplate();
	}