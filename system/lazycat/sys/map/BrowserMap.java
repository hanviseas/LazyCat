package lazycat.sys.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import lazycat.sys.core.Config;
import lazycat.sys.core.Server;

public class BrowserMap {

    /**
     * map: 浏览器配置表
     */
    private static ConcurrentHashMap<String, HashMap<String, String>> map = initMap();

    public static ConcurrentHashMap<String, HashMap<String, String>> getMap() {
        return map;
    }

    /**
     * initMap: 初始化浏览器配置表
     *
     * @return map 浏览器配置表
     */
    private static ConcurrentHashMap<String, HashMap<String, String>> initMap() {
        NodeList browserList = new Config(Server.getCurrentPath() + "/config/browser.xml").readTags("browser");
        ConcurrentHashMap<String, HashMap<String, String>> browserMap = new ConcurrentHashMap<String, HashMap<String, String>>();
        for (int i = 0; i < browserList.getLength(); i++) { // 获取所有浏览器的全部配置信息
            NamedNodeMap browserAttrs = browserList.item(i).getAttributes();
            if (Config.getNodeValue(browserAttrs.getNamedItem("status")).equals("disabled")) { // 浏览器停用
                continue;
            }
            HashMap<String, String> attrMap = new HashMap<String, String>();
            attrMap.put("name", Config.getNodeValue(browserAttrs.getNamedItem("name")));
            attrMap.put("version", Config.getNodeValue(browserAttrs.getNamedItem("version")));
            attrMap.put("platform", Config.getNodeValue(browserAttrs.getNamedItem("platform")));
            attrMap.put("driver", Config.getNodeValue(browserAttrs.getNamedItem("driver")));
            attrMap.put("pageLoadTimeout", Config.getNodeValue(browserAttrs.getNamedItem("pageLoadTimeout")));
            attrMap.put("scriptTimeout", Config.getNodeValue(browserAttrs.getNamedItem("scriptTimeout")));
            attrMap.put("implicitlyWait", Config.getNodeValue(browserAttrs.getNamedItem("implicitlyWait")));
            browserMap.put("Browser-" + String.format("%06d", i), attrMap);
        }
        return browserMap;
    }
}