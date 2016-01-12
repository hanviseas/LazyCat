package lazyat.sys.core;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lazyat.sys.map.BrowserMap;
import lazyat.sys.map.CaseMap;

public class Commander {

	/**
	 * name: 指挥官名
	 */
	private String name = Server.generateId();

	public String getNamex() {
		return name;
	}

	/**
	 * threadMap: 线程映射表
	 */
	private ConcurrentHashMap<Long, Browser> threadMap = new ConcurrentHashMap<Long, Browser>();

	public ConcurrentHashMap<Long, Browser> getThreadMap() {
		return threadMap;
	}

	/**
	 * judgeMap: 审判映射表
	 */
	private ConcurrentHashMap<String, Judge> judgeMap = new ConcurrentHashMap<String, Judge>();

	public ConcurrentHashMap<String, Judge> getJudgeMap() {
		return judgeMap;
	}

	/**
	 * browserQueue: 浏览器队列
	 */
	private ConcurrentLinkedQueue<String> browserQueue = initBrowserQueue();

	public ConcurrentLinkedQueue<String> getBrowserQueue() {
		return browserQueue;
	}

	/**
	 * initBrowserQueue: 初始化浏览器队列
	 * @return browserQueue 浏览器队列
	 */
	private ConcurrentLinkedQueue<String> initBrowserQueue() {
		ConcurrentLinkedQueue<String> browserQueue = new ConcurrentLinkedQueue<String>();
		browserQueue.addAll(BrowserMap.getMap().keySet());
		return browserQueue;
	}

	/**
	 * caseQueue: 用例队列
	 */
	private ConcurrentLinkedQueue<String> caseQueue = initCaseQueue();

	public ConcurrentLinkedQueue<String> getCaseQueue() {
		return caseQueue;
	}

	/**
	 * initCaseQueue: 初始化用例队列
	 * @return caseQueue 用例队列
	 */
	private ConcurrentLinkedQueue<String> initCaseQueue() {
		ConcurrentLinkedQueue<String> caseQueue = new ConcurrentLinkedQueue<String>();
		caseQueue.addAll(CaseMap.getMap().keySet());
		return caseQueue;
	}

	/**
	 * dispatch: 任务调度
	 * @return void
	 */
	public void dispatch() {
		Iterator<String> browserIterator = browserQueue.iterator();
		if (Server.getRunMode().equals("remote") || Server.getRunMode().equals("multiple")) {
			ExecutorService threadPool = Executors.newCachedThreadPool(); // 并发测试线程池
			while (browserIterator.hasNext()) {
				String browserKey = browserIterator.next().toString();
				Browser browser = new Browser().initial(browserKey);
				threadPool.execute(browser); // 远程测试，并发启动多浏览器
			}
			try { // 等待所有线程结束
				threadPool.shutdown();
				threadPool.awaitTermination(24, TimeUnit.HOURS);
			} catch (Exception e) { // 线程错误
				System.out.println(e);
			}
			Report.save();
		} else if (Server.getRunMode().equals("local")) {
			while (browserIterator.hasNext()) {
				String browserKey = browserIterator.next().toString();
				Browser browser = new Browser().initial(browserKey);
				browser.launch(); // 本地测试，按顺序启动多浏览器
			}
			Report.save();
		} else {
			// 模式未误别
		}
	}
}
