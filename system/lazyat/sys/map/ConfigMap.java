package lazyat.sys.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import lazyat.sys.core.Config;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigMap {

	/**
	 * map: 基础配置表
	 */
	private static ConcurrentHashMap<String, HashMap<String, String>> map = initMap();

	public static ConcurrentHashMap<String, HashMap<String, String>> getMap() {
		return map;
	}

	/**
	 * initMap: 初始化基础配置表
	 * @return map 基础配置表
	 */
	private static ConcurrentHashMap<String, HashMap<String, String>> initMap() {
		NodeList configList = new Config("config/base.xml").readTags("config");
		ConcurrentHashMap<String, HashMap<String, String>> configMap = new ConcurrentHashMap<String, HashMap<String, String>>();
		for (int i = 0; i < configList.getLength(); i++) { // 获取所有配置组的全部配置信息
			NodeList propertyList = configList.item(i).getChildNodes();
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			String groupName = Config.getNodeString(configList.item(i).getAttributes().getNamedItem("group"));
			if (groupName.equals("")) {
				// 配置组名错误
				continue;
			}
			for (int j = 0; j < propertyList.getLength(); j++) { // 获取所有配置节点的值
				if (propertyList.item(j).getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				String propertyName = propertyList.item(j).getNodeName().toString();
				String propertyValue = propertyList.item(j).getFirstChild().getNodeValue().toString();
				propertyMap.put(propertyName, propertyValue);
			}
			configMap.put(groupName, propertyMap);
		}
		return configMap;
	}
}
