package lazyat.sys.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import lazyat.sys.core.Config;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
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
		ConcurrentHashMap<String, HashMap<String, String>> caseMap = new ConcurrentHashMap<String, HashMap<String, String>>();
		NodeList suitList = new Config("config/case.xml").readTags("suit");
		for (int i = 0; i < suitList.getLength(); i++) { // 获取所有用例集
			if (Config.getNodeAttrValue(suitList.item(i), "enable").equals("off")) { // 用例集停用
				continue;
			}
			NodeList caseList = suitList.item(i).getChildNodes();
			for (int j = 0; j < caseList.getLength(); j++) { // 获取所有用例的全部配置信息
				if (caseList.item(j).getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				NamedNodeMap caseAttrs = caseList.item(j).getAttributes();
				if (Config.getNodeValue(caseAttrs.getNamedItem("enable")).equals("off")) { // 用例停用
					continue;
				}
				HashMap<String, String> attrMap = new HashMap<String, String>();
				attrMap.put("name", Config.getNodeValue(caseAttrs.getNamedItem("name")));
				attrMap.put("description", Config.getNodeValue(caseAttrs.getNamedItem("description")));
				attrMap.put("src", Config.getNodeValue(caseAttrs.getNamedItem("src")));
				attrMap.put("pageLoadTimeout", Config.getNodeValue(caseAttrs.getNamedItem("pageLoadTimeout")));
				attrMap.put("scriptTimeout", Config.getNodeValue(caseAttrs.getNamedItem("scriptTimeout")));
				attrMap.put("implicitlyWait", Config.getNodeValue(caseAttrs.getNamedItem("implicitlyWait")));
				caseMap.put("Case-" + i + "-" + j, attrMap);
			}
		}
		return caseMap;
	}
}
