package lazyat.sys.core;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Judge {

	/**
	 * counter: 计数器
	 */
	private Integer counter = 1;

	/**
	 * logMap: 日志信息表
	 */
	private HashMap<String, HashMap<String, String>> logMap = new HashMap<String, HashMap<String, String>>();

	public HashMap<String, HashMap<String, String>> getLogMap() {
		return logMap;
	}

	/**
	 * logDeque: 日志队列
	 */
	private ArrayDeque<String> logDeque = new ArrayDeque<String>();

	public ArrayDeque<String> getLogDeque() {
		return logDeque;
	}

	/**
	 * isNull 判断为空
	 * @param obj 测试对象
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void isNull(Object obj, String message) {
		is(obj == null, message);
	}

	public void isNull(Object obj, String message, String remark) {
		is(obj == null, message, remark);
	}

	/**
	 * notNull 判断不为空
	 * @param obj 测试对象
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void notNull(Object obj, String message) {
		not(obj == null, message);
	}

	public void notNull(Object obj, String message, String remark) {
		not(obj == null, message, remark);
	}

	/**
	 * isEqual 判断字符串相同
	 * @param str1 测试字符串1
	 * @param str2 测试字符串2
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void isEqual(String str1, String str2, String message) {
		is(str1.equals(str2), message);
	}

	public void isEqual(String str1, String str2, String message, String remark) {
		is(str1.equals(str2), message, remark);
	}

	/**
	 * notEqual 判断字符串不相同
	 * @param str1 测试字符串1
	 * @param str2 测试字符串2
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void notEqual(String str1, String str2, String message) {
		not(str1.equals(str2), message);
	}

	public void notEqual(String str1, String str2, String message, String remark) {
		not(str1.equals(str2), message, remark);
	}

	/**
	 * isEqual 判断数值相同
	 * @param int1 测试数值1
	 * @param int2 测试数值2
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void isEqual(Integer int1, Integer int2, String message) {
		is(int1 == int2, message);
	}

	public void isEqual(Integer int1, Integer int2, String message, String remark) {
		is(int1 == int2, message, remark);
	}

	/**
	 * notEqual 判断数值不相同
	 * @param int1 测试数值1
	 * @param int2 测试数值2
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void notEqual(Integer int1, Integer int2, String message) {
		is(int1 != int2, message);
	}

	public void notEqual(Integer int1, Integer int2, String message, String remark) {
		is(int1 != int2, message, remark);
	}

	/**
	 * is: 判断条件成立
	 * @param bool 条件表达式
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void is(Boolean bool, String message) {
		if (bool) {
			pass(message);
		} else {
			fail(message);
		}
	}

	public void is(Boolean bool, String message, String remark) {
		if (bool) {
			pass(message, remark);
		} else {
			fail(message, remark);
		}
	}

	/**
	 * not: 判断条件不成立
	 * @param bool 条件表达式
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void not(Boolean bool, String message) {
		if (!bool) {
			pass(message);
		} else {
			fail(message);
		}
	}

	public void not(Boolean bool, String message, String remark) {
		if (!bool) {
			pass(message, remark);
		} else {
			fail(message, remark);
		}
	}

	/**
	 * pass: 测试通过
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void pass(String message) {
		log("#e5f0d3", message + " >> 测试通过");
	}

	public void pass(String message, String remark) {
		log("#e5f0d3", message + " >> 测试通过", remark);
	}

	/**
	 * fail: 测试不通过
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void fail(String message) {
		log("#f0d3d3", message + " >> 测试不通过");
	}

	public void fail(String message, String remark) {
		log("#f0d3d3", message + " >> 测试不通过", remark);
	}

	/**
	 * notice: 测试警告
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void notice(String message) {
		log("#f0ecd3", "警告: " + message);
	}

	public void notice(String message, String remark) {
		log("#f0ecd3", "警告: " + message, remark);
	}

	/**
	 * error: 测试错误
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void error(String message) {
		log("#deabab", "错误: " + message);
	}

	public void error(String message, String remark) {
		log("#deabab", "错误: " + message, remark);
	}

	/**
	 * info: 测试信息
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void info(String message) {
		log("#f6f6f6", message);
	}

	public void info(String message, String remark) {
		log("#f6f6f6", message, remark);
	}

	/**
	 * log: 记录日志
	 * @param color 标识颜色
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void log(String color, String message) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("color", color);
		map.put("message", message);
		map.put("remark", "");
		map.put("time", Server.currentTime());
		logMap.put(counter.toString(), map);
		logDeque.add(counter.toString());
		counter++;
	}

	public void log(String color, String message, String remark) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("color", color);
		map.put("message", message);
		map.put("remark", remark);
		map.put("time", Server.currentTime());
		logMap.put(counter.toString(), map);
		logDeque.add(counter.toString());
		counter++;
	}
}
