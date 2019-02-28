package auto.zhongml.table.dao;

import java.util.List;

/**
 * 
 * <pre>
 * 构建StringBuffer必须实现的接口
 * </pre>
 * @author zhongml
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public interface CreateBuffer {
	public StringBuffer createBuffer(String tableName, String fileName, String voName, String voAllName, String daoImplAllName, List ausd);
}
