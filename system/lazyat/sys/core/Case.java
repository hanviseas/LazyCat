package lazyat.sys.core;

import java.util.concurrent.TimeUnit;

import lazyat.sys.driver.ExtendWebDriver;
import lazyat.sys.map.CaseMap;

import org.openqa.selenium.WebDriver;

public abstract class Case {

	/**
	 * key: 用例配置索引
	 */
	protected String key = "";

	/**
	 * name: 用例名
	 */
	protected String name = "";

	/**
	 * description: 用例描述
	 */
	protected String description = "";

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
	 * judge: 浏览器测试审判
	 */
	protected Judge judge = initJudge();

	/**
	 * 初始化浏览器测试审判
	 * @return judge 浏览器测试审判
	 */
	protected Judge initJudge() {
		return browser.getJudge();
	}

	/**
	 * pageLoadTimeout: 页面超时时间（秒）
	 */
	protected Integer pageLoadTimeout = 30;

	/**
	 * initPageLoadTimeout: 初始化页面超时时间
	 * @return pageLoadTimeout 页面超时时间
	 */
	protected Integer initPageLoadTimeout() {
		Integer pageLoadTimeout = browser.getPageLoadTimeout();
		try { // 读取配置错误或无配置时，使用浏览器级别配置
			String pageLoadValue = CaseMap.getMap().get(key).get("pageLoadTimeout"); // 覆盖浏览器级别配置
			pageLoadTimeout = (!pageLoadValue.equals("")) ? Integer.parseInt(pageLoadValue) : browser.getPageLoadTimeout();
		} catch (Exception e) { // 配置设置错误
			System.out.println(e);
		}
		return pageLoadTimeout;
	}

	/**
	 * scriptTimeout: 脚本超时时间（秒）
	 */
	protected Integer scriptTimeout = 30;

	/**
	 * initScriptTimeout: 初始化脚本超时时间
	 * @return scriptTimeout 脚本超时时间
	 */
	protected Integer initScriptTimeout() {
		Integer scriptTimeout = browser.getScriptTimeout();
		try { // 读取配置错误或无配置时，使用浏览器级别配置
			String scriptValue = CaseMap.getMap().get(key).get("scriptTimeout"); // 覆盖浏览器级别配置
			scriptTimeout = (!scriptValue.equals("")) ? Integer.parseInt(scriptValue) : browser.getScriptTimeout();
		} catch (Exception e) { // 配置设置错误
			System.out.println(e);
		}
		return scriptTimeout;
	}

	/**
	 * implicitlyWait: 等待超时时间（秒）
	 */
	protected Integer implicitlyWait = 10;

	/**
	 * initImplicitlyWait: 初始化等待超时时间
	 * @return implicitlyWait 等待超时时间
	 */
	protected Integer initImplicitlyWait() {
		Integer implicitlyWait = browser.getImplicitlyWait();
		try { // 读取配置错误或无配置时，使用浏览器级别配置
			String implicitnessValue = CaseMap.getMap().get(key).get("implicitlyWait"); // 覆盖浏览器级别配置
			implicitlyWait = (!implicitnessValue.equals("")) ? Integer.parseInt(implicitnessValue) : browser.getImplicitlyWait();
		} catch (Exception e) { // 配置设置错误
			System.out.println(e);
		}
		return implicitlyWait;
	}

	/**
	 * initial: 初始化用例信息
	 * @param key 用例配置索引
	 * @param description 用例描述
	 * @return case 对象实例
	 */
	public Case initial(String key) {
		this.key = key;
		name = CaseMap.getMap().get(key).get("name").toString(); // 根据索引获取用例名，用例描述
		description = CaseMap.getMap().get(key).get("description").toString();
		pageLoadTimeout = initPageLoadTimeout();
		scriptTimeout = initScriptTimeout();
		implicitlyWait = initImplicitlyWait();
		return this;
	}

	/**
	 * launch: 用例测试执行
	 * @return void
	 */
	public void launch() {
		judge.info("测试用例: " + name + " >> 开始执行");
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		execute(); // 用例需要继承Case类并实现execute方法
		judge.info("测试用例: " + name + " >> 结束");
	}

	/**
	 * execute: 用例主体执行代码
	 * @return void
	 */
	protected abstract void execute();
}
