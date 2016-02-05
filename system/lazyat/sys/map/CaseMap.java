package lazyat.sys.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import lazyat.sys.core.Config;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class CaseMap {

	/**
	 * map: 用例配置表
	 */
	private static ConcurrentHashMap<String, HashMap<String, String>> map = initMap();

	public static ConcurrentHashMap<String, HashMap<String, String>> getMap() {
		return map;
	}

	/**
	 * initMap: 初始化用例配置表
	 * @return map 用例配置表
	 */
	private static ConcurrentHashMap<String, HashMap<String, String>> initMap() {
		NodeList caseList = new Config("config/case.xml").readTags("case");
		ConcurrentHashMap<String, HashMap<String, String>> caseMap = new ConcurrentHashMap<String, HashMap<String, String>>();
		for (int i = 0; i < caseList.getLength(); i++) { // 获取所有用例的全部配置信息
			NamedNodeMap caseAttrs = caseList.item(i).getAttributes();
			HashMap<String, String> attrMap = new HashMap<String, String>();
			attrMap.put("name", Config.getNodeString(caseAttrs.getNamedItem("name")));
			attrMap.put("description", Config.getNodeString(caseAttrs.getNamedItem("description")));
			attrMap.put("script", Config.getNodeString(caseAttrs.getNamedItem("script")));
			attrMap.put("pageLoadTimeout", Config.getNodeString(caseAttrs.getNamedItem("pageLoadTimeout")));
			attrMap.put("scriptTimeout", Config.getNodeString(caseAttrs.getNamedItem("scriptTimeout")));
			attrMap.put("implicitlyWait", Config.getNodeString(caseAttrs.getNamedItem("implicitlyWait")));
			caseMap.put("Case-" + i, attrMap);
		}
		return caseMap;
	}
}
