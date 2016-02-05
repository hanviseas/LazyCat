package lazyat.tc.global;

import lazyat.sys.core.Case;

public class Demo extends Case {

	protected void execute() {
		lazyat.page.home.Demo demoPage = new lazyat.page.home.Demo();
		judge.pass("打开百度首页", screenshot());
		judge.isEqual(demoPage.getButtonText(), "百度一下", "搜索链接文字");
		demoPage.search();
	}
}
