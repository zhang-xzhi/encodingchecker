package allen.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


/**
 * File write util class.
 */
public class FileWriteUtil {

    /**
     * save data to file.
     * */
    public static void save2File(File file, byte[] data) {
        if (data == null) {
            data = new byte[0];
        }
        IOUtil.createNewFileIfNotExist(file);
        save2File(file.getAbsolutePath(), data);
    }

    /**
     * save data to file.
     * */
    public static void save2File(String filePath, byte[] data) {
        if (data == null) {
            data = new byte[0];
        }

        IOUtil.createNewFileIfNotExist(filePath);
        BufferedOutputStream bufferOutput = null;
        try {
            bufferOutput = new BufferedOutputStream(new FileOutputStream(
                    new File(filePath)));
            bufferOutput.write(data);
            bufferOutput.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtil.close(bufferOutput);
        }
    }

}
