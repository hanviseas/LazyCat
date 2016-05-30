package lazyat.sys.core;

import java.lang.reflect.Method;
import java.util.Iterator;

import lazyat.sys.annotation.DataSource;
import lazyat.sys.driver.PageDriver;
import lazyat.sys.map.BrowserMap;
import lazyat.sys.map.CaseMap;
import lazyat.sys.map.DataMap;

import org.openqa.selenium.WebDriver;

public class Browser extends Thread {

	/**
	 * name: 浏览器名
	 */
	private String name = "";

	public String getNamex() {
		return name;
	}

	/**
	 * version: 浏览器版本号
	 */
	private String version = "";

	public String getVersion() {
		return version;
	}

	/**
	 * platform: 浏览器平台
	 */
	private String platform = "";

	public String getPlatform() {
		return platform;
	}

	/**
	 * driver: 测试驱动
	 */
	private WebDriver driver = null;

	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * log: 测试日志
	 */
	private Log log = new Log();

	public Log getLog() {
		return log;
	}

	/**
	 * pageLoadTimeout: 页面超时时间（秒）
	 */
	private Integer pageLoadTimeout = 30;

	public Integer getPageLoadTimeout() {
		return pageLoadTimeout;
	}

	/**
	 * initPageLoadTimeout: 初始化页面超时时间
	 * @param key 浏览器配置索引
	 * @return pageLoadTimeout 页面超时时间
	 */
	private Integer initPageLoadTimeout(String key) {
		Integer pageLoadTimeout = Server.getPageLoadTimeout();
		try { // 读取配置错误或无配置时，使用全局配置
			String pageLoadValue = BrowserMap.getMap().get(key).get("pageLoadTimeout"); // 覆盖全局配置
			pageLoadTimeout = (!pageLoadValue.equals("")) ? Integer.parseInt(pageLoadValue) : Server.getPageLoadTimeout();
		} catch (Exception e) { // 配置设置错误
			e.printStackTrace();
		}
		return pageLoadTimeout;
	}

	/**
	 * scriptTimeout: 脚本超时时间（秒）
	 */
	private Integer scriptTimeout = 30;

	public Integer getScriptTimeout() {
		return scriptTimeout;
	}

	/**
	 * initScriptTimeout: 初始化脚本超时时间
	 * @param key 浏览器配置索引
	 * @return scriptTimeout 脚本超时时间
	 */
	private Integer initScriptTimeout(String key) {
		Integer scriptTimeout = Server.getScriptTimeout();
		try { // 读取配置错误或无配置时，使用全局配置
			String scriptValue = BrowserMap.getMap().get(key).get("scriptTimeout"); // 覆盖全局配置
			scriptTimeout = (!scriptValue.equals("")) ? Integer.parseInt(scriptValue) : Server.getScriptTimeout();
		} catch (Exception e) { // 配置设置错误
			e.printStackTrace();
		}
		return scriptTimeout;
	}

	/**
	 * implicitlyWait: 等待超时时间（秒）
	 */
	private Integer implicitlyWait = 10;

	public Integer getImplicitlyWait() {
		return implicitlyWait;
	}

	/**
	 * initImplicitlyWait: 初始化等待超时时间
	 * @param key 浏览器配置索引
	 * @return implicitlyWait 等待超时时间
	 */
	private Integer initImplicitlyWait(String key) {
		Integer implicitlyWait = Server.getImplicitlyWait();
		try { // 读取配置错误或无配置时，使用全局配置
			String implicitnessValue = BrowserMap.getMap().get(key).get("implicitlyWait"); // 覆盖全局配置
			implicitlyWait = (!implicitnessValue.equals("")) ? Integer.parseInt(implicitnessValue) : Server.getImplicitlyWait();
		} catch (Exception e) { // 配置设置错误
			e.printStackTrace();
		}
		return implicitlyWait;
	}

	/**
	 * initial: 初始化浏览器信息
	 * @param key 浏览器配置索引
	 * @return browser 对象实例
	 */
	public Browser initial(String key) {
		name = BrowserMap.getMap().get(key).get("name"); // 根据索引获取浏览器名、版本号、系统平台配置
		version = BrowserMap.getMap().get(key).get("version");
		platform = BrowserMap.getMap().get(key).get("platform");
		pageLoadTimeout = initPageLoadTimeout(key);
		scriptTimeout = initScriptTimeout(key);
		implicitlyWait = initImplicitlyWait(key);
		return this;
	}

