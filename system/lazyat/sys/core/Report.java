package lazyat.sys.core;

import java.io.FileOutputStream;
import java.io.PrintStream;
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
		builder.append("<style type=\"text/css\">");
		builder.append("a{color:#189dc3;}");
		builder.append("span{font-family:SIMHEI,Arial;font-size:14px;margin-left:10px;margin-right:10px;}");
		builder.append(".title{height:40px;line-height:40px;margin-top:30px;}");
		builder.append(".title span{font-size:18px;font-weight:bold;}");
		builder.append(".line{height:30px;line-height:30px;margin-top:-1px;border:solid 1px #939393;}");
		builder.append(".line div{float:left;height:30px;}");
		builder.append("</style>");
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
			builder.append("<div class=\"title\"><span>" + logName + "</span></div>");
			builder.append("<hr/>");
			Iterator<String> itemIterator = log.getDeque().iterator();
			while (itemIterator.hasNext()) { // 输出所有日志行
				String logKey = itemIterator.next().toString();
				HashMap<String, String> logValue = log.getMap().get(logKey);
				builder.append("<div class=\"line\" style=\"background-color:" + logValue.get("color") + ";\">");
				String styleFiledA = "style=\"width:15%;\"";
				builder.append("<div " + styleFiledA + "><span>" + logValue.get("time") + "</span></div>");
				String styleFiledB = "style=\"width:45%;border-left:solid 1px #939393;\"";
				builder.append("<div " + styleFiledB + "><span>" + logValue.get("message") + "</span></div>");
				String styleFiledC = "style=\"width:39%;border-left:solid 1px #939393;\"";
				String remark = logValue.get("remark");
				if (remark.matches(".+\\.png")) { // 如果备注是PNG格式的图片，则显示成链接
					remark = "<a href=\"" + remark + "\" target=\"_blank\">屏幕截图 >></a>";
				}
				builder.append("<div " + styleFiledC + "><span>" + remark + "</span></div>");
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
			FileOutputStream fileOutputStream = new FileOutputStream("report/" + Server.getCommander().getNamex() + ".html");
			PrintStream printStream = new PrintStream(fileOutputStream);
			printStream.println(builder.toString());
			printStream.close();
		} catch (Exception e) { // 生成错误
			e.printStackTrace();
		}
	}
}
