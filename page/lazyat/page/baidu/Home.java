package lazyat.page.baidu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lazyat.sys.core.Page;

public class Home extends Page {

	/**
	 * keywordField: 搜索关键字输入框
	 */
	@FindBy(id = "kw")
	private WebElement keywordField;

	/**
	 * submitButton: 搜索按钮
	 */
	@FindBy(id = "su")
	private WebElement submitButton;

	/**
	 * numberText: 搜索结果文本
	 */
	@FindBy(className = "nums")
	private WebElement numberText;

	/**
	 * load: 载入百度首页
	 * @return void
	 */
	protected void load() {
		driver.get("http://www.baidu.com");
	}

	/**
	 * input: 在搜索框中输入关键字
	 * @param keyword 关键字
	 * @return void
	 */
	public void input(String keyword) {
		log.info("搜索关键字:" + keyword);
		keywordField.clear();
		keywordField.sendKeys(keyword);
	}

	/**
	 * search: 提交搜索
	 * @return void
	 */
	public void search() {
		submitButton.click();
	}

	/**
	 * getNumber: 获取搜索结果统计
	 * @return number 搜索结果统计
	 */
	public String getNumber() {
		return numberText.getText();
	}
}
