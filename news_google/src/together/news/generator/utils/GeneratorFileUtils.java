package together.news.generator.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import together.news.common.constant.FileSystemConfig;
import together.news.common.utils.TogetherFileUtils;

public class GeneratorFileUtils {

	
	public static String readTemplateFile(String pageName,String encoding) throws IOException{
		return TogetherFileUtils.read(FileSystemConfig.template_FOLDER+"/"+pageName,encoding);
	}
	public static String readPageDataFile(String pageName,String encoding) throws IOException{
		return TogetherFileUtils.read(FileSystemConfig.getMergeFilePath(pageName),encoding);
	}

	public static void writeHtmlGenerated(String pageName,String text,String encoding) throws IOException {
		File saveFile=maintainGeneratedFileSystem(pageName);
		TogetherFileUtils.write(saveFile,text,encoding);
	}
	
	private static File maintainGeneratedFileSystem(String pageName) throws IOException {
		File siteFile = new File(FileSystemConfig.generate_FOLDER);
		if (siteFile.exists() == false) {
			siteFile.mkdirs();
		}
		String currentFileName=pageName;
		File saveFile = new File(FileSystemConfig.generate_FOLDER+"/"+currentFileName);
		if (saveFile.exists() == false) {
			saveFile.createNewFile();
		}
		return saveFile;
	}
	
	
	public static void copy(String subFolder) throws IOException{
		File baseFromFolder = new File(FileSystemConfig.template_FOLDER+"/"+subFolder);
		File baseToFolder = new File(FileSystemConfig.generate_FOLDER+"/"+subFolder);
		if(baseToFolder.exists()==false){
			baseToFolder.mkdirs();
		}
		File[] dataFiles=baseFromFolder.listFiles();
		for(int i=0;dataFiles!=null && i<dataFiles.length;i++){
			File fromFile=dataFiles[i];
			if(fromFile.isDirectory()) continue;
			File toFile=new File(baseToFolder+"/"+fromFile.getName());
			if(toFile.exists()==false){
				toFile.createNewFile();
			}
			FileInputStream fromFileStream = new FileInputStream(fromFile);
			FileOutputStream toFileStream = new FileOutputStream(toFile);
			int buf=fromFileStream.read();
			while(buf!=-1){
				toFileStream.write(buf);
				buf=fromFileStream.read();
			}
			toFileStream.flush();
			toFileStream.close();
			fromFileStream.close();
		}
	}
}
