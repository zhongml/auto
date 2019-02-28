package auto.zhongml.table.buffer;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

import java.util.List;

public class DaoBuffer implements CreateBuffer{


	@Override
	public StringBuffer createBuffer(String tableName, String fileName,String voName,String voAllName,String daoImplAllName, List ausd) {
		StringBuffer buffer=new StringBuffer();
		fileName=BaseUtil.getClassFolderPath(fileName);
		buffer.append("package "+fileName+";\r\n\n");
		buffer.append("import org.springframework.stereotype.Repository;\r\n");
		buffer.append("import com.newaim.core.jpa.BaseDao;\r\n");
		buffer.append("import com.newaim.purchase.admin.account.entity.Role;\r\n");
		buffer.append("import "+voAllName+"; \r\n");
		buffer.append(" @Repository \r\n");
		buffer.append(" public interface "+voName+"Dao extends BaseDao<"+voName+", String>{ \r\n");
		buffer.append(" } \r\n");
		return buffer;
	}

}
