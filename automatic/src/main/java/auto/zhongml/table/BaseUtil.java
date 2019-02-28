package auto.zhongml.table;

import auto.zhongml.table.db.DbColumn;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseUtil {

    /**
     * 根据表名获取数据库字段和字段类型
     *
     * @param tableName
     * @return
     */
    public static Map<String, String> getColumn(String tableName) {

        Map<String, String> map = new HashMap<String, String>();
        Connection conn = DbColumn.getConnection();
        String sql = "select * from " + tableName;
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            for (int i = 1; i <= data.getColumnCount(); i++) {
                // 获得指定列的列名
                String columnName = data.getColumnName(i);
                // 获得指定列的数据类型名
                String columnTypeName = data.getColumnTypeName(i);
                map.put(columnName, columnTypeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;

    }

    /**
     * 根据表名获取数据库字段和字段类型
     *
     * @param tableName
     * @return
     */
    public static Map<String, String> getColumnAndType(String tableName) {

        Map<String, String> map = new HashMap<String, String>();
        Connection conn = DbColumn.getConnection();
        String sql = "select * from " + tableName;
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            for (int i = 1; i <= data.getColumnCount(); i++) {
                // 获得指定列的列名
                String columnName = data.getColumnName(i);
                // 获得指定列的数据类型名
                String columnTypeName = data.getColumnClassName(i);

                //特殊类型处理
                if ("java.sql.Timestamp".equals(columnTypeName)) {
                    columnTypeName = "java.util.Date";
                }
                map.put(columnName, columnTypeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;

    }


    /**
     * 字段转换成对象属性 例如：user_name to userName
     *
     * @param field
     * @return
     */
    public static String fieldToProperty(String field) {

        field = field.toLowerCase();
        if (null == field) {
            return "";
        }
        char[] chars = field.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                int j = i + 1;
                if (j < chars.length) {
                    sb.append(StringUtils.upperCase(CharUtils.toString(chars[j])));
                    i++;
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 全类名转成类名
     *
     * @param field 全类名
     * @return
     */
    public static String fieldToEntityType(String field) {
        if (null == field) {
            return "";
        }
        String Type = field.substring(field.lastIndexOf(".") + 1, field.length());

        return Type;
    }


    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String up(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    /**
     * 首字母小写
     *
     * @param name
     * @return
     */
    public static String low(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

    /**
     * 过滤特殊类型
     *
     * @param str
     * @return
     */
    public static String pase(String str) {
        if ("DATETIME".equals(str)) {
            return "TIMESTAMP";
        }
        if ("TEXT".equals(str)) {
            return "VARCHAR";
        }
        return str;
    }

    /**
     * 创建文件
     */
    public static void createFile(String fileName, StringBuffer buffer) {
        try {
            File newFilePath = new File(fileName);
            File fileParent = newFilePath.getParentFile();
            String fileName2 = newFilePath.getName();
            String fileParentName2 = fileParent.getName();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
                System.out.println("======================" + fileParentName2 + "  start======================");
                System.out.println(fileParentName2 + ">目录创建成功！");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
            writer.write(buffer.toString());
            writer.close();
            System.out.println(fileName2 + ">文件创建成功！");
            System.out.println("======================" + fileParentName2 + "  end======================");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据类全路径来获取所在目录
     *
     * @param folderPath
     * @return
     */
    public static String getFolderPath(String folderPath) {
        int i = folderPath.lastIndexOf(".");
        folderPath = folderPath.substring(0, i);
        return folderPath;
    }

    /**
     * 物理路径转成类路径
     *
     * @param filePath
     * @return
     */
    public static String getClassFolderPath(String filePath) {
        File file = new File(filePath);
        String name = file.getName();
        filePath = filePath.substring(filePath.indexOf(":") + 2, filePath.length() - 1);
        filePath = filePath.substring(filePath.indexOf("src") + 4, filePath.length() - (name.length()));
        filePath = filePath.replace("/", ".");
        return filePath;
    }

}
