package lazyat.sys.driver;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobileDriver {

	public MobileDriver() { // 待测试阶段代码，暂不正式纳入使用

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "iPhone 6 SIM");
		capabilities.setCapability("platformVersion", "9.1");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("browserName", "safari");

		try {
			WebDriver wd = new RemoteWebDriver(new URL("http://192.168.2.68:4723/wd/hub"), capabilities);
			wd.get("http://www.baidu.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
