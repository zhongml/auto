package auto.zhongml.table.buffer;

import java.util.List;
import java.util.Map;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

public class ListJspBuffer  extends BaseUtil  implements CreateBuffer  {

	@Override
	public StringBuffer createBuffer(String tableName, String fileName,
			String voName,String voAllName,String daoImplAllName, List ausd) {
		StringBuffer buffer=new StringBuffer();
		Map<String,String> map=getColumnAndType(tableName);
		buffer.append(" <%@page contentType=\"text/html; utf-8\" pageEncoding=\"utf-8\"%> \r\n");
		buffer.append(" <%@ taglib uri=\"http://ycs168.cn/portal\" prefix=\"portal\"%> \r\n");
		buffer.append(" <%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/jquery/jquery.min.js\"></script> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath}/ecm-taglib/viewport.js\"></script> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/ecm-taglib/css/loadMask.css\"/> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/stylenext.css\"/> \r\n");
		buffer.append(" <div style=\"background-color:#fff;margin:0 10px 0 10px;padding:10px 0\">\r\n");
		buffer.append(" <portal:Viewport> \r\n");
		buffer.append("      <portal:ToolBar id=\"toolbar\"> \r\n");
		
		buffer.append("         <div class=\"moresearchbox\">  \r\n");
		buffer.append("         <span class=\"search\"><input id=\"TITLE\" name=\"TITLE\" value=\"标题\" type=\"text\" class=\"inputtxt\" /><input name=\"\" type=\"button\" class=\"inputbtn\" onclick=\"cx()\" value=\"\" /></span>  \r\n");
		buffer.append("         <a href=\"#\" class=\"btn0 btn03\" onclick=\"deltzgg();\" >删除</a>  \r\n");
		buffer.append("         <a href=\"#\" class=\"btn0 btn03\"  onclick=\"add();\">新增</a>  \r\n");
		buffer.append("         </div>  \r\n");
		  
		buffer.append("    </portal:ToolBar>   \r\n");
		buffer.append(" 	<portal:Grid id=\"grid\" autoLoad=\"false\" rowClick=\"false\" rowDblclick=\"false\" \r\n");
		buffer.append(" 		dataServiceUrl=\"${pageContext.request.contextPath}/"+low(voName)+"/list.do?action=load\" \r\n");
		buffer.append(" 		showPagingToolbar=\"true\" draggable=\"false\" pageSize=\"15\" \r\n");
		buffer.append(" 		distinct=\"true\" returnDataType=\"xml\" > \r\n");
		buffer.append(" 		<portal:CheckboxColumn title=\"选择\" fieldName=\"Id\" align=\"center\"  asRowDataId=\"true\"  width=\"8% \"/> \r\n");
		
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			buffer.append(" 		<portal:Column title=\""+fieldToProperty(entry.getKey())+"\" fieldName=\""+fieldToProperty(entry.getKey())+"\" align=\"left\" width=\"20% \" /> \r\n");
		}
		
		buffer.append(" 		<portal:Column title=\"操作\" fieldName=\"cz\" align=\"center\"  width=\"10% \" /> \r\n");
		buffer.append(" 	</portal:Grid> \r\n");
		buffer.append(" </portal:Viewport> \r\n");
		buffer.append(" </div>\r\n");
		
		buffer.append("  <script type=\"text/javascript\">\r\n");
		buffer.append(" 	$(document).ready(function() {\r\n");
		buffer.append(" 		grid.reloadData();\r\n");
		buffer.append(" 		parent.resize();\r\n");
		buffer.append(" 		$(\"#grid_body\").height($(window).height()-110);\r\n");
		buffer.append(" 	});\r\n");
			
		buffer.append(" 	function cx(){\r\n");
		buffer.append(" 		grid.reloadData(\"TITLE\",$(\"#TITLE\").val());\r\n");
		buffer.append(" 	}\r\n");
		
		buffer.append(" 	function reloadGrid(){\r\n");
		buffer.append(" 		grid.reloadData(\"TITLE\",$(\"#TITLE\").val());\r\n");
		buffer.append(" 	}\r\n");
			
