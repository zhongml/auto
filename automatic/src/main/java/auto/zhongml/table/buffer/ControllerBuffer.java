package auto.zhongml.table.buffer;

import java.util.List;
import java.util.Map;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

public class ControllerBuffer extends BaseUtil implements CreateBuffer {

    @Override
    public StringBuffer createBuffer(String tableName, String fileName, String voName, String voAllName, String daoImplAllName, List ausd) {
        StringBuffer buffer = new StringBuffer();
        Map<String, String> map = getColumnAndType(tableName);
        String modelPath = "";
        String folderPathOfDaoImpl = BaseUtil.getFolderPath(daoImplAllName);
        modelPath = folderPathOfDaoImpl.substring(0, folderPathOfDaoImpl.length() - 4);
        String controllerPath = modelPath.replace("dao.", "controllers");
        String servicePath = modelPath.replace("dao", "service");
        buffer.append("package " + controllerPath + ";\r\n\n");
        buffer.append("import com.google.common.collect.Maps;\r\n");
        buffer.append("import com.newaim.core.contoller.ControllerBase;\r\n");
        buffer.append("import com.newaim.core.utils.RestResult;\r\n");
        buffer.append("import com.newaim.core.utils.ServletUtils;\r\n");
        buffer.append("import com.newaim.purchase.admin.account.entity.Role;\r\n");
        buffer.append("import com.newaim.purchase.admin.account.service.RoleService;\r\n");
        buffer.append("import com.newaim.purchase.admin.account.vo.RoleVo;\r\n");
        buffer.append("import org.apache.commons.lang3.StringUtils;\r\n");
        buffer.append("import org.apache.shiro.authz.annotation.Logical;\r\n");
        buffer.append("import org.apache.shiro.authz.annotation.RequiresPermissions;\r\n");
        buffer.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        buffer.append("import org.springframework.data.domain.Page;\r\n");
        buffer.append("import org.springframework.web.bind.annotation.*;\r\n");

        buffer.append("import javax.servlet.ServletRequest;\r\n");
        buffer.append("import java.util.Date;\r\n");
        buffer.append("import java.util.LinkedHashMap;\r\n");
        buffer.append(" import " + voAllName + "; \r\n");
        buffer.append(" import " + servicePath + voName + "Service; \r\n");

        buffer.append(" @RestController \r\n");
        buffer.append(" @RequestMapping(\"/admin/" + low(voName) + "\") \r\n");
        buffer.append(" public class " + voName + "Controller extends ControllerBase { \r\n");

        buffer.append("     @Autowired \r\n");
        buffer.append("     private " + voName + "Service " + low(voName) + "Service; \r\n");


        if (ausd.contains("select")) {
            buffer.append("     @RequiresPermissions(\"" + voName + ":normal:list\") \r\n");
            buffer.append("     @PostMapping(\"/list\") \r\n");
            buffer.append("     public RestResult list(ServletRequest request, String sort, String keywords, \r\n");
            buffer.append("     		@RequestParam(value = \"page\", defaultValue = \"1\") int pageNumber, \r\n");
            buffer.append("             @RequestParam(value = \"limit\", defaultValue = PAGE_SIZE) int pageSize \r\n");
            buffer.append("      ){ \r\n");
            buffer.append(" 		RestResult result = new RestResult(); \r\n");
            buffer.append(" 		try { \r\n");
            buffer.append("             LinkedHashMap<String, Object> params = Maps.newLinkedHashMap(); \r\n");
            buffer.append(" 			if(StringUtils.isNotBlank(keywords)){ \r\n");

            for (Map.Entry<String, String> entry : map.entrySet()) {
                if ("String".equals(fieldToEntityType(entry.getValue()))) {
                    buffer.append(" 				params.put(\"" + fieldToProperty(entry.getKey()) + "-S-LK-OR\", keywords); \r\n");
                }
            }
            buffer.append(" 			}else{ \r\n");
            buffer.append(" 				params = ServletUtils.getParametersStartingWith(request); \r\n");
            buffer.append(" 			} \r\n");
            buffer.append(" 			params = setParams(params, \"" + voName + "\", \":4:3:2:1\", false); \r\n");
            buffer.append(" 			Page<" + voName + "Vo> page = " + low(voName) + "Service.list(params, pageNumber, pageSize, getSort(sort)); \r\n");
            buffer.append(" 			result.setSuccess(true).setPage(page).setMsg(localeMessageSource.getMsgListSuccess()); \r\n");

            buffer.append(" 		} catch (Exception e) { \r\n");
            buffer.append(" 			result.setSuccess(false).setMsg(localeMessageSource.getMsgListFailure(e.getMessage())); \r\n");
            buffer.append(" 		} \r\n");
            buffer.append("         return result; \r\n");
            buffer.append("     } \r\n");
        }

        buffer.append("     @RequiresPermissions(\"" + voName + ":normal:list\") \r\n");
        buffer.append("     @PostMapping(\"/get\") \r\n");
        buffer.append(" 	public RestResult get(String id) { \r\n");
        buffer.append(" 		RestResult result = new RestResult(); \r\n");
        buffer.append(" 		try { \r\n");
        buffer.append(" 			" + voName + "Vo rd =  " + low(voName) + "Service.get(id); \r\n");
        buffer.append(" 			result.setSuccess(true).setData(rd).setMsg(localeMessageSource.getMsgGetSuccess()); \r\n");

        buffer.append(" 		} catch (Exception e) { \r\n");
        buffer.append(" 			result.setSuccess(false).setMsg(localeMessageSource.getMsgGetFailure(e.getMessage())); \r\n");
        buffer.append(" 		} \r\n");

        buffer.append(" 		return result;  \r\n");
        buffer.append(" 	} \r\n");

        if (ausd.contains("add") || ausd.contains("update")) {
            buffer.append("     @RequiresPermissions(value = {\"" + voName + ":normal:add\", \"" + voName + ":normal:edit\"}, logical = Logical.OR) \r\n");
            buffer.append("     @PostMapping(\"/save\") \r\n");
            buffer.append(" 	public RestResult save(String act, @ModelAttribute(\"main\") " + voName + " main) { \r\n");
            buffer.append(" 		RestResult result = new RestResult(); \r\n");
            buffer.append(" 		try { \r\n");
            buffer.append(" 			if( StringUtils.isNotBlank(main.getId())){ \r\n");
            buffer.append(" 				//复制另存 \r\n");
            buffer.append(" 				if(StringUtils.isNotBlank(act) && ACT_COPY.equals(act)){ \r\n");

            buffer.append(" 					main.setCreatedAt(new Date()); \r\n");
            buffer.append(" 					main.setId(null); \r\n");

            buffer.append(" 					" + low(voName) + "Service.add(main); \r\n");
            buffer.append(" 				}else{ \r\n");
            buffer.append(" 					" + low(voName) + "Service.save(main); \r\n");
            buffer.append(" 				} \r\n");

            buffer.append(" 				result.setSuccess(true).setData(null).setMsg(localeMessageSource.getMsgSaveSuccess(\"edit\")); \r\n");

            buffer.append(" 			}else{ \r\n");

            buffer.append(" 				main.setCreatedAt(new Date());				 \r\n");
            buffer.append(" 				" + low(voName) + "Service.add(main); \r\n");

            buffer.append(" 				result.setSuccess(true).setData(null).setMsg(localeMessageSource.getMsgSaveSuccess(\"add\")); \r\n");

            buffer.append(" 			} \r\n");

            buffer.append(" 		} catch (RuntimeException e) { \r\n");
            buffer.append(" 			//result.setSuccess(false).setData(null).setMsg(localeMessageSource.getMsgOperateFailure()); \r\n");
            buffer.append(" 			result.setSuccess(false).setMsg(localeMessageSource.getMsgSaveFailure(act,e.getMessage())); \r\n");
            buffer.append(" 		} \r\n");

            buffer.append(" 		return result;  \r\n");
            buffer.append(" 	} \r\n");
        }

        if (ausd.contains("delete")) {
            buffer.append("     @RequiresPermissions(\"" + voName + ":normal:del\") \r\n");
            buffer.append(" 	@PostMapping(\"/delete\") \r\n");
            buffer.append(" 	public RestResult delete(String ids) { \r\n");
            buffer.append(" 		RestResult result = new RestResult(); \r\n");
            buffer.append(" 		try { \r\n");
            buffer.append(" 			" + low(voName) + "Service.delete(ids); \r\n");

            buffer.append(" 			result.setSuccess(true).setData(null).setMsg(localeMessageSource.getMsgDeleteSuccess()); \r\n");
            buffer.append(" 		} catch (Exception e) { \r\n");
            buffer.append(" 			result.setSuccess(false).setData(null).setMsg(localeMessageSource.getMsgOperateException(e.getMessage())); \r\n");
            buffer.append(" 		} \r\n");

            buffer.append(" 		return result; \r\n");
            buffer.append(" 	} \r\n");
        }

        buffer.append(" 	@ModelAttribute(\"main\") \r\n");
        buffer.append("     public " + voName + " main(String act, String id){ \r\n");
        buffer.append("         if(StringUtils.isNotBlank(act) && StringUtils.isNotBlank(id)){ \r\n");
        buffer.append("             return " + low(voName) + "Service.get" + voName + "(id); \r\n");
        buffer.append("         }else if(StringUtils.isNotBlank(act)){ \r\n");
        buffer.append("         	return new " + voName + "(); \r\n");
        buffer.append("         } \r\n");
        buffer.append("         return null; \r\n");
        buffer.append("     } \r\n");
        buffer.append(" } \r\n");

        return buffer;
    }


}