	/**
	 * launch: 本地浏览器顺序测试
	 * @return void
	 */
	public void launch() {
		log.info("浏览器: " + name + "-" + version + " >> 启动");
		Server.getCommander().getThreadMap().put(Thread.currentThread().getId(), this);
		Server.getCommander().getLogMap().put(name + " [ Version:" + version + " Platform:" + platform + " ]", log);
		driver = new PageDriver(name).getDriver();
		if (driver == null) { // 驱动初始化失败
			log.error("浏览器" + name + "-" + version + "启动失败");
			return;
		}
		Iterator<String> caseIterator = CaseMap.getMap().keySet().iterator();
		while (caseIterator.hasNext()) { // 顺序执行测试用例
			callCase(caseIterator.next());
		}
		driver.close();
		driver.quit();
		log.info("浏览器: " + name + "-" + version + " >> 关闭");
	}

	/**
	 * run: 远程浏览器并发测试
	 * @return void
	 */
	public void run() {
		log.info("浏览器: " + name + "-" + version + " >> 启动");
		Server.getCommander().getThreadMap().put(Thread.currentThread().getId(), this);
		Server.getCommander().getLogMap().put(name + " [ Version:" + version + " Platform:" + platform + " ]", log);
		driver = new PageDriver(name, version, platform, Server.getRemoteHost()).getDriver();
		if (driver == null) { // 驱动初始化失败
			log.error("浏览器" + name + "-" + version + "启动失败");
			return;
		}
		if (Server.getRunMode().equals("remote")) { // 独立执行全部测试用例
			Iterator<String> caseIterator = Server.getCommander().getCaseQueue().iterator();
			while (caseIterator.hasNext()) {
				callCase(caseIterator.next());
			}
		} else if (Server.getRunMode().equals("multiple")) { // 共同分配执行全部用例
			while (!Server.getCommander().getCaseQueue().isEmpty()) {
				callCase(Server.getCommander().getCaseQueue().poll());
			}
		}
		driver.close();
		driver.quit();
		log.info("浏览器: " + name + "-" + version + " >> 关闭");
	}

	/**
	 * callCase: 调用用例
	 * @param key 用例索引
	 * @return void
	 */
	private void callCase(String key) {
		String caseSrc = CaseMap.getMap().get(key).get("src");
		try { // 通过反射实例化用例
			Case testCase = (Case) Class.forName("lazyat.tc." + caseSrc).newInstance();
			testCase.initial(key).launch();
			log.info("测试用例: " + testCase.getNamex() + " >> 执行开始");
			testCase.before(); // 可重写的前置方法
			for (Method method : testCase.getClass().getDeclaredMethods()) { // 遍历所有类方法
				if (method.getName().matches("^test.+")) { // 只运行以test开头的方法
					callMethod(testCase, method);
				}
			}
			testCase.after(); // 可重写的后置方法
			log.info("测试用例: " + testCase.getNamex() + " >> 执行结束");
		} catch (Exception e) { // Case文件初始化错误
			log.error("用例" + caseSrc + "调用失败");
			e.printStackTrace();
		}
	}

	/**
	 * callMethod: 调用方法
	 * @param tc 用例实例
	 * @param method 方法实例
	 * @return void
	 */
	private void callMethod(Case tc, Method method) {
		if (method.isAnnotationPresent(DataSource.class)) { // 已声明数据源
			DataSource dataSource = (DataSource) method.getAnnotation(DataSource.class);
			Object[][] params = require(dataSource.src(), dataSource.field()); // 限定使用二维数组
			if (dataSource.method() == DataSource.ONCE) { // 数据单次调用
				invoke(tc, method, params[0]);
			} else if (dataSource.method() == DataSource.ITERATION) { // 数据迭代调用
				for (Object[] param : params) {
					invoke(tc, method, param);
				}
			} else if (dataSource.method() == DataSource.SEQUENCE) { // 数据顺序调用
				DataMap.regsiter(dataSource.src(), dataSource.field(), params);
				invoke(tc, method, (Object[]) DataMap.use(dataSource.src(), dataSource.field()));
			}
		} else {
			invoke(tc, method, new Object[] {}); // 未声明数据源则空参数调用
		}
	}

	/**
	 * require: 引用数据
	 * @param src 数据源
	 * @param field 数据字段
	 * @return array 数据数组
	 */
	private Object[][] require(String src, String field) {
		try { // 通过反射实例化数据
			Data testData = (Data) Class.forName("lazyat.data." + src).newInstance();
			return (Object[][]) testData.getClass().getField(field).get(testData);
		} catch (Exception e) { // Data文件初始化错误
			log.error("数据" + src + "@" + field + "调用失败");
			e.printStackTrace();
			return new Object[][] {};
		}
	}

	/**
	 * invoke: 调用方法
	 * @param tc 用例实例
	 * @param method 方法实例
	 * @param param 调用参数
	 * @return void
	 */
	private void invoke(Case tc, Method method, Object[] param) {
		try { // 通过反射调用方法
			method.invoke(tc, param);
		} catch (Exception e) { // 方法调用初始化错误
			log.error("方法" + method.getName() + "调用失败");
			e.printStackTrace();
		}
	}
}
