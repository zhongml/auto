package auto.zhongml.table.buffer;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

import java.util.List;
import java.util.Map;

public class EntityVoBuffer extends BaseUtil  implements CreateBuffer {

	@Override
	public StringBuffer createBuffer(String tableName, String fileName,String voName,String voAllName,String daoImplAllName, List ausd) {
		StringBuffer buffer=new StringBuffer();
		Map<String,String> map=getColumnAndType(tableName);
		System.out.println(voAllName);
		String folderPath=BaseUtil.getFolderPath(voAllName);
		System.out.println(folderPath);
		buffer.append("package "+folderPath+";\r\n\n");
		buffer.append("import com.newaim.purchase.config.Interceptor.CheckRepeatDB;\r\n");
		buffer.append("import org.hibernate.annotations.GenericGenerator;\r\n");
		buffer.append("import org.hibernate.annotations.Parameter;\r\n");
		buffer.append("import javax.persistence.Entity;\r\n");
		buffer.append("import javax.persistence.GeneratedValue;\r\n");
		buffer.append("import javax.persistence.Id;\r\n");
		buffer.append("import javax.persistence.Table;\r\n");

		buffer.append("import java.io.Serializable;\r\n");
		buffer.append("import java.util.Date;\r\n");
		buffer.append(" @Entity \r\n");
		buffer.append(" @Table(name = \""+tableName+"\") \r\n");
		buffer.append("public class "+voName+"Vo implements Serializable { \r\n\n");
		for(Map.Entry<String, String> entry : map.entrySet()) {

				buffer.append("	private "+fieldToEntityType(entry.getValue())+" "+fieldToProperty(entry.getKey())+"; \r\n");

		}
		for(Map.Entry<String, String> entry : map.entrySet()) {
		buffer.append("		public "+fieldToEntityType(entry.getValue())+" get"+up(fieldToProperty(entry.getKey()))+"() { \r\n");
		buffer.append("	  		return "+fieldToProperty(entry.getKey())+"; \r\n");
		buffer.append(" 		} \r\n");
		buffer.append(" 		public void set"+up(fieldToProperty(entry.getKey()))+"("+fieldToEntityType(entry.getValue())+" "+fieldToProperty(entry.getKey())+") { \r\n");
		buffer.append("   			this."+fieldToProperty(entry.getKey())+" = "+fieldToProperty(entry.getKey())+"; \r\n");
		buffer.append(" 		} \r\n");
		}
		buffer.append("} \r\n");
						
		return buffer;
	}

}
