package lazyat.sys.core;

import lazyat.sys.driver.ExtendWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {

	/**
	 * Page: 构建方法
	 */
	public Page() {
		PageFactory.initElements(driver, this);
		load();
	}

	/**
	 * browser: 浏览器对象实例
	 */
	protected Browser browser = Server.getCommander().getThreadMap().get(Thread.currentThread().getId());

	/**
	 * driver: 测试驱动
	 */
	protected WebDriver driver = browser.getDriver();

	/**
	 * driverx: 扩展测试驱动
	 */
	protected ExtendWebDriver driverx = new ExtendWebDriver(driver);

	/**
	 * log: 测试日志
	 */
	protected Log log = browser.getLog();

	/**
	 * load: 页面载入执行代码
	 * @return void
	 */
	protected void load() {
		// 基类不做任何事
	};

	/**
	 * getTitle: 获取页面标题
	 * @return title 页面标题
	 */
	public String getTitle() {
		return driver.getTitle();
	}
}
