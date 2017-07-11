package lazycat.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

	/**
	 * currentDate: 当前日期
	 * @return date 当前日期
	 */
	public static String currentDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * currentDate: 当前日期
	 * @param format 日期格式
	 * @return date 当前日期
	 */
	public static String currentDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	/**
	 * currentDateTime: 当前日期时间
	 * @return datetime 当前日期时间
	 */
	public static String currentDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * currentDateTime: 当前日期时间
	 * @param format 日期时间格式
	 * @return datetime 当前日期时间
	 */
	public static String currentDateTime(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
}
