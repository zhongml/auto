package auto.zhongml.table.buffer;

import java.util.List;
import java.util.Map;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

public class EditJspBuffer  extends BaseUtil  implements CreateBuffer  {

	@Override
	public StringBuffer createBuffer(String tableName, String fileName,
			String voName,String voAllName,String daoImplAllName, List ausd) {
		StringBuffer buffer=new StringBuffer();
		Map<String,String> map=getColumnAndType(tableName);
		buffer.append("<!DOCTYPE html> \r\n");
		buffer.append(" <%@page contentType=\"text/html; utf-8\" pageEncoding=\"utf-8\"%> \r\n");
		buffer.append(" <%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\"%> \r\n");
		buffer.append(" <%@ taglib uri=\"http://ycs168.cn/portal\" prefix=\"portal\"%> \r\n");
		buffer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"> \r\n");
		buffer.append("<head> \r\n");
		buffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \r\n");
		buffer.append("<title>编辑转账信息</title> \r\n");
		buffer.append(" <c:set var=\"ctx\" value=\"${pageContext.request.contextPath }\" /> \r\n");

		buffer.append("<script type=\"text/javascript\"> \r\n");
		buffer.append("	//为全局js提供上下文\r\n");
		buffer.append("	var _CTX = '${ctx}'; \r\n");
		buffer.append("</script> \r\n");

