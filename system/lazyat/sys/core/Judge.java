package lazyat.sys.core;

public class Judge {

	/**
	 * Judge: 构建方法
	 * @param log 测试日志
	 */
	public Judge(Log log) {
		this.log = log;
	}

	/**
	 * log: 测试日志
	 */
	private Log log = null;

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
	 * isMatch 判断字符串匹配正则
	 * @param str 测试字符串
	 * @param patten 正则表达式
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void isMatch(String str, String pattern, String message) {
		is(str.matches(pattern), message);
	}

	public void isMatch(String str, String pattern, String message, String remark) {
		is(str.matches(pattern), message, remark);
	}

	/**
	 * notMatch 判断字符串不匹配正则
	 * @param str 测试字符串
	 * @param patten 正则表达式
	 * @param message 日志信息
	 * @param remark 备注
	 * @return void
	 */
	public void notMatch(String str, String pattern, String message) {
		not(str.matches(pattern), message);
	}

	public void notMatch(String str, String pattern, String message, String remark) {
		not(str.matches(pattern), message, remark);
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
			log.pass(message);
		} else {
			log.fail(message);
		}
	}

	public void is(Boolean bool, String message, String remark) {
		if (bool) {
			log.pass(message, remark);
		} else {
			log.fail(message, remark);
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
			log.pass(message);
		} else {
			log.fail(message);
		}
	}

	public void not(Boolean bool, String message, String remark) {
		if (!bool) {
			log.pass(message, remark);
		} else {
			log.fail(message, remark);
		}
	}
}
