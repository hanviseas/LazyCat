package lazycat.sys.core;

import java.util.ArrayDeque;
import java.util.HashMap;

import lazycat.sys.util.DateTime;

public class Log {

	/**
	 * counter: 计数器
	 */
	private Integer counter = 1;

	/**
	 * summary: 概要统计
	 */
	private Integer[] summary = { 0, 0, 0, 0 };

	public Integer[] getSummary() {
		return summary;
	}

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
		summary[0]++;
		add("#4db376", message + " >> 测试通过");
	}

	public void pass(String message, String remark) {
		summary[0]++;
		add("#4db376", message + " >> 测试通过", remark);
	}

	/**
	 * fail: 测试不通过
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void fail(String message) {
		summary[1]++;
		add("#c43c3c", message + " >> 测试不通过");
	}

	public void fail(String message, String remark) {
		summary[1]++;
		add("#c43c3c", message + " >> 测试不通过", remark);
	}

	/**
	 * notice: 测试警告
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void notice(String message) {
		summary[2]++;
		add("#b3b34d", "警告: " + message);
	}

	public void notice(String message, String remark) {
		summary[2]++;
		add("#b3b34d", "警告: " + message, remark);
	}

	/**
	 * error: 测试错误
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void error(String message) {
		summary[3]++;
		add("#c43c3c", "错误: " + message);
	}

	public void error(String message, String remark) {
		summary[3]++;
		add("#c43c3c", "错误: " + message, remark);
	}

	/**
	 * info: 测试信息
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void info(String message) {
		add("#363636", message);
	}

	public void info(String message, String remark) {
		add("#363636", message, remark);
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
		itemMap.put("time", DateTime.currentDateTime());
		map.put(counter.toString(), itemMap);
		deque.add(counter.toString());
		counter++;
	}
}
