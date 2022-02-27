package allen.encoding;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import allen.util.ArrayUtil;

/**
 * ExtEncodingUtil
 * */
public class ExtEncodingUtil {

    private static Log log = LogFactory.getLog(ExtEncodingUtil.class);

    /**
     * 校验data是否是采用extEncoding编码.
     * 
     * <pre>
     * 对于utf-16等带BOM的bytes,如果bytes不以BOM bytes开始,则认为不是正确的编码.
     * 对于utf-16等不带BOM的bytes,如果bytes以BOM bytes开始,则认为不是正确的编码.
     * </pre>
     * */
    public static boolean isRightEncoding(byte[] data, String extEncoding) {
        if (data == null || data.length == 0) {
            return false;
        }

        // 处理BOM. With BOM.
        if (Encoding.isWithBomEncoding(extEncoding)) {
            byte[] bomBytes = Encoding.findBomBytes(extEncoding);
            if (!ArrayUtil.startWith(data, bomBytes)) {
                return false;
            }

            byte[] tem = new byte[data.length - bomBytes.length];
            System.arraycopy(data, bomBytes.length, tem, 0, tem.length);
            data = tem;
        }
        // 处理BOM. Without BOM.
        if (Encoding.isWithoutBomEncoding(extEncoding)) {
            byte[] bomBytes = Encoding.findBomBytes(extEncoding);
            if (ArrayUtil.startWith(data, bomBytes)) {
                return false;
            }
        }

        String realEncoding = Encoding.getRealEncoding(extEncoding);

        try {
            String temStr = new String(data, realEncoding);
            byte[] newData = temStr.getBytes(realEncoding);

            return ArrayUtil.equals(data, newData);
        } catch (Exception e) {
            log.error(e);
            return false;
        }

    }

    /**
     * 将data使用decoding解码，然后使用encoding编码。
     * 
     * <pre>
     * decoding如果不能正确decoding则抛异常。 
     * encoding如果不能正确encoding则抛异常。
     * </pre>
     * */
    public static byte[] convertBytes(byte[] data, String decoding,
            String encoding) {
        if (data == null || data.length == 0) {
            throw new RuntimeException("wrong data.");
        }
        if (!isRightEncoding(data, decoding)) {
            throw new RuntimeException("wrong decoding.");
        }

        String str = decodeBytes(data, decoding);

        String realEncoding = Encoding.getRealEncoding(encoding);

        byte[] result = null;

        try {
            result = str.getBytes(realEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        if (!Encoding.isWithBomEncoding(encoding)) {
            return result;
        }

        // BOM.
        byte[] bomBytes = Encoding.findBomBytes(encoding);

        byte[] tem = new byte[result.length + bomBytes.length];
        System.arraycopy(bomBytes, 0, tem, 0, bomBytes.length);
        System.arraycopy(result, 0, tem, bomBytes.length, result.length);

        return tem;

    }

    /**
     * 解码bytes为string,对extEncoding不做校验.
     * 
     * <pre>
     * 调用该方法前需要使用#isRightEncoding来确保extEncoding是正确的。
     * </pre>
     * */
    public static String decodeBytes(byte[] data, String extEncoding) {

        if (data == null || data.length == 0) {
            throw new RuntimeException("wrong data.");
        }

        // 处理BOM.
        if (Encoding.isWithBomEncoding(extEncoding)) {
            byte[] bomBytes = Encoding.findBomBytes(extEncoding);
            if (!ArrayUtil.startWith(data, bomBytes)) {
                throw new RuntimeException("wrong extEncoding for data.");
            }

            byte[] tem = new byte[data.length - bomBytes.length];
            System.arraycopy(data, bomBytes.length, tem, 0, tem.length);
            data = tem;
        }

        String realEncoding = Encoding.getRealEncoding(extEncoding);
        try {
            return new String(data, realEncoding);
        } catch (Exception e) {
            throw new RuntimeException("wrong encoding for data.");
        }
    }
}
