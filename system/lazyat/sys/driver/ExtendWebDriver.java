package lazyat.sys.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExtendWebDriver {

	/**
	 * ExtendWebDriver: 构建方法
	 * @param driver 测试驱动
	 */
	public ExtendWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * driver: 测试驱动
	 */
	private WebDriver driver = null;

	/**
	 * id: 根据id属性查找页面元素
	 * @param id 属性
	 * @return webElement 页面元素
	 */
	public WebElement id(String id) {
		return driver.findElement(By.id(id));
	}

	/**
	 * name: 根据name属性查找页面元素
	 * @param name 属性
	 * @return webElement 页面元素
	 */
	public WebElement name(String name) {
		return driver.findElement(By.id(name));
	}

	public List<WebElement> names(String name) {
		return driver.findElements(By.id(name));
	}

	/**
	 * tag: 根据tag属性查找页面元素
	 * @param tag 属性
	 * @return webElement 页面元素
	 */
	public WebElement tag(String tag) {
		return driver.findElement(By.tagName(tag));
	}

	public List<WebElement> tags(String tag) {
		return driver.findElements(By.tagName(tag));
	}

	/**
	 * classx: 根据class属性查找页面元素
	 * @param classx 属性
	 * @return webElement 页面元素
	 */
	public WebElement classx(String classx) {
		return driver.findElement(By.className(classx));
	}

	public List<WebElement> classes(String classx) {
		return driver.findElements(By.className(classx));
	}

	/**
	 * css: 根据css选择器查找页面元素
	 * @param selector 选择器
	 * @return webElement 页面元素
	 */
	public WebElement css(String selector) {
		return driver.findElement(By.cssSelector(selector));
	}

	public List<WebElement> csses(String selector) {
		return driver.findElements(By.cssSelector(selector));
	}

	/**
	 * xpath: 根据xpath查找页面元素
	 * @param xpath xpath
	 * @return webElement 页面元素
	 */
	public WebElement xpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public List<WebElement> xpathes(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	/**
	 * link: 根据超链接文本查找页面元素
	 * @param text 超链接文本
	 * @return webElement 页面元素
	 */
	public WebElement link(String text) {
		return driver.findElement(By.linkText(text));
	}

	public List<WebElement> links(String text) {
		return driver.findElements(By.linkText(text));
	}

	/**
	 * plink: 根据部分超链接文本查找页面元素
	 * @param text 超链接文本
	 * @return webElement 页面元素
	 */
	public WebElement plink(String text) {
		return driver.findElement(By.partialLinkText(text));
	}

	public List<WebElement> plinks(String text) {
		return driver.findElements(By.partialLinkText(text));
	}
}
