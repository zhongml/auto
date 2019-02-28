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
        String tableName = "na_account_user_role";
        String entityName = "Role";
        String model = "admin.account";
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

        String voAllName = "com.newaim.purchase." + model + ".entity." + entityName;
        String daoImplAllName = "com.newaim.purchase." + model + ".dao.impl." + entityName + "DaoImpl";

        String path = rootPath + "com.newaim.purchase/" + model + "/";

        //创建实体类
        Engine.createEntityJava(tableName, path + "entity/" + entityName + ".java", entityName, voAllName, daoImplAllName);

        //创建Vo类
        Engine.createEntityVoJava(tableName, path + "vo/" + entityName + "Vo.java", entityName, voAllName, daoImplAllName);

        //创建Dao类
        Engine.createDaoJava("", path + "dao/" + entityName + "Dao.java", entityName, voAllName, daoImplAllName,ausd);

        //创建dao实现类
        Engine.createDaoImplJava("", path + "dao/impl/" + entityName + "DaoImpl.java", entityName, voAllName, daoImplAllName,ausd);

        //创建service类
        Engine.createServiceJava("", path + "service/" + entityName + "Service.java", entityName, voAllName, daoImplAllName,ausd);

        //创建service实现类
        //Engine.createServiceImplJava("", path + "service/impl/" + entityName + "ServiceImpl.java", entityName, voAllName, daoImplAllName,ausd);

        //创建控制器
        Engine.createControllerJava(tableName, path + "controllers/" + entityName + "Controller.java", entityName, voAllName, daoImplAllName,ausd);

        System.out.println("结束。。。。");

    }

}
