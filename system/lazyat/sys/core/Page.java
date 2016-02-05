package lazyat.sys.core;

import lazyat.sys.driver.ExtendWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

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
	protected Browser browser = initBrowser();

	/**
	 * initBrowser: 初始化浏览器对象实例
	 * @return browser 浏览器对象实例
	 */
	protected Browser initBrowser() {
		return Server.getCommander().getThreadMap().get(Thread.currentThread().getId());
	}

	/**
	 * driver: 浏览器测试驱动
	 */
	protected WebDriver driver = initDriver();

	/**
	 * 初始化浏览器测试驱动
	 * @return driver 浏览器测试驱动
	 */
	protected WebDriver initDriver() {
		return browser.getDriver();
	}

	/**
	 * driverx: 扩展浏览器测试驱动
	 */
	protected ExtendWebDriver driverx = initDriverx();

	/**
	 * 初始化扩展浏览器测试驱动
	 * @return driverx 扩展浏览器测试驱动
	 */
	protected ExtendWebDriver initDriverx() {
		return new ExtendWebDriver(driver);
	}

	/**
	 * load: 页面载入执行代码
	 * @return void
	 */
	protected void load() {
		// 基类不做任何事
	};
}
