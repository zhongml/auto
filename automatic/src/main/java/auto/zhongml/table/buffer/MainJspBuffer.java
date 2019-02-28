package auto.zhongml.table.buffer;

import java.util.List;
import java.util.Map;

import auto.zhongml.table.BaseUtil;
import auto.zhongml.table.dao.CreateBuffer;

public class MainJspBuffer  extends BaseUtil  implements CreateBuffer  {

	@Override
	public StringBuffer createBuffer(String tableName, String fileName,
			String voName,String voAllName,String daoImplAllName, List ausd) {
		StringBuffer buffer=new StringBuffer();
		Map<String,String> map=getColumnAndType(tableName);
		buffer.append(" <%@page contentType=\"text/html; utf-8\" pageEncoding=\"utf-8\"%> \r\n");
		buffer.append(" <html xmlns=\"http://www.w3.org/1999/xhtml\">  \r\n");
		buffer.append(" <head>  \r\n");
		buffer.append(" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />  \r\n");
		buffer.append(" <title>${initParam.pageTitle}</title> \r\n");
		buffer.append("  <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/stylecon.css\">  \r\n");  
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/common.css\"/> \r\n");
		buffer.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"${csscontextPath}/jquery.treeview.css\"/> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/jquery/jquery.min.js\"></script> \r\n");
		buffer.append(" <script type=\"text/javascript\" src=\"${jscontextPath }/ecm-taglib/frame/frame.js\"></script> \r\n");
		buffer.append(" </head>  \r\n");
		buffer.append(" <body style=\"overflow-x:hidden;overflow-y:hidden;\">  \r\n");
		buffer.append(" <div class=\"tykjbox\">  \r\n");
		buffer.append(" 		<div class=\"khyjwall\">  \r\n");
		buffer.append(" 		    <div id=\"divheight\"  style=\"margin-left:-10px;margin-top:-10px;\">  \r\n");
		buffer.append(" 		  	   <iframe name=\"list\" id=\"list\" src=\"${pageContext.request.contextPath }/order/discount/listPage.do\" \r\n");
		buffer.append(" 				frameborder=\"0\" width=\"100%\"  FRAMEBORDER=0 height=\"100%\"  \r\n");
		buffer.append(" 				SCROLLING=NO></iframe>  \r\n");
		buffer.append(" 	     </div>  \r\n");
		buffer.append(" 	</div>  \r\n");
		buffer.append(" </div>  \r\n");
		buffer.append(" </body>  \r\n");
		buffer.append(" </html>  \r\n");
		return buffer;
	}

}
