package lazyat.tc.global;

import lazyat.sys.core.Case;

public class Demo2 extends Case {

	protected void execute() {
		open();
	}

	public void open() {
		driver.get("http://www.jd.com");
		judge.pass("打开京东首页", driverx.screenshot());
	}
}
