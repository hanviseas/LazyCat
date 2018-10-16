package lazycat.sys.core;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import lazycat.sys.map.BrowserMap;
import lazycat.sys.map.CaseMap;

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
    private HashMap<Long, Browser> threadMap = new HashMap<Long, Browser>();

    public HashMap<Long, Browser> getThreadMap() {
        return threadMap;
    }

    /**
     * logMap: 日志映射表
     */
    private HashMap<String, Log> logMap = new HashMap<String, Log>();

    public HashMap<String, Log> getLogMap() {
        return logMap;
    }

    /**
     * browserQueue: 浏览器队列
     */
    private PriorityBlockingQueue<String> browserQueue = initBrowserQueue();

    public PriorityBlockingQueue<String> getBrowserQueue() {
        return browserQueue;
    }

    /**
     * initBrowserQueue: 初始化浏览器队列
     *
     * @return browserQueue 浏览器队列
     */
    public PriorityBlockingQueue<String> initBrowserQueue() {
        PriorityBlockingQueue<String> browserQueue = new PriorityBlockingQueue<String>();
        browserQueue.addAll(BrowserMap.getMap().keySet()); // keySet不保证顺序，因此需要使用优先队列
        return browserQueue;
    }

    /**
     * caseQueue: 用例队列
     */
    private PriorityBlockingQueue<String> caseQueue = initCaseQueue();

    public PriorityBlockingQueue<String> getCaseQueue() {
        return caseQueue;
    }

    /**
     * initCaseQueue: 初始化用例队列
     *
     * @return caseQueue 用例队列
     */
    public PriorityBlockingQueue<String> initCaseQueue() {
        PriorityBlockingQueue<String> caseQueue = new PriorityBlockingQueue<String>();
        caseQueue.addAll(CaseMap.getMap().keySet()); // keySet不保证顺序，因此需要使用优先队列
        return caseQueue;
    }

    /**
     * dispatch: 任务调度
     *
     * @return void
     */
    public void dispatch() {
        if (Server.getRunMode().equals("remote") || Server.getRunMode().equals("multiple")) {
            ExecutorService threadPool = Executors.newCachedThreadPool(); // 并发测试线程池
            while (!browserQueue.isEmpty()) {
                Browser browser = new Browser().initial(browserQueue.poll());
                threadPool.execute(browser); // 远程测试，并发启动多浏览器
            }
            try { // 等待所有线程结束
                threadPool.shutdown();
                threadPool.awaitTermination(24, TimeUnit.HOURS);
            } catch (Exception e) { // 线程错误
                e.printStackTrace();
            }
            Report.save();
        } else if (Server.getRunMode().equals("local")) {
            while (!browserQueue.isEmpty()) {
                Browser browser = new Browser().initial(browserQueue.poll());
                browser.launch(); // 本地测试，按顺序启动多浏览器
            }
            Report.save();
        } else {
            // 模式未误别
        }
    }
}