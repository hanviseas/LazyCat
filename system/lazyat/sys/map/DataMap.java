package lazyat.sys.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataMap {

	/**
	 * map: 数据映射表
	 */
	private static ConcurrentHashMap<String, HashMap<String, Object>> map = new ConcurrentHashMap<String, HashMap<String, Object>>();

	public static ConcurrentHashMap<String, HashMap<String, Object>> getMap() {
		return map;
	}

	/**
	 * regsiter: 注册数据
	 * @param group 数据分组
	 * @param key 数据索引
	 * @param value 数据值
	 * @return void
	 */
	public static synchronized void regsiter(String group, String key, Object value) {
		if (map.containsKey(group) && map.get(group).containsKey(key)) { // 已存在数据不做覆盖
			return;
		}
		HashMap<String, Object> itemMap = new HashMap<String, Object>();
		if (value.getClass().isArray()) { // 数组转存成队列
			ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<Object>();
			for (Object object : (Object[]) value) {
				queue.add(object);
			}
			itemMap.put(key, queue);
			map.put(group, itemMap);
		} else if (value instanceof String || value instanceof Number) { // 字符串和数字存储，忽略其他类型
			itemMap.put(key, value);
			map.put(group, itemMap);
		}
	}

	/**
	 * use: 使用数据
	 * @param group 数据分组
	 * @param key 数据索引
	 * @return value 数据值
	 */
	public static synchronized Object use(String group, String key) {
		if (!map.containsKey(group)) { // 数据分组不存在
			return null;
		}
		if (!map.get(group).containsKey(key)) { // 数据索引不存在
			return null;
		}
		Object value = map.get(group).get(key);
		if (value instanceof ConcurrentLinkedQueue<?>) {
			return ((ConcurrentLinkedQueue<?>) value).poll();
		}
		return value;
	}
}
