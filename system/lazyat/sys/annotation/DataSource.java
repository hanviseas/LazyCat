package lazyat.sys.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

	/**
	 * ONCE: 单次调用
	 */
	public static final int ONCE = 1;

	/**
	 * ITERATION: 迭代调用
	 */
	public static final int ITERATION = 2;

	/**
	 * SEQUENCE: 顺序调用
	 */
	public static final int SEQUENCE = 3;

	/**
	 * src: 数据源文件
	 */
	String src();

	/**
	 * field: 数据字段
	 */
	String field();

	/**
	 * method: 数据调用方式
	 */
	int method();

}