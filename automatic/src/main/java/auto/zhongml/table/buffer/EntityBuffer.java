package auto.zhongml.table.buffer;

import java.util.List;
import java.util.Map;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

public class EntityBuffer  extends BaseUtil  implements CreateBuffer {

	@Override
	public StringBuffer createBuffer(String tableName, String fileName,String voName,String voAllName,String daoImplAllName, List ausd) {
		StringBuffer buffer=new StringBuffer();
		Map<String,String> map=getColumnAndType(tableName);
		System.out.println(voAllName);
		String folderPath=BaseUtil.getFolderPath(voAllName);
		System.out.println(folderPath);
		buffer.append("package "+folderPath+";\r\n\n");
		buffer.append("import java.util.Date;\r\n\n");
		buffer.append(" @Entity \r\n");
		buffer.append(" @Table(name = \""+tableName+"\") \r\n");
		buffer.append("public class "+voName+" implements Serializable { \r\n\n");
		for(Map.Entry<String, String> entry : map.entrySet()) {
			if("id".equals(fieldToProperty(entry.getKey()))){
				buffer.append("    @Id \r\n");
				buffer.append(" 	@GeneratedValue(generator = \"idGenerator\") \r\n");
				buffer.append(" 	@GenericGenerator(name = \"idGenerator\", strategy = \"com.newaim.core.jpa.IdGenerator\", \r\n");
				buffer.append(" 		parameters = {@Parameter(name = \"prefix\", value = \"ROLE\")}) \r\n");
				buffer.append("	private "+fieldToEntityType(entry.getValue())+" "+fieldToProperty(entry.getKey())+"; \r\n");
			}else{
				buffer.append("	private "+fieldToEntityType(entry.getValue())+" "+fieldToProperty(entry.getKey())+"; \r\n");
			}
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
