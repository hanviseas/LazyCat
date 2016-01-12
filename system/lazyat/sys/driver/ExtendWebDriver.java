package lazyat.sys.driver;

import java.io.File;

import lazyat.sys.core.Server;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExtendWebDriver {

	/**
	 * driver: 浏览器测试驱动
	 */
	private WebDriver driver = null;

	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * ExtendWebDriver: 构建方法
	 * @param driver 浏览器测试驱动
	 */
	public ExtendWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * screenshot: 屏幕快照
	 * @return void
	 */
	public String screenshot() {
		String path = Server.getCommander().getNamex() + "/" + Server.generateId() + ".png";
		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File target = new File("report/" + path); // 快照图片存放于report/{测试标识号}/目录下
			FileUtils.copyFile(source, target);
		} catch (Exception e) { // 生成错误
			System.out.println(e);
		}
		return path;
	}

	/**
	 * id: 根询id属性查找页面元素
	 * @param id id属性
	 * @return webelement 页面元素
	 */
	public WebElement id(String id) {
		return driver.findElement(By.id(id));
	}

	/**
	 * name: 根据name属性查找页面元素
	 * @param name 属性
	 * @return webelement 页面元素
	 */
	public WebElement name(String name) {
		return driver.findElement(By.id(name));
	}

	/**
	 * tag: 根据tag属性查找页面元素
	 * @param tag 属性
	 * @return webelement 页面元素
	 */
	public WebElement tag(String tag) {
		return driver.findElement(By.tagName(tag));
	}

	/**
	 * classx: 根据class属性查找页面元素
	 * @param classx 属性
	 * @return webelement 页面元素
	 */
	public WebElement classx(String classx) {
		return driver.findElement(By.className(classx));
	}

	/**
	 * css: 根据css选择器查找页面元素
	 * @param selector 选择器
	 * @return webelement 页面元素
	 */
	public WebElement css(String selector) {
		return driver.findElement(By.cssSelector(selector));
	}

	/**
	 * xpath: 根据xpath查找页面元素
	 * @param xpath xpath
	 * @return webelement 页面元素
	 */
	public WebElement xpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	/**
	 * link: 根据超链接文本查找页面元素
	 * @param text 超链接文本
	 * @return webelement 页面元素
	 */
	public WebElement link(String text) {
		return driver.findElement(By.linkText(text));
	}

	/**
	 * plink: 根据部分超链接文本查找页面元素
	 * @param text 超链接文本
	 * @return webelement 页面元素
	 */
	public WebElement plink(String text) {
		return driver.findElement(By.partialLinkText(text));
	}
}