		buffer.append(" 	function edit(infoId){\r\n");
		buffer.append(" 		var url=\"\";\r\n");
		buffer.append("		url=\"${pageContext.request.contextPath }/well/edit.do?id=\"+infoId;\r\n");
		buffer.append(" 		var height = 530;\r\n");
		buffer.append(" 		var width = 800;\r\n");
		buffer.append("		var imgurl =\"${project_path}/resources/images/customer/customerinformation/customernomal.png\"; \r\n");
		buffer.append(" 		parent.parent.openWindow(imgurl,\"更新通知\" ,width,height,\"<iframe id=\\\"mmxgPageFrame0\\\" src=\\\"\"+url+\"\\\" frameborder=\\\"0\\\" width=\\\"100%\\\" height=\\\"\" + height + \"\\\" scrolling=\\\"no\\\"></iframe>\");\r\n");

		buffer.append(" 	}\r\n");
		buffer.append(" 	function add(){\r\n");
		buffer.append(" 		var url=\"\";\r\n");
		buffer.append("		url=\"${pageContext.request.contextPath }/well/edit.do\"; \r\n");
		buffer.append(" 		var height = 530;\r\n");
		buffer.append(" 		var width = 800;\r\n");
		buffer.append("		var imgurl =\"${project_path}/resources/images/customer/customerinformation/customernomal.png\"; \r\n");
		buffer.append(" 		parent.parent.openWindow(imgurl,\"新增通知\" ,width,height,\"<iframe id=\\\"mmxgPageFrame0\\\" src=\\\"\"+url+\"\\\" frameborder=\\\"0\\\" width=\\\"100%\\\" height=\\\"\" + height + \"\\\" scrolling=\\\"no\\\"></iframe>\");\r\n");

		buffer.append(" 	}\r\n");
		buffer.append(" 	function del(obj){\r\n");
		buffer.append(" 			removetzgg(obj);\r\n");
		buffer.append(" 	}\r\n");
			
		buffer.append(" 	function deltzgg(){\r\n");
		buffer.append(" 		var rows = grid.getSelectRows().join(\",\");\r\n");
		buffer.append(" 		if(rows.length==0){\r\n");
		buffer.append(" 			Message.alert({\"title\" : \"提示\", \"message\" : \"请至少选择一条记录进行操作\"});\r\n");
		buffer.append(" 			return false;\r\n");
		buffer.append(" 		}else{\r\n");
		buffer.append(" 			removetzgg(rows);\r\n");
		buffer.append(" 		}\r\n");
		buffer.append(" 	}\r\n");
			
		buffer.append(" 	function removetzgg(rows){\r\n");
		buffer.append(" 	Message.confirmInfo({\r\n");
		buffer.append(" 			title : \"提示\",\r\n");
		buffer.append(" 			message : \"确认要删除选中的记录？\",\r\n");
		buffer.append(" 			handler : function(a){\r\n");
		buffer.append(" 				if(a==\"ok\"){\r\n");
		buffer.append(" 		var param = {};\r\n");
		buffer.append(" 		param.infoIds =rows ;\r\n");
		buffer.append(" 		  $.ajax({\r\n");
		buffer.append("			url:'${pageContext.request.contextPath}/well/del"+voName+".do',\r\n");
		buffer.append(" 			type:'POST',\r\n");
		buffer.append(" 			data:{param:JSON.stringify(param)},\r\n");
		buffer.append(" 			success:function(data){\r\n");
		buffer.append(" 				if(data.flag==\"true\"){\r\n");
		buffer.append(" 					Message.alert({title:\"提示\",message:\"操作成功\",handler : function() {\r\n");
		buffer.append(" 						grid.loadData();\r\n");
		buffer.append(" 						}});\r\n");
		buffer.append(" 				}else{\r\n");
		buffer.append(" 					Message.errorInfo(\"处理失败：\"+data.message);\r\n");
		buffer.append(" 					grid.loadData();\r\n");
		buffer.append(" 				}\r\n");
		buffer.append(" 			},\r\n");
		buffer.append(" 			error:function(){\r\n");
		buffer.append(" 				Message.errorInfo(\"处理失败稍后再试！\");\r\n");
		buffer.append(" 			}\r\n");
		buffer.append(" 		});  \r\n");
		buffer.append(" 		} \r\n");
		buffer.append(" 		} \r\n");
		buffer.append(" 		}); \r\n");
		buffer.append(" 	}\r\n");
		buffer.append("  </script>\r\n");
		return buffer;
	}

}
