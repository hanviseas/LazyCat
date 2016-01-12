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
	private static StringBuilder stringBuilder = new StringBuilder();

	/**
	 * 构建Html Head
	 * @return void
	 */
	private static void buildHead() {
		stringBuilder.append("<head>");
		stringBuilder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		stringBuilder.append("<title>自动化测试报告: " + Server.getCommander().getNamex() + "</title>");
		stringBuilder.append("<style type=\"text/css\">");
		stringBuilder.append("a{color:#189dc3;}");
		stringBuilder.append("span{font-family:SIMHEI,Arial;font-size:14px;margin-left:10px;margin-right:10px;}");
		stringBuilder.append(".title{height:40px;line-height:40px;margin-top:30px;}");
		stringBuilder.append(".title span{font-size:18px;font-weight:bold;}");
		stringBuilder.append(".line{height:30px;line-height:30px;margin-top:-1px;border:solid 1px #939393;}");
		stringBuilder.append(".line div{float:left;height:30px;}");
		stringBuilder.append("</style>");
		stringBuilder.append("</head>");
	}

	/**
	 * 构建Html Body
	 * @return void
	 */
	private static void buildBody() {
		stringBuilder.append("<body>");
		Iterator<Map.Entry<String, Judge>> judgeIterator = Server.getCommander().getJudgeMap().entrySet().iterator();
		while (judgeIterator.hasNext()) {
			Map.Entry<String, Judge> judgeEntry = judgeIterator.next();
			String judgeName = judgeEntry.getKey();
			Judge judge = judgeEntry.getValue();
			stringBuilder.append("<div class=\"title\"><span>" + judgeName + "</span></div>");
			stringBuilder.append("<hr/>");
			Iterator<String> logIterator = judge.getLogDeque().iterator();
			while (logIterator.hasNext()) { // 输出所有日志行
				String logKey = logIterator.next().toString();
				HashMap<String, String> logValue = judge.getLogMap().get(logKey);
				stringBuilder.append("<div class=\"line\" style=\"background-color:" + logValue.get("color") + ";\">");
				String styleFiledA = "style=\"width:15%;\"";
				stringBuilder.append("<div " + styleFiledA + "><span>" + logValue.get("time") + "</span></div>");
				String styleFiledB = "style=\"width:45%;border-left:solid 1px #939393;\"";
				stringBuilder.append("<div " + styleFiledB + "><span>" + logValue.get("message") + "</span></div>");
				String styleFiledC = "style=\"width:39%;border-left:solid 1px #939393;\"";
				String remark = logValue.get("remark");
				if (remark.matches(".+\\.png")) { // 如果备注是PNG格式的图片，则显示成链接
					remark = "<a href=\"" + remark + "\" target=\"_blank\">屏幕截图 >></a>";
				}
				stringBuilder.append("<div " + styleFiledC + "><span>" + remark + "</span></div>");
				stringBuilder.append("</div>");
			}
		}
		stringBuilder.append("</body>");
	}

	/**
	 * save: 保存HTML文档
	 * @return void
	 */
	public static void save() {
		stringBuilder.append("<html>");
		buildHead();
		buildBody();
		stringBuilder.append("</html>");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("report/" + Server.getCommander().getNamex() + ".html");
			PrintStream printStream = new PrintStream(fileOutputStream);
			printStream.println(stringBuilder.toString());
			printStream.close();
		} catch (Exception e) { // 生成错误
			System.out.println(e);
		}
	}
}
