package allen.main;

import allen.encoding.Encoding;

/**
 * Config.
 * */
public class Config {

	/**
	 * 程序支持的所有扩展编码。
	 * */
	public static String[] extEncodings = Encoding.getExtEncodings();

	/**
	 * 默认编码.
	 * 
	 * <pre>
	 * 大家都用GBK,完全没有面向国际化.当然,我写中文注释也是一样的.
	 * </pre>
	 * */
	public static String Default_Encoding = extEncodings[0];
}
