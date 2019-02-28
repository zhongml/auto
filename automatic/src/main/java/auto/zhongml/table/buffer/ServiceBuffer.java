package auto.zhongml.table.buffer;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

import java.util.List;

public class ServiceBuffer  implements CreateBuffer {

	@Override
	public StringBuffer createBuffer(String tableName, String fileName,
			String voName,String voAllName,String daoImplAllName, List ausd) {
		StringBuffer buffer=new StringBuffer();
		fileName=BaseUtil.getClassFolderPath(fileName);
		buffer.append("package "+fileName+";\r\n\n");
		buffer.append("import java.util.List;\r\n");
		buffer.append("import "+voAllName+"; \r\n");
		buffer.append("import com.foresee.fbrp.infrastructure.util.PagedResult;\r\n");
		buffer.append("public interface "+voName+"Service{ \r\n");
		buffer.append("public static final String BEAN_ID = \""+BaseUtil.low(voName)+"Service\";\r\n");
		buffer.append(" boolean add"+voName+"("+voName+" vo) throws Exception; \r\n");
		buffer.append(" boolean update"+voName+"("+voName+" vo) throws Exception; \r\n");
		buffer.append(" boolean delete"+voName+"("+voName+" vo) throws Exception; \r\n");
		buffer.append(" List<"+voName+"> get"+voName+"List("+voName+" vo) throws Exception; \r\n");
		buffer.append(" "+voName+" query"+voName+"ById(String id) throws Exception; \r\n");
		buffer.append(" PagedResult<"+voName+"> queryAllData("+voName+" vo, boolean onlyMine, int start, int limit)  throws Exception; \r\n");
		buffer.append("} \r\n");
		return buffer;
	}


}
