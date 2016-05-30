package lazyat.sys.core;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Log {

	/**
	 * counter: 计数器
	 */
	private Integer counter = 1;

	/**
	 * map: 日志信息表
	 */
	private HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();

	public HashMap<String, HashMap<String, String>> getMap() {
		return map;
	}

	/**
	 * deque: 日志队列
	 */
	private ArrayDeque<String> deque = new ArrayDeque<String>();

	public ArrayDeque<String> getDeque() {
		return deque;
	}

	/**
	 * pass: 测试通过
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void pass(String message) {
		add("#e5f0d3", message + " >> 测试通过");
	}

	public void pass(String message, String remark) {
		add("#e5f0d3", message + " >> 测试通过", remark);
	}

	/**
	 * fail: 测试不通过
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void fail(String message) {
		add("#f0d3d3", message + " >> 测试不通过");
	}

	public void fail(String message, String remark) {
		add("#f0d3d3", message + " >> 测试不通过", remark);
	}

	/**
	 * notice: 测试警告
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void notice(String message) {
		add("#f0ecd3", "警告: " + message);
	}

	public void notice(String message, String remark) {
		add("#f0ecd3", "警告: " + message, remark);
	}

	/**
	 * error: 测试错误
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void error(String message) {
		add("#deabab", "错误: " + message);
	}

	public void error(String message, String remark) {
		add("#deabab", "错误: " + message, remark);
	}

	/**
	 * info: 测试信息
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void info(String message) {
		add("#f6f6f6", message);
	}

	public void info(String message, String remark) {
		add("#f6f6f6", message, remark);
	}

	/**
	 * add: 添加日志
	 * @param color 标识颜色
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void add(String color, String message) {
		add(color, message, "");
	}

	public void add(String color, String message, String remark) {
		HashMap<String, String> itemMap = new HashMap<String, String>();
		itemMap.put("color", color);
		itemMap.put("message", message);
		itemMap.put("remark", remark);
		itemMap.put("time", Server.currentTime());
		map.put(counter.toString(), itemMap);
		deque.add(counter.toString());
		counter++;
	}
}