		buffer.append(" <script type=\"text/javascript\" src=\"${pageContext.request.contextPath }/resources/js/ecm-taglib/window/form-window.js\"></script> \r\n");
		buffer.append("  <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/jquery/jquery.min.js\"></script>  \r\n");

		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/frame/frame.js\"></script> \r\n");

		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath}/ecm-taglib/viewport.js\"></script> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/message/Message.js\"></script> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/jquery/jquery.blockUI.js\"></script> \r\n");

		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/json2/json2.js\"></script> \r\n");

		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${pageContext.request.contextPath }/resources/jquery-miniui/css/boot.css\"> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${pageContext.request.contextPath }/resources/jquery-miniui/boot.js\"></script> \r\n");

		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/ecm-taglib/css/loadMask.css\"/> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/stylenext.css\"/> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/css/tab_list.css\"/> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${jscontextPath }/ecm-taglib/message/skin/default/Message.css\" /> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/common.css\"/> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${css_path}/css/index.css\"> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${css_path}/css/font.css\"> \r\n");
		buffer.append(" <link href=\"${jscontextPath }/ecm-taglib/jquery/validate/validate.css\" rel=\"stylesheet\" type=\"text/css\"/> \r\n");
		buffer.append("<style type=\"text/css\"> \r\n");
		buffer.append(".mini-buttonedit-border \r\n");
		buffer.append("{ \r\n");

		buffer.append("    height: 29px; \r\n");

		buffer.append("} \r\n");
		buffer.append(".tbxbox1{padding: 44px 0px 44px 0px;} \r\n");
		buffer.append("</style> \r\n");


		buffer.append("</head> \r\n");
		buffer.append("<portal:Viewport> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/jquery/ajaxfileupload-multi.js\"></script> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/jquery/validate/jquery.validate.js\"></script> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/jquery/validate/jquery.validate.extend.js\"></script> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/jquery/validate/jquery.validate.lang-zh_CN.js\"></script> \r\n");
		buffer.append("<body style=\"background-color: #FFF;\"> \r\n");
		buffer.append("	<div style=\"width: 300px\"> \r\n");
		buffer.append("		<form id=\"formDefinitionForm\" name=\"formDefinitionForm\" method=\"post\" \r\n");
		buffer.append("			action=\"\"> \r\n");
		buffer.append("			<div class=\"tbxbox1\" id=\"con_grsz_2\"> \r\n");
		buffer.append("				<table class=\"tabinput\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" \r\n");
		buffer.append("					border=\"0\"> \r\n");
		buffer.append("					<tbody> \r\n");
		
		
	
		for(Map.Entry<String, String> entry : map.entrySet()) {
			
			buffer.append("					    <tr > \r\n");
			buffer.append("							<td>"+fieldToProperty(entry.getKey())+"： \r\n");
			buffer.append("							</td> \r\n");
			buffer.append(" 							<td class=\"biaodan03\"><input  type=\"text\" id=\""+fieldToProperty(entry.getKey())+"\" \r\n");
			buffer.append(" 								name=\""+fieldToProperty(entry.getKey())+"\" class=\"inptxt\" style=\"width: 170px;\" value=\"${"+low(voName)+"."+fieldToProperty(entry.getKey())+"}\"/></td> \r\n");
			buffer.append("						</tr> \r\n");
		
		}
		
		
		buffer.append("						<tr style='display: none;'> \r\n");
		buffer.append("							<td>创建人：</td> \r\n");
		buffer.append(" 							<td>${at.createUserName}</td> \r\n");
		buffer.append("						</tr> \r\n");
		buffer.append("						<tr style='display: none;'> \r\n");
		buffer.append("							<td>创建时间：</td> \r\n");
		buffer.append(" 							<td>${at.strCreateDate}</td> \r\n");
		buffer.append("						</tr> \r\n");
		buffer.append("					</tbody> \r\n");
		buffer.append("				</table> \r\n");
		buffer.append("			</div> \r\n");
		buffer.append("		</form> \r\n");

		buffer.append("		<div id=\"content_wrap\" class=\"content_wrap\"> \r\n");
		buffer.append("			<table  border=\"0\" cellspacing=\"2\" cellpadding=\"0\"> \r\n");
		buffer.append("				<tr> \r\n");
		buffer.append("					<td colspan=\"2\" align=\"center\"> \r\n");
		buffer.append("						<div class=\"btn_right\"> \r\n");
		buffer.append("							<a href=\"#none\" class=\"btn0 btn03\" onclick=\"saveOrUpdate()\">确&nbsp;定</a> \r\n");
		buffer.append("						</div> \r\n");
		buffer.append("						<div class=\"btn_right\"> \r\n");
		buffer.append("							<a href=\"#none\" class=\"btn0 btn03\" \r\n");
		buffer.append("								onclick=\"toCloseWin()\">取&nbsp;消</a> \r\n");
		buffer.append("						</div> \r\n");
		buffer.append("					</td> \r\n");
		buffer.append("				</tr> \r\n");
		buffer.append("			</table> \r\n");
		buffer.append("		</div> \r\n");
		buffer.append("	</div> \r\n");

		buffer.append("</body> \r\n");
		buffer.append("</portal:Viewport> \r\n");
		buffer.append("<script type=\"text/javascript\"> \r\n");
		buffer.append("	$(document).ready(function() { \r\n");

		buffer.append("          if(\"add\"==\"${type}\"){ \r\n");
		buffer.append("				/*检查表单 start  */ \r\n");
		buffer.append("				$(\"#formDefinitionForm\").validate({ \r\n");
		buffer.append("									rules : { \r\n");
		buffer.append("										outBank : { \r\n");
		buffer.append("											required : true \r\n");
		buffer.append("										}, \r\n");
		buffer.append("										outAccountName : { \r\n");
		buffer.append("											required : true \r\n");
		buffer.append("										}, \r\n");
		buffer.append("										outAccountNum : { \r\n");
		buffer.append("											required : true \r\n");
		buffer.append("										},/* \r\n");
		buffer.append("										inAccountNum : { \r\n");
		buffer.append("											required : true \r\n");
		buffer.append("										}, */ \r\n");
		buffer.append("										money : { \r\n");
		buffer.append("											required : true \r\n");
		buffer.append("										} \r\n");
		buffer.append("									}, \r\n");
		buffer.append("									messages : { \r\n");
		buffer.append("										outBank : { \r\n");
		buffer.append("											required : \"请输入开户行！\" \r\n");
		buffer.append("										}, \r\n");
		buffer.append("										outAccountName : { \r\n");
		buffer.append("											required : \"请输入账户名！\" \r\n");
		buffer.append("										}, \r\n");
		buffer.append("										/* inAccountNum : { \r\n");
		buffer.append("											required : \"请选择转入账户！\" \r\n");
		buffer.append("										}, */ \r\n");
		buffer.append("										money : { \r\n");
		buffer.append("											required : \"请选择金额！\" \r\n");
		buffer.append("										} \r\n");
		buffer.append("									}, \r\n");
		buffer.append("									errorPlacement : function( \r\n");
		buffer.append("											error, element) { \r\n");
		buffer.append("										error \r\n");
		buffer.append("												.css({//设置提示框的样式 \r\n");
		buffer.append("													position : \"absolute\", \r\n");
		buffer.append("													top : element \r\n");
		buffer.append("															.offset().top \r\n");
		buffer.append("															- -25, \r\n");
		buffer.append("													left : element \r\n");
		buffer.append("															.offset().left, \r\n");
		buffer.append("													whiteSpace : \"nowrap\", \r\n");
		buffer.append("													zIndex : 9999 \r\n");
		buffer.append("												}); \r\n");
		buffer.append("										error.appendTo(element \r\n");
		buffer.append("												.parent()); \r\n");
		buffer.append("										element \r\n");
		buffer.append("												.mouseover(function() { \r\n");
		buffer.append("													error.show(); \r\n");
		buffer.append("												}); \r\n");
		buffer.append("										element \r\n");
		buffer.append("												.mouseout(function() { \r\n");
		buffer.append("													error.hide(); \r\n");
		buffer.append("												}); \r\n");
		buffer.append("									}, \r\n");
		buffer.append("									success : function(label) { \r\n");
		buffer.append("										label.remove(); \r\n");
		buffer.append("									}, \r\n");
		buffer.append("									errorClass : \"errorMessage\" \r\n");
		buffer.append("								}); \r\n");
		buffer.append("				/*检查表单 end  */ \r\n");
		buffer.append("				} \r\n");

		buffer.append("}); \r\n");
		buffer.append("</script> \r\n");
				
		buffer.append("<script> \r\n");


		buffer.append(" 	/*保存或修改 start  */ \r\n");
		buffer.append(" 	function saveOrUpdate() { \r\n");
		buffer.append(" 		$(\"body\").mask(\"处理中，请稍后。。。\"); \r\n");
		buffer.append(" 		if (!$(\"#formDefinitionForm\").valid()) { \r\n");
		buffer.append(" 			return; \r\n");
		buffer.append(" 		}; \r\n");
		
		
		
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			buffer.append(" 			//"+fieldToProperty(entry.getKey())+"\r\n");
			buffer.append(" 		formDefinitionForm."+fieldToProperty(entry.getKey())+".value = $.trim(formDefinitionForm."+fieldToProperty(entry.getKey())+".value); \r\n");
		}

		buffer.append(" 		$.ajax({ \r\n");
		buffer.append(" 			url : 'edit.do?saveOrUpdate', \r\n");
		buffer.append(" 			async : false, \r\n");
		buffer.append(" 			type : 'post', \r\n");
		buffer.append(" 			data : $(\"#formDefinitionForm\").serialize(), \r\n");
		buffer.append(" 			success : function(msg) { \r\n");
		buffer.append(" 				if(msg==true){ \r\n");
		buffer.append(" 					$(\"body\").unmask(); \r\n");
		buffer.append(" 					Message.succeedInfo({ \r\n");
		buffer.append(" 						title : \"提示\", \r\n");
		buffer.append(" 						message : \"保存成功：\", \r\n");
		buffer.append(" 						handler : function() { \r\n");
		
			
		buffer.append(" 	                          parent.frames['iframehtm'].frames['list'].reloadGrid(); \r\n");
		buffer.append(" 	                          parent.parent.closeWindow(); \r\n");
		buffer.append(" 						} \r\n");
		buffer.append(" 					}); \r\n");
							
		buffer.append(" 				}else{ \r\n");
		buffer.append(" 					Message.errorInfo({ \r\n");
		buffer.append(" 						title : \"错误\", \r\n");
		buffer.append(" 						message : \"保存失败\" \r\n");
		buffer.append(" 					}); \r\n");
		buffer.append(" 				} \r\n");
		buffer.append(" 			}, \r\n");
		buffer.append(" 			error : function(msg) { \r\n");
		buffer.append(" 				Message.errorInfo({ \r\n");
		buffer.append(" 					title : \"错误\", \r\n");
		buffer.append(" 					message : \"保存失败：\" + data.errMsg \r\n");
		buffer.append(" 				}); \r\n");
		buffer.append(" 			} \r\n");
		buffer.append(" 		}); \r\n");
		buffer.append(" 	} \r\n");
		buffer.append(" 	/*保存或修改 end  */ \r\n");
		buffer.append(" 	function toCloseWin(){ \r\n");
		buffer.append(" 		parent.parent.closeWindow();  \r\n");
		buffer.append(" 	} \r\n");
		buffer.append("</script> \r\n");

				

		buffer.append("</html> \r\n");
		return buffer;
	}

}
