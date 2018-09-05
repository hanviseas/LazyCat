package lazycat.sys.core;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import lazycat.sys.map.ConfigMap;

public final class Server extends Thread {

	/**
	 * main: 程序入口
	 * @param args 参数列表
	 * @return void
	 */
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String[] arg_group = args[i].split("="); // 存储命令行参数（key=value的形式）至_args
			if (arg_group.length == 2 && arg_group[0].toString() != "" && arg_group[1].toString() != "") {
				_args.put(arg_group[0].toString(), arg_group[1].toString());
			}
		}
		Server.commander = new Commander();
		commander.dispatch();
	}

	/**
	 * _args: 参数表
	 */
	private static HashMap<String, String> _args = new HashMap<String, String>();

	public static String getArg(String key) {
		return _args.get(key);
	}

	/**
	 * commander: 测试指挥官
	 */
	private static Commander commander = null;

	public static Commander getCommander() {
		return commander;
	}

	/**
	 * currentPath: 当前路径
	 */
	private static String currentPath = initCurrentPath();

	public static String getCurrentPath() {
		return currentPath;
	}

	/**
	 * initCurrentPath: 初始化当前路径
	 * @return currentPath 当前路径
	 */
	private static String initCurrentPath() {
		String currentPath = ".";
		try { // 解析错误时，默认设为"."
			String classPath = System.getProperty("java.class.path");
			String[] classPaths = classPath.split(";");
			currentPath = URLDecoder.decode(new File(classPaths[0]).getParent(), "UTF-8");
		} catch (UnsupportedEncodingException e) { // 解析错误
			e.printStackTrace();
		}
		return currentPath;
	}

	/**
	 * runMode: 测试运行模式
	 */
	private static String runMode = initRunMode();

	public static String getRunMode() {
		return runMode;
	}

	/**
	 * initRunMode: 初始化测试运行模式
	 * @return runMode 测试运行模式
	 */
	private static String initRunMode() {
		String runMode = "local";
		try { // 读取配置错误时，默认设为local
			runMode = ConfigMap.getMap().get("normal").get("mode");
			runMode = runMode.equals("") ? "local" : runMode;
		} catch (Exception e) { // 配置错误
			e.printStackTrace();
		}
		return runMode;
	}

	/**
	 * remoteHost: 远程服务位置
	 */
	private static String remoteHost = initRemoteHost();

	public static String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * initRemoteHost: 初始化远程服务位置
	 * @return remoteHost 远程服务位置
	 */
	private static String initRemoteHost() {
		String remoteHost = "127.0.0.1:4444";
		try { // 读取配置错误时，默认设为本地默认端口
			remoteHost = ConfigMap.getMap().get("normal").get("remote");
			remoteHost = remoteHost.equals("") ? "127.0.0.1:4444" : remoteHost;
		} catch (Exception e) { // 配置错误
			e.printStackTrace();
		}
		return remoteHost;
	}

	/**
	 * packageName: 包名
	 */
	private static String packageName = initPackageName();

	public static String getPackageName() {
		return packageName;
	}

	/**
	 * initPackageName: 初始化包名
	 * @return packageName 包名
	 */
	private static String initPackageName() {
		String packageName = "lazycat";
		try { // 读取配置错误时，默认设为lazycat
			packageName = ConfigMap.getMap().get("normal").get("package");
			packageName = packageName.equals("") ? "lazycat" : packageName;
		} catch (Exception e) { // 配置错误
			e.printStackTrace();
		}
		return packageName;
	}

	/**
	 * pageLoadTimeout: 页面超时时间（秒）
	 */
	private static Integer pageLoadTimeout = initPageLoadTimeout();

	public static Integer getPageLoadTimeout() {
		return pageLoadTimeout;
	}

	/**
	 * initPageLoadTimeout: 初始化页面超时时间
	 * @return pageLoadTimeout 页面超时时间
	 */
	private static Integer initPageLoadTimeout() {
		Integer pageLoadTimeout = 30;
		try { // 读取配置错误时，默认设为30秒
			pageLoadTimeout = Integer.parseInt(ConfigMap.getMap().get("timeout").get("page"));
		} catch (Exception e) { // 配置错误
			e.printStackTrace();
		}
		return pageLoadTimeout;
	}

	/**
	 * scriptTimeout: 脚本超时时间（秒）
	 */
	private static Integer scriptTimeout = initScriptTimeout();

	public static Integer getScriptTimeout() {
		return scriptTimeout;
	}

	/**
	 * initScriptTimeout: 初始化脚本超时时间
	 * @return scriptTimeout 脚本超时时间
	 */
	private static Integer initScriptTimeout() {
		Integer scriptTimeout = 30;
		try { // 读取配置错误时，默认设为30秒
			scriptTimeout = Integer.parseInt(ConfigMap.getMap().get("timeout").get("script"));
		} catch (Exception e) { // 配置错误
			e.printStackTrace();
		}
		return scriptTimeout;
	}

	/**
	 * implicitlyWait: 等待超时时间（秒）
	 */
	private static Integer implicitlyWait = initImplicitlyWait();

	public static Integer getImplicitlyWait() {
		return implicitlyWait;
	}

	/**
	 * initImplicitlyWait: 初始化等待超时时间
	 * @return implicitlyWait 等待超时时间
	 */
	private static Integer initImplicitlyWait() {
		Integer implicitlyWait = 10;
		try { // 读取配置错误时，默认设为10秒
			implicitlyWait = Integer.parseInt(ConfigMap.getMap().get("timeout").get("implicitness"));
		} catch (Exception e) { // 配置错误
			e.printStackTrace();
		}
		return implicitlyWait;
	}

	/**
	 * generateId: 生成唯一标识号
	 * @return id 唯一标识号
	 */
	public static String generateId() {
		String time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
		String rand = String.format("%04d", new Random().nextInt(9999));
		return time + "-" + rand;
	}
}
