package allen.encoding;

import java.io.UnsupportedEncodingException;

/**
 * EncodingUtil
 * */
public class EncodingUtil {

    private static String[] int2Hex = new String[] { "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", };

    /**
     * 以utf-8编码字符串为Hex字符串。
     * */
    public static String toHexStringUsingUtf8(String str) {
        if (str == null) {
            return null;
        }
        byte[] data;
        try {
            data = str.getBytes(Encoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return toHexString(data);
    }

    /**
     * 解码HexString。hexString为utf-8格式。
     * */
    public static String parseStringFromHexStringUsingUtf8(String str) {
        if (str == null) {
            return null;
        }

        byte[] data = parseBytesFromHexString_0(str);
        try {
            return new String(data, Encoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从16进制的字符串str中解析一个字节数组。
     * 
     * */
    private static byte[] parseBytesFromHexString_0(String str) {

        if (str == null) {
            return new byte[0];
        }

        if (str.length() % 2 == 1) {
            throw new RuntimeException();
        }

        byte[] result = new byte[str.length() / 2];

        for (int i = 0; i < result.length; i++) {
            String s = str.substring(i * 2, i * 2 + 2);
            int value = Integer.parseInt(s, 16);
            result[i] = (byte) value;
        }
        return result;
    }

    /**
     * 从16进制的字符串str中解析一个字节数组。字符串在16进制字符外可以有其他字符，解析时自动忽略。
     * */
    public static byte[] parseBytesFromHexString(String str) {
        if (str == null) {
            return new byte[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            }
            if (c >= 'A' && c <= 'F') {
                sb.append(c);
            }
            if (c >= 'a' && c <= 'f') {
                sb.append(c);
            }
        }
        return parseBytesFromHexString_0(sb.toString());
    }

    /**
     * Convert bytes to hex string.
     * */
    public static String toHexString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toHexString(b));
        }
        return sb.toString();
    }

    /**
     * Convert bytes to int value string.
     * */
    public static String toUnsignIntString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int r = b & 0xFF;
            sb.append(r + ",");
        }
        return sb.toString();
    }

    /**
     * Convert byte to hex string.
     * */
    public static String toHexString(byte b) {

        StringBuilder sb = new StringBuilder();

        int r1 = (b >>> 4) & 0xF;
        sb.append(int2Hex[r1]);
        int r2 = b & 0xF;
        sb.append(int2Hex[r2]);

        return sb.toString();
    }

    /**
     * Convent byte to binary string.
     * */
    public static String toBinaryString(byte b) {
        StringBuilder sb = new StringBuilder();
        int intb = 0xFF & b;
        for (int i = 0; i < 8; i++) {
            int tem = intb >>> (7 - i);
            if ((tem & 0x01) == 1) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }
}
