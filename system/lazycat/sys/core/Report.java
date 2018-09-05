package lazycat.sys.core;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Report {

	/**
	 * stringBuilder: 字符串构建器
	 */
	private static StringBuilder builder = new StringBuilder();

	/**
	 * 构建Html Head
	 * @return void
	 */
	private static void buildHead() {
		builder.append("<head>");
		builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		builder.append("<title>自动化测试报告: " + Server.getCommander().getNamex() + "</title>");
		builder.append("<link rel=\"stylesheet\" href=\"./report.css\" />");
		builder.append("</head>");
	}

	/**
	 * 构建Html Body
	 * @return void
	 */
	private static void buildBody() {
		builder.append("<body>");
		Iterator<Map.Entry<String, Log>> logIterator = Server.getCommander().getLogMap().entrySet().iterator();
		while (logIterator.hasNext()) {
			Map.Entry<String, Log> logEntry = logIterator.next();
			String logName = logEntry.getKey();
			Log log = logEntry.getValue();
			Integer[] summary = log.getSummary();
			builder.append("<div class=\"title\"><span>" + logName + "</span></div>");
			builder.append("<div class=\"summary\">");
			builder.append("<span class=\"pass_num\">通过: " + summary[0] + "</span>");
			builder.append("<span class=\"fail_num\">失败: " + summary[1] + "</span>");
			builder.append("<span class=\"notice_num\">警告: " + summary[2] + "</span>");
			builder.append("<span class=\"error_num\">错误: " + summary[3] + "</span>");
			builder.append("</div>");
			builder.append("<hr/>");
			builder.append("<div class=\"line head_line\" style=\"color:#ffffff\">");
			builder.append("<div class=\"col_time\"><div class=\"inner\"><span>时间</span></div></div>");
			builder.append("<div class=\"col_message\"><div class=\"inner\"><span>信息</span></div></div>");
			builder.append("<div class=\"col_remark\"><div class=\"inner\"><span>备注</span></div></div>");
			builder.append("</div>");
			Iterator<String> itemIterator = log.getDeque().iterator();
			while (itemIterator.hasNext()) { // 输出所有日志行
				String logKey = itemIterator.next().toString();
				HashMap<String, String> logValue = log.getMap().get(logKey);
				builder.append("<div class=\"line\" style=\"color:" + logValue.get("color") + ";\">");
				builder.append("<div class=\"col_time\"><div class=\"inner\"><span>" + logValue.get("time") + "</span></div></div>");
				builder.append("<div class=\"col_message\"><div class=\"inner\"><span>" + logValue.get("message") + "</span></div></div>");
				String remark = logValue.get("remark");
				if (remark.matches(".+\\.png")) { // 如果备注是PNG格式的图片，则显示成链接
					remark = "<a href=\"" + remark + "\" target=\"_blank\">屏幕截图 >></a>";
				}
				builder.append("<div class=\"col_remark\"><div class=\"inner\"><span>" + remark + "</span></div></div>");
				builder.append("<div class=\"clear\"></div>");
				builder.append("</div>");
			}
		}
		builder.append("</body>");
	}

	/**
	 * save: 保存HTML文档
	 * @return void
	 */
	public static void save() {
		builder.append("<html>");
		buildHead();
		buildBody();
		builder.append("</html>");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(Server.getCurrentPath() + "/report/" + Server.getCommander().getNamex() + ".html");
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			outputStreamWriter.append(builder.toString());
			outputStreamWriter.close();
		} catch (Exception e) { // 生成错误
			e.printStackTrace();
		}
	}
}
