package auto.zhongml.table;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 程序的中文名称。
 * </pre>
 *
 * @author heath
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class WellMouth {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("开始生成。。。。");
        String rootPath = "E:/newaim/java/";
        String tableName = "na_product_marks";
        String entityName = "ProductMarks";
        String model = "mark";
        List ausd=new ArrayList<>();
        ausd.add("select");
        ausd.add("update");
        ausd.add("add");
        ausd.add("delete");
        create(rootPath, tableName, entityName, model,ausd);
    }

    /**
     * 创建
     *
     * @param rootPath  工程所在物理路径
     * @param tableName 表名
     * @param entityName    实体类名
     * @param model     功能模块名
     */
    public static void create(String rootPath, String tableName, String entityName, String model, List ausd) {
        System.out.println("开始生成。。。。");

        String voAllName = "com.newaim.purchase.archives." + model + ".entity." + entityName;
        String daoImplAllName = "com.newaim.purchase.archives." + model + ".dao.impl." + entityName + "DaoImpl";

        String path = rootPath + "com.newaim\\purchase\\archives/" + model + "/";
        //String pathJsp = rootPath + "web-ycs/WebContent/view/" + model + "/";
        //String xmlpath = rootPath + "com.ycjf.ycs/src/META-INF/conf/";

      /*  //创建mybatis映射文件
        Engine.createMapperXml(tableName, xmlpath + "mybatis/" + entityName.toLowerCase() + "-mapper.xml", entityName, voAllName, daoImplAllName);

        //创建spring配置文件
        Engine.createSpringXml("", xmlpath + "spring/yypt-" + entityName.toLowerCase() + "-service.xml", entityName, voAllName, daoImplAllName);
*/
        //创建实体类
        Engine.createEntityJava(tableName, path + "entity/" + entityName + ".java", entityName, voAllName, daoImplAllName);

        //创建Vo类
        Engine.createEntityJava(tableName, path + "vo/" + entityName + "Vo.java", entityName, voAllName, daoImplAllName);

        //创建Dao类
        Engine.createDaoJava("", path + "dao/" + entityName + "Dao.java", entityName, voAllName, daoImplAllName,ausd);

        //创建dao实现类
        Engine.createDaoImplJava("", path + "dao/impl/" + entityName + "DaoImpl.java", entityName, voAllName, daoImplAllName,ausd);

        //创建service类
       // Engine.createServiceJava("", path + "service/" + entityName + "Service.java", entityName, voAllName, daoImplAllName);

        //创建service实现类
        Engine.createServiceImplJava("", path + "service/impl/" + entityName + "ServiceImpl.java", entityName, voAllName, daoImplAllName,ausd);

        //创建控制器
        Engine.createControllerJava(tableName, path + "controllers/" + entityName + "Controller.java", entityName, voAllName, daoImplAllName,ausd);
			
		/*	//创建Listjsp列表
		    Engine.createListJsp(tableName, pathJsp+"list-"+model+".jsp",entityName,voAllName,daoImplAllName);
		
		    //创建Editjsp列表
		    Engine.createEditJsp(tableName, pathJsp+"edit-"+model+".jsp",entityName,voAllName,daoImplAllName);
		    
		    //创建Mainjsp列表
		    Engine.createMainJsp(tableName, pathJsp+"main-"+model+".jsp",entityName,voAllName,daoImplAllName);
			*/
        System.out.println("结束。。。。");

    }

}
