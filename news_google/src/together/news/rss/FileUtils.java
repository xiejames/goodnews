package together.news.rss;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtils {
	public static void saveToFile(String text,String fileName) throws IOException {
		File saveFile = new File(fileName);
		if (saveFile.exists() == false) {
			saveFile.createNewFile();
		}
		FileOutputStream stream = new FileOutputStream(saveFile);
		OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
		writer.write(text);
		writer.flush();
		writer.close();
	}

}
