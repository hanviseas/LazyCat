package lazyat.page.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lazyat.sys.core.Page;

public class Demo extends Page {

	@FindBy(id = "kw")
	private WebElement keyword;

	@FindBy(id = "su")
	private WebElement submit;

	protected void load() {
		driver.get("http://www.baidu.com");
	}

	public String getButtonText() {
		return submit.getAttribute("value");
	}

	public void search() {
		keyword.sendKeys("软件测试");
		submit.click();
	}
}
