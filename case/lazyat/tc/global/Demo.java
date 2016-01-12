package lazyat.tc.global;

import lazyat.sys.core.Case;

public class Demo extends Case {

	protected void execute() {
		driver.get("http://www.baidu.com");
		judge.pass("打开百度首页", driverx.screenshot());
		judge.isEqual(driverx.id("su").getAttribute("value"), "百度一下", "搜索链接文字");
		new Demo2().open();
	}
}
