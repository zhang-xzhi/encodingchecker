package allen.util;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;


/**
 * FileReadUtil.
 * */
public class FileReadUtil {

    /**
     * Read input and return a byte[]. Client open and close input.
     * */
    public static byte[] readBytes(InputStream input) throws IOException {
        List<Byte> list = new ArrayList<Byte>();
        int r = 0;
        while ((r = input.read()) != -1) {
            list.add((byte) r);
        }
        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * Read file.
     * */
    public static byte[] readBytes(File file) throws IOException {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            return readBytes(bis);
        } finally {
            IOUtil.close(bis);
        }
    }

}
