package lazycat.sys.util;

public class Random {

	/**
	 * generateNumberString: 生成数字随机串
	 * @return numberString 数字随机串
	 */
	public static String generateNumberString(int length) {
		String numberString = "";
		for (int i = 0; i < length; i++) {
			numberString += String.format("%01d", new java.util.Random().nextInt(9));
		}
		return numberString;
	}
}
