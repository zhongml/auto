package auto.zhongml.table.buffer;

import java.util.List;
import java.util.Map;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

/**
 * 映射文件StringBuffer
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author zhongml
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class MapperXmlBuffer extends BaseUtil implements CreateBuffer {
	
	/**
	 * 构建映射文件StringBuffer方法
	 * @param tableName 需要生成映射文件的表名
	 * @param fileName 生成的映射文件名
	 * @return
	 */
	public StringBuffer createBuffer(String tableName, String fileName, String voName, String voAllName, String daoImplAllName, List ausd){
		Map<String,String> map=getColumn(tableName);
		StringBuffer buffer=new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
		buffer.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
		buffer.append("<mapper namespace=\""+voAllName+"\">\r\n");
		
		//映射列表
		buffer.append("<resultMap id=\"BaseResultMap\" type=\""+voAllName+"\" >\r\n");
		for(Map.Entry<String, String> entry : map.entrySet()) {
			    buffer.append("<result column=\""+entry.getKey()+"\" property=\""+fieldToProperty(entry.getKey())+"\" jdbcType=\""+pase(entry.getValue())+"\" />\r\n");
			}
		buffer.append("</resultMap>\r\n");
		
		
		//insert字段
		buffer.append("   <sql id=\"insert_column\"> \r\n");
		for(Map.Entry<String, String> entry : map.entrySet()) {
			buffer.append(entry.getKey()+", \r\n");
		}
		buffer.deleteCharAt(buffer.length() - 4);
		buffer.append("   </sql> \r\n");
		
		//insert值
		buffer.append(" <sql id=\"insert_value\"> \r\n");
		for(Map.Entry<String, String> entry : map.entrySet()) {
			buffer.append("        #{"+fieldToProperty(entry.getKey())+",jdbcType="+pase(entry.getValue())+"},  \r\n");
		}
		buffer.deleteCharAt(buffer.length() - 5);
		buffer.append(" </sql> \r\n");
		
		//update设值
		buffer.append("	<sql id=\""+voName+"_updateSet\"> \r\n");
		buffer.append("		<set> \r\n");
		for(Map.Entry<String, String> entry : map.entrySet()) {
			buffer.append(" 			<if test=\""+fieldToProperty(entry.getKey())+"!=null  and "+fieldToProperty(entry.getKey())+" != ''\"> "+entry.getKey()+"=#{"+fieldToProperty(entry.getKey())+"},</if> \r\n");
		}
		buffer.deleteCharAt(buffer.length() - 9);
		buffer.append("		</set> \r\n");
		buffer.append("	</sql> \r\n");
		
		//需要查询的列
		buffer.append("<sql id=\""+voName+"_Column\"> \r\n");
		for(Map.Entry<String, String> entry : map.entrySet()) {
			buffer.append(" 			 "+entry.getKey()+",\r\n");
		}
		buffer.deleteCharAt(buffer.length() - 3);
		buffer.append("</sql> \r\n");
				
		//查询条件
		buffer.append("<sql id=\""+voName+"_condition\"> \r\n");
		buffer.append("		<where> \r\n");
		buffer.append("		    1 = 1 \r\n");
		for(Map.Entry<String, String> entry : map.entrySet()) {
			buffer.append(" 			<if test=\""+fieldToProperty(entry.getKey())+"!=null  and "+fieldToProperty(entry.getKey())+" != ''\"> and "+entry.getKey()+"=#{"+fieldToProperty(entry.getKey())+"}</if> \r\n");
		}
		buffer.append("		</where> \r\n");
		buffer.append("	</sql> \r\n");
		
		//insert操作
		buffer.append("    <insert id=\"insert"+voName+"\" parameterType=\""+voAllName+"\" > \r\n");
		buffer.append("   	    <![CDATA[ \r\n");
		buffer.append("   			  insert into "+tableName+"( \r\n");
		buffer.append("   		]]> \r\n");
		buffer.append("   		<include refid=\"insert_column\" /> \r\n");
		buffer.append("   		<![CDATA[ \r\n");
		buffer.append("   			) values( \r\n");
		buffer.append("   		]]> \r\n");
		buffer.append("   		<include refid=\"insert_value\" /> \r\n");
		buffer.append("   		<![CDATA[ \r\n");
		buffer.append("   		) \r\n");
		buffer.append("   		]]> \r\n");
		buffer.append("   </insert> \r\n");
		
		//update操作
		buffer.append("<update id=\"update"+voName+"\" parameterType=\""+voAllName+"\" > \r\n");
		buffer.append("		<![CDATA[ \r\n");
		buffer.append("			update "+tableName+" \r\n");
		buffer.append("		]]>\r\n");
		buffer.append("		<include refid=\""+voName+"_updateSet\"/> \r\n");
		buffer.append("		<![CDATA[ \r\n");
		buffer.append("			where id = #{id}\r\n");
		buffer.append("		]]> \r\n");
		buffer.append("	</update> \r\n");
		
		//删除操作
		buffer.append("		<delete id=\"delete"+voName+"\"  parameterType=\""+voAllName+"\"> \r\n");
		buffer.append("			<![CDATA[ \r\n");
		buffer.append("			delete from "+tableName+" where ID = #{id} \r\n");
		buffer.append("		    ]]> \r\n");
		buffer.append("		</delete> \r\n");
		
		//查询LIst
		buffer.append("		<select id=\"get"+voName+"List\" parameterType=\""+voAllName+"\" \r\n");
		buffer.append("				resultType=\""+voAllName+"\" resultMap=\"BaseResultMap\">  \r\n");
		buffer.append("		 		<![CDATA[  \r\n");
		buffer.append("					SELECT \r\n");
		buffer.append("					]]> \r\n");
		buffer.append("				<include refid=\""+voName+"_Column\" />  \r\n");
		buffer.append("		 		<![CDATA[  \r\n");
		buffer.append("					FROM "+tableName+"  \r\n");
		buffer.append("					]]> \r\n");
		buffer.append("				<include refid=\""+voName+"_condition\" />  \r\n");
		buffer.append("		 			<![CDATA[  \r\n");
		buffer.append("					ORDER BY create_date asc \r\n");
		buffer.append("				    ]]> \r\n");
		buffer.append("			</select> \r\n");
	    
		//查询操作
		buffer.append(" <select id=\"query"+voName+"\" parameterType=\"map\"  resultType=\""+voAllName+"\" resultMap=\"BaseResultMap\" > \r\n");
		buffer.append(" 		<![CDATA[ \r\n");
		buffer.append("			SELECT\r\n");
		buffer.append("			]]>\r\n");
		buffer.append(" 			<include refid=\""+voName+"_Column\" /> \r\n");
		buffer.append(" 		<![CDATA[ \r\n");
		buffer.append("			FROM "+tableName+" \r\n");
		buffer.append("			]]>\r\n");
		buffer.append(" 			<include refid=\""+voName+"_condition\" /> \r\n");
		buffer.append(" 			<![CDATA[ \r\n");
		buffer.append("			ORDER BY create_date desc\r\n");
		buffer.append("		    ]]>\r\n");
		buffer.append(" </select> \r\n");
		
		
		//查询记录数
		buffer.append("   <select id=\"query"+voName+"_Count\" parameterType=\""+voAllName+"\"  resultType=\"long\"  >   \r\n");
		 buffer.append(" 		<![CDATA[    \r\n");
		buffer.append(" 			SELECT   \r\n");
		buffer.append(" 		    count(*)   \r\n");
		buffer.append(" 			FROM "+tableName+"   \r\n");
		buffer.append(" 			]]>   \r\n");
		buffer.append("  			<include refid=\""+voName+"_condition\" />   \r\n");
		buffer.append("  </select>  \r\n");
		 
		//根据id查询
		buffer.append("   <select id=\"query"+voName+"ById\" parameterType=\"string\"  resultType=\""+voAllName+"\"  resultMap=\"BaseResultMap\">   \r\n");
		buffer.append(" 		<![CDATA[    \r\n");
		buffer.append(" 		SELECT   \r\n");
		buffer.append(" 		]]>   \r\n");
		buffer.append("  			<include refid=\""+voName+"_Column\" />   \r\n");
		buffer.append(" 		<![CDATA[    \r\n");
		buffer.append(" 			FROM "+tableName+"     \r\n");
		buffer.append(" 			where id = #{id,jdbcType=VARCHAR}   \r\n");
		buffer.append(" 		    ]]>   \r\n");
		buffer.append("  </select>   \r\n");
		
		
		return buffer.append("</mapper>\r\n");
	}
	

}
