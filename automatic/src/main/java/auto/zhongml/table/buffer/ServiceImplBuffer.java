package auto.zhongml.table.buffer;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

import java.util.List;

public class ServiceImplBuffer extends BaseUtil implements CreateBuffer {

    @Override
    public StringBuffer createBuffer(String tableName, String fileName,
                                     String voName, String voAllName, String daoImplAllName, List ausd) {
        StringBuffer buffer = new StringBuffer();
        String modelPath = "";
        String folderPathOfDaoImpl = BaseUtil.getFolderPath(daoImplAllName);
        modelPath = folderPathOfDaoImpl.substring(0, folderPathOfDaoImpl.length() - 4);
        String servicePath = modelPath.replace("dao", "service");
        buffer.append("package " + servicePath + "impl;\r\n\n");
        buffer.append(" import java.util.List; \r\n");
        buffer.append(" import org.springframework.beans.factory.annotation.Autowired; \r\n");
        buffer.append(" import org.springframework.core.convert.converter.Converter; \r\n");
        buffer.append(" import org.springframework.data.domain.Page; \r\n");
        buffer.append(" import org.springframework.data.domain.PageRequest; \r\n");
        buffer.append(" import org.springframework.data.domain.Sort; \r\n");
        buffer.append(" import org.springframework.data.jpa.domain.Specification; \r\n");
        buffer.append(" import org.springframework.stereotype.Service; \r\n");
        buffer.append(" import org.springframework.transaction.annotation.Transactional; \r\n");

        buffer.append(" import com.newaim.core.jpa.DynamicSpecifications; \r\n");
        buffer.append(" import com.newaim.core.jpa.SearchFilter; \r\n");
        buffer.append(" import com.newaim.core.mapper.BeanMapper; \r\n");
        buffer.append(" import com.newaim.core.service.ServiceBase; \r\n");


        buffer.append("import " + voAllName + "; \r\n");
        buffer.append("import " + servicePath + voName + "Service; \r\n");
        buffer.append("import " + modelPath + voName + "Dao; \r\n");

        buffer.append(" @Service \r\n");
        buffer.append(" @Transactional(readOnly=true) \r\n");
        buffer.append(" public class " + voName + "ServiceImpl implements " + voName + "Service { \r\n\n");
        buffer.append(" @Autowired \r\n");
        buffer.append(" 	private " + voName + "Dao " + low(voName) + "Dao; \r\n");


        if (ausd.contains("select")) {

            buffer.append(" public Page<"+voName+"Vo> list(LinkedHashMap<String, Object> params, int pageNumber, int pageSize, Sort sort){ \r\n");
            buffer.append("         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort); \r\n");
            buffer.append("         Specification<"+voName+"> spec = buildSpecification(params); \r\n");
            buffer.append("         Page<"+voName+"> p = "+voName+"Dao.findAll(spec, pageRequest); \r\n");
            buffer.append("         Page<"+voName+"Vo> page = p.map(new Converter<"+voName+", "+voName+"Vo>() { \r\n");
            buffer.append(" 		    @Override \r\n");
            buffer.append(" 		    public "+voName+"Vo convert("+voName+" " + low(voName) + ") { \r\n");
            buffer.append(" 		        return BeanMapper.map(" + low(voName) + ", " + voName + "Vo.class); \r\n");
            buffer.append(" 		    } \r\n");
            buffer.append(" 		}); \r\n");
            buffer.append("         return page; \r\n");
            buffer.append("     } \r\n");

            buffer.append(" 	/** \r\n");
            buffer.append("      * 创建动态查询条件组合. \r\n");
            buffer.append("      */ \r\n");
            buffer.append("     private Specification<"+voName+"> buildSpecification(LinkedHashMap<String, Object> searchParams) { \r\n");
            buffer.append("         LinkedHashMap<String, SearchFilter> filters = SearchFilter.parse(searchParams); \r\n");
            buffer.append("         Specification<"+voName+"> spec = DynamicSpecifications.bySearchFilter(filters.values(), "+voName+".class); \r\n");
            buffer.append("         return spec; \r\n");
            buffer.append("     } \r\n");
        }


        buffer.append(" 	public "+voName+"Vo get(String id) { \r\n");
        buffer.append(" 		return BeanMapper.map(" + low(voName) + "Dao.findOne(id), "+voName+"Vo.class); \r\n");
        buffer.append(" 	} \r\n");

        buffer.append(" 	public \"+voName+\" get"+voName+"(String id){ \r\n");
        buffer.append(" 		return " + low(voName) + "Dao.findOne(id); \r\n");
        buffer.append(" 	} \r\n");

        if (ausd.contains("add")) {
            buffer.append(" 	@Transactional \r\n");
            buffer.append(" 	public void add(" + voName + " o){ \r\n");
            buffer.append(" 		" + low(voName) + "Dao.clear(); \r\n");
            buffer.append(" 		//判断数据重复性，在需要判断的属性上加上注解:@CheckRepeatDB \r\n");
            buffer.append(" 		checkDb(o," + low(voName) + "Dao); \r\n");
            buffer.append(" 		" + low(voName) + "Dao.save(o); \r\n");
            buffer.append(" 	} \r\n");
        }
        if (ausd.contains("update")) {
            buffer.append(" 	@Transactional \r\n");
            buffer.append(" 	public void save(" + voName + " o){ \r\n");
            buffer.append(" 		" + voName + "Dao.save(o); \r\n");
            buffer.append(" 	} \r\n");
        }

        if (ausd.contains("delete")) {
            buffer.append(" 	@Transactional \r\n");
            buffer.append(" 	public void delete(String id){ \r\n");
            buffer.append(" 		" + low(voName) + "Dao.delete(id); \r\n");
            buffer.append(" 	} \r\n");
        }


        buffer.append(" } \r\n");


        return buffer;
    }

}

