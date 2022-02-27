package allen.encoding;

import java.util.HashMap;
import java.util.Map;

/**
 * Encoding.
 * 
 * <pre>
 * 包含java的编码和扩展对bom识别的编码(扩展编码).
 * </pre>
 * */
public class Encoding {

    private static String WithBom    = "_withBom";

    private static String WithoutBom = "_withoutBom";

    public static String  US_ASCII   = "US-ASCII";

    /** ISO-LATIN-1 */
    public static String  ISO_8859_1 = "ISO-8859-1";

    public static String  UTF_8      = "utf-8";

    public static String  UTF_16BE   = "UTF-16BE";

    public static String  UTF_16LE   = "UTF-16LE";

    public static String  UTF_32BE   = "UTF-32BE";

    public static String  UTF_32LE   = "UTF-32LE";

    public static String  GBK        = "GBK";

    /**
     * 得到扩展编码表,所有和扩展编码相关的处理,应该调用该类所在的包中的方法完成.
     * */
    public static String[] getExtEncodings() {
        return new String[] { GBK, US_ASCII, ISO_8859_1, UTF_8 + WithBom,
                UTF_8 + WithoutBom, UTF_16BE + WithBom, UTF_16BE + WithoutBom,
                UTF_16LE + WithBom, UTF_16LE + WithoutBom, UTF_32BE + WithBom,
                UTF_32BE + WithoutBom, UTF_32LE + WithBom,
                UTF_32LE + WithoutBom, };
    }

    public static byte[]               utf_8_bom     = EncodingUtil
                                                             .parseBytesFromHexString("EF BB BF");

    public static byte[]               utf_16_BE_bom = EncodingUtil
                                                             .parseBytesFromHexString("FE FF");

    public static byte[]               utf_16_LE_bom = EncodingUtil
                                                             .parseBytesFromHexString("FF FE");

    public static byte[]               utf_32_BE_bom = EncodingUtil
                                                             .parseBytesFromHexString("00 00 FE FF");

    public static byte[]               utf_32_LE_bom = EncodingUtil
                                                             .parseBytesFromHexString("FF FE 00 00");
    /**
     * extEncoding -- BOM byte[] pair
     * */
    private static Map<String, byte[]> map           = new HashMap<String, byte[]>();

    static {
        map.put(UTF_8 + WithBom, utf_8_bom);
        map.put(UTF_16BE + WithBom, utf_16_BE_bom);
        map.put(UTF_16LE + WithBom, utf_16_LE_bom);
        map.put(UTF_32BE + WithBom, utf_32_BE_bom);
        map.put(UTF_32LE + WithBom, utf_32_LE_bom);
    }

    /**
     * 从扩展的编码得到实际的编码。 若非扩展编码，则返回原值。
     * */
    public static String getRealEncoding(String extEncoding) {
        if (extEncoding.endsWith(WithBom)) {
            return extEncoding.substring(0,
                    extEncoding.length() - WithBom.length());
        }
        if (extEncoding.endsWith(WithoutBom)) {
            return extEncoding.substring(0,
                    extEncoding.length() - WithoutBom.length());
        }
        return extEncoding;
    }

    /**
     * 扩展的编码是否带BOM.
     * */
    public static boolean isWithBomEncoding(String extEncoding) {
        return extEncoding.endsWith(WithBom);
    }

    /**
     * 扩展的编码是否不带BOM.
     * */
    public static boolean isWithoutBomEncoding(String extEncoding) {
        return extEncoding.endsWith(WithoutBom);
    }

    /**
     * 从扩展的编码得到Bom字节数组.
     * 
     * <pre>
     * extEncoding必须满足isWithBomEncoding或isWithoutBomEncoding为true.
     * _withBom的编码得到对应的 bytes.
     * _withoutBom的编码得到对应的_withBom的bytes.
     * </pre>
     * */
    public static byte[] findBomBytes(String extEncoding) {

        byte[] result = null;

        if (isWithBomEncoding(extEncoding)) {
            result = map.get(extEncoding);
        }

        if (isWithoutBomEncoding(extEncoding)) {
            result = map.get(extEncoding.replace(WithoutBom, WithBom));
        }

        if (result == null) {
            throw new RuntimeException("wrong extEncoding. extEncoding = "
                    + extEncoding);
        }

        return result;
    }
}
