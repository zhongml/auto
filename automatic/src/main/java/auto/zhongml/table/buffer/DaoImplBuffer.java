package auto.zhongml.table.buffer;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

import java.util.List;

public class DaoImplBuffer  implements CreateBuffer {

	@Override
	public StringBuffer createBuffer(String tableName, String fileName,String voName,String voAllName,String daoImplAllName, List ausd) {
		StringBuffer buffer=new StringBuffer();
		String modelPath="";
		String folderPathOfDaoImpl=BaseUtil.getFolderPath(daoImplAllName);
		modelPath=folderPathOfDaoImpl.substring(0, folderPathOfDaoImpl.length()-4);
		buffer.append("package "+folderPathOfDaoImpl+";\r\n\n");
		buffer.append(" import com.newaim.core.jpa.BaseDaoCustomImpl; \r\n");
		buffer.append(" public class "+voName+"DaoImpl extends BaseDaoCustomImpl { \r\n");
		buffer.append(" } \r\n");
		return buffer;
	}


}
