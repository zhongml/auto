package auto.zhongml.table.buffer;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

import java.util.List;

public class SpringBuffer extends BaseUtil implements CreateBuffer {

	@Override
	public StringBuffer createBuffer(String tableName, String fileName, String voName, String voAllName, String daoImplAllName, List ausd) {
		
		String modelPath="";
		String folderPathOfDaoImpl=BaseUtil.getFolderPath(daoImplAllName);
		modelPath=folderPathOfDaoImpl.substring(0, folderPathOfDaoImpl.length()-4);
		String servicePath=modelPath.replace("dao", "service");
		StringBuffer buffer=new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \r\n");
		buffer.append(" <beans xmlns=\"http://www.springframework.org/schema/beans\" \r\n");
		buffer.append(" 	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \r\n");
		buffer.append(" 	xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd\" \r\n");
		buffer.append(" 	default-lazy-init=\"true\"> \r\n");
			
		buffer.append(" 	<bean id=\""+low(voName)+"Service\" class=\""+servicePath+"impl."+voName+"ServiceImpl\" > \r\n");
		buffer.append(" 		<property name=\""+low(voName)+"Dao\" ref=\""+low(voName)+"Dao\" /> \r\n");
		buffer.append(" 	</bean> \r\n");
			
		buffer.append(" 	<bean id=\""+low(voName)+"Dao\" \r\n");
		buffer.append(" 		class=\""+daoImplAllName+"\" \r\n");
		buffer.append(" 		 parent=\"fbrp_infrastructure_defaultService\"></bean> \r\n");
			
		buffer.append(" </beans> \r\n");
		return buffer;
	}

}
