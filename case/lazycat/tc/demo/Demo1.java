package lazycat.tc.demo;

import lazycat.sys.annotation.DataSource;
import lazycat.sys.core.Case;

public class Demo1 extends Case {

	protected void before() {
		log.info("用例准备");
	}

	@DataSource(src = "keyword.Search", field = "baidu", method = DataSource.ITERATION)
	public void testDemo(String keyword) {
		lazycat.page.baidu.Home baidu = new lazycat.page.baidu.Home();
		log.info("打开百度首页", screenshot());
		baidu.input(keyword);
		baidu.search();
		judge.isMatch(baidu.getNumber(), "[\\s\\S]*百度为您找到相关结果约([\\d]+,)*[\\d]+个", "测试搜索结果统计");
	}

	protected void after() {
		log.info("用例收尾");
	}
}
