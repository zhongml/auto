package auto.zhongml.table;

import java.io.File;

public class TestFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath ="D:/well/com.ycjf.ycs/src/com/ycjf/ycs/processflow/counseling/Coladfe.java";
		File file=new File(filePath);
		String name=file.getName();
		filePath=filePath.substring(filePath.indexOf(":")+2, filePath.length()-1);
		filePath=filePath.substring(filePath.indexOf("src")+4, filePath.length()-(name.length()));
		filePath=filePath.replace("/", ".");
		System.out.println(filePath);
	}
	
	/**
	 * 创建文件夹
	 * @param folderPath  文件夹路径
	 */
	public static void createFolder(String folderPath){
		File file = new File(folderPath);
		  String name = file.getName(); 
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
			System.out.println("//目录["+name+"]创建成功");
		} else {
			System.out.println("//目录["+name+"]已存在，不需要创建");
		}
	}
	
	public static String  getFolderPath(String folderPath){
		int i=folderPath.lastIndexOf(".");
		folderPath=folderPath.substring(0,i);
		return folderPath;
	}
	
	public static String  getClassFolderPath(String filePath){
		filePath=filePath.substring(filePath.indexOf(":")+2, filePath.length()-1);
		filePath=filePath.substring(filePath.indexOf("/")+1, filePath.length());
		filePath=filePath.replace("/", ".");
		return filePath;
	}
	

}
