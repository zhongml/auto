package auto.zhongml.table;

import auto.zhongml.table.buffer.ControllerBuffer;
import auto.zhongml.table.buffer.DaoBuffer;
import auto.zhongml.table.buffer.DaoImplBuffer;
import auto.zhongml.table.buffer.EditJspBuffer;
import auto.zhongml.table.buffer.EntityBuffer;
import auto.zhongml.table.buffer.ListJspBuffer;
import auto.zhongml.table.buffer.MainJspBuffer;
import auto.zhongml.table.buffer.MapperXmlBuffer;
import auto.zhongml.table.buffer.ServiceBuffer;
import auto.zhongml.table.buffer.ServiceImplBuffer;
import auto.zhongml.table.buffer.SpringBuffer;

import java.util.List;

public class Engine extends BaseUtil{

	
	/**
	 * 创建映射文件
	 * @param tableName
	 */
	public static void createMapperXml(String tableName,String fileName,String voName,String voAllName,String daoImplAllName) {
		
		MapperXmlBuffer mapBuffer=new MapperXmlBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,null);
		createFile(fileName, buffer);

	}
	
	/**
	 * 创建实体类文件
	 * @param tableName
	 */
	public static void createEntityJava(String tableName,String fileName,String voName,String voAllName,String daoImplAllName) {
		
		EntityBuffer mapBuffer=new EntityBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,null);
		createFile(fileName, buffer);

	}
	
	/**
	 * 创建Dao类文件
	 * @param tableName
	 */
	public static void createDaoJava(String tableName,String fileName,String voName,String voAllName,String daoImplAllName, List ausd) {
		
		DaoBuffer mapBuffer=new DaoBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,ausd);
		createFile(fileName, buffer);

	}
	
	/**
	 * 创建DaoImpl类文件
	 * @param tableName
	 */
	public static void createDaoImplJava(String tableName,String fileName,String voName,String voAllName,String daoImplAllName, List ausd) {
		
		DaoImplBuffer mapBuffer=new DaoImplBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,ausd);
		createFile(fileName, buffer);
	}
	
	/**
	 * 创建Service类文件
	 * @param tableName
	 */
	public static void createServiceJava(String tableName,String fileName,String voName,String voAllName,String daoImplAllName, List ausd) {
		
		ServiceBuffer mapBuffer=new ServiceBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,ausd);
		createFile(fileName, buffer);

	}
	
	
	
	/**
	 * 创建ServiceImpl类文件
	 * @param tableName
	 */
	public static void createServiceImplJava(String tableName,String fileName,String voName, String voAllName, String daoImplAllName, List ausd) {
		
		ServiceImplBuffer mapBuffer=new ServiceImplBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,ausd);
		createFile(fileName, buffer);
	}
	
	/**
	 * 创建ServiceImpl类文件
	 * @param tableName
	 */
	public static void createControllerJava(String tableName,String fileName,String voName,String voAllName,String daoImplAllName, List ausd) {
		
		ControllerBuffer mapBuffer=new ControllerBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,ausd);
		createFile(fileName, buffer);
	}
	
	/**
	 * 创建Spring bean文件
	 * @param tableName
	 */
	public static void createSpringXml(String tableName,String fileName,String voName,String voAllName,String daoImplAllName) {
		
		SpringBuffer mapBuffer=new SpringBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,null);
		createFile(fileName, buffer);
	}
	
	/**
	 * 创建List Jsp文件
	 * @param tableName
	 */
	public static void createListJsp(String tableName,String fileName,String voName,String voAllName,String daoImplAllName) {
		
		ListJspBuffer mapBuffer=new ListJspBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,null);
		createFile(fileName, buffer);
	}
	
	
	/**
	 * 创建Edit Jsp文件
	 * @param tableName
	 */
	public static void createEditJsp(String tableName,String fileName,String voName,String voAllName,String daoImplAllName) {
		
		EditJspBuffer mapBuffer=new EditJspBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,null);
		createFile(fileName, buffer);
	}
	
	/**
	 * 创建Main Jsp文件
	 * @param tableName
	 */
	public static void createMainJsp(String tableName,String fileName,String voName,String voAllName,String daoImplAllName) {
		
		MainJspBuffer mapBuffer=new MainJspBuffer();
		StringBuffer buffer=mapBuffer.createBuffer(tableName, fileName,voName, voAllName, daoImplAllName,null);
		createFile(fileName, buffer);
	}
	
	


}
