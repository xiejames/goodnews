package together.news.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TogetherFileUtils {
	public static String read(String fileName,String encoding) throws IOException {
		FileInputStream stream = new FileInputStream(fileName);
		InputStreamReader reader = new InputStreamReader(stream, encoding);
		BufferedReader bufReader=new BufferedReader(reader);
		StringBuffer bufStr=new StringBuffer();
		String line=bufReader.readLine();
		while(line!=null){
			bufStr.append(line);
			line=bufReader.readLine();
		}
		bufReader.close();
		reader.close();
		stream.close();
		return bufStr.toString();
	}
	public static String read(File file,String encoding) throws IOException {
		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream, encoding);
		BufferedReader bufReader=new BufferedReader(reader);
		StringBuffer bufStr=new StringBuffer();
		String line=bufReader.readLine();
		while(line!=null){
			bufStr.append(line);
			line=bufReader.readLine();
		}
		bufReader.close();
		reader.close();
		stream.close();
		return bufStr.toString();
	}
	public static void write(String saveFileStr, String text, String encoding) throws IOException {
		FileOutputStream stream = new FileOutputStream(saveFileStr);
		OutputStreamWriter writer = new OutputStreamWriter(stream, encoding);
		writer.write(text);
		writer.flush();
		writer.close();
	}
	public static void write(File saveFile, String text, String encoding) throws IOException {
		FileOutputStream stream = new FileOutputStream(saveFile);
		OutputStreamWriter writer = new OutputStreamWriter(stream, encoding);
		writer.write(text);
		writer.flush();
		writer.close();
	}
}
