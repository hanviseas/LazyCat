package lazycat.sys.core;

import java.util.ArrayList;
import java.util.List;

import lazycat.sys.driver.ExtendWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	 * actions: 动作
	 */
	protected Actions actions = new Actions(driver);

	/**
	 * log: 测试日志
	 */
	protected Log log = browser.getLog();

	/**
	 * timeout: 等待超时时间（秒）
	 */
	protected int timeout = 30;

	/**
	 * load: 页面载入执行代码
	 * @return void
	 */
	protected void load() {
		// 基类不做任何事
	};

	/**
	 * switchWindow: 切换窗口
	 * @param index 窗口索引
	 * @return handle 窗口句柄
	 */
	public String switchWindow(int index) {
		try {
			List<String> windowList = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(windowList.get(index));
			return driver.getWindowHandle();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * closeWindow: 关闭窗口
	 * @return void
	 */
	public void closeWindow() {
		driver.close();
	}

	/**
	 * refresh: 刷新面面
	 * @return void
	 */
	public void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * clearCookies: 清除所有Cookie
	 * @return void
	 */
	public void clearCookies() {
		driver.manage().deleteAllCookies();
	}

	/**
	 * getTitle: 获取页面标题
	 * @return title 页面标题
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * getUrl: 获取窗口地址
	 * @return url 窗口地址
	 */
	public String getUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * isDisplayed: 判断元素是否显示
	 * 
	 * @param element 页面元素
	 * @return displayed
	 */
	protected boolean isDisplayed(WebElement element) {
		return element.isDisplayed() ? true : false;
	}

	/**
	 * notDisplayed: 判断元素是否不显示
	 * 
	 * @param element 页面元素
	 * @return displayed
	 */
	protected boolean notDisplayed(WebElement element) {
		return element.isDisplayed() ? false : true;
	}

	/**
	 * isExists: 判断元素是否存在
	 * 
	 * @param element 页面元素
	 * @return exists
	 */
	protected boolean isExists(WebElement element) {
		try {
			element.getTagName();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * notExists: 判断元素是否不存在
	 * 
	 * @param element 页面元素
	 * @return exists
	 */
	protected boolean notExists(WebElement element) {
		try {
			element.getTagName();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * waitForTime: 等待指定时间（毫秒）
	 * @param time 时间长度
	 * @return wait
	 */
	protected boolean waitForTime(long time) {
		try {
			Thread.sleep(time);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForVisibility: 等待元素可见
	 * @param element 页面元素
	 * @return visibility
	 */
	protected boolean waitForVisibility(WebElement element) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForVisibility: 等待元素可见
	 * @param locator 页面元素定位
	 * @return visibility
	 */
	protected boolean waitForVisibility(By locator) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForInvisibility: 等待元素不可见
	 * @param element 页面元素
	 * @return visibility
	 */
	protected boolean waitForInvisibility(WebElement element) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForInvisibility: 等待元素不可见
	 * @param locator 页面元素定位
	 * @return visibility
	 */
	protected boolean waitForInvisibility(By locator) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForClickable: 等待元素可点击
	 * @param element 页面元素
	 * @return clickable
	 */
	protected boolean waitForClickable(WebElement element) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForClickable: 等待元素可点击
	 * @param locator 页面元素定位
	 * @return clickable
	 */
	protected boolean waitForClickable(By locator) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForPresent: 等待元素出现
	 * @param locator 页面元素定位
	 * @return present
	 */
	protected boolean waitForPresent(By locator) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForStaleness: 等待元素消除
	 * @param element 页面元素
	 * @return staleness
	 */
	protected boolean waitForStaleness(WebElement element) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.stalenessOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForAlertPresent: 等待对话框出现
	 * @return present
	 */
	protected boolean waitForAlertPresent() {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * waitForTitleContains: 等待标题包含文标
	 * @param title 标题文本
	 * @return contains
	 */
	protected boolean waitForTitleContains(String title) {
		try {
			waitForTime(1000); // 容错等待
			new WebDriverWait(driver, timeout).until(ExpectedConditions.titleContains(title));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
