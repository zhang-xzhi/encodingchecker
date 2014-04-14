package allen.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A Util class for IO.
 * */
public class IOUtil {

    private static Log log = LogFactory.getLog(IOUtil.class);

    /**
     * close reader.
     * */
    public static void close(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                log.warn(e);
            }
        }
    }

    /**
     * close writer.
     * */
    public static void close(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                log.warn(e);
            }
        }
    }

    /**
     * close OutputStream.
     * */
    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.warn(e);
            }
        }
    }

    /**
     * close InputStream.
     * */
    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.warn(e);
            }
        }
    }

    /**
     * Check file is file. Note: not a dir.
     * 
     * @throws RuntimeException if it is not file.
     * */
    public static void checkIsFile(File file) {
        if (file.isDirectory()) {
            log.error("File is not file. File name = " + file.getAbsolutePath());
            throw new RuntimeException(file.getAbsolutePath());
        }
    }

    /**
     * Check file is dir. Note: not a file.
     * 
     * @throws RuntimeException if it is not dir.
     * */
    public static void checkIsDir(File file) {
        if (file.isFile()) {
            log.error("File is not dir. File name = " + file.getAbsolutePath());
            throw new RuntimeException(file.getAbsolutePath());
        }
    }

    /**
     * try to create new file if file doesn't exist.
     */
    public static void createNewFileIfNotExist(String filePath) {
        File file = new File(filePath);
        createNewFileIfNotExist(file);
    }

    /**
     * try to create new file if file doesn't exist.
     */
    public static void createNewFileIfNotExist(File file) {
        if (file == null) {
            throw new NullPointerException();
        }

        // Fix the p. So the file can get its parent file.
        file = new File(file.getAbsolutePath());

        checkIsFile(file);

        log.debug("create New File If Not Exist. File = "
                + file.getAbsolutePath());

        if (!file.exists()) {
            try {
                File parentDir = file.getParentFile();

                createNewDirIfNotExist(parentDir);

                if (!file.createNewFile()) {
                    throw new RuntimeException("bad luck.");
                }

            } catch (IOException e) {
                log.error("create file fail.", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * try to create new dir if not exist.
     * */
    public static void createNewDirIfNotExist(File dir) {

        if (dir == null) {
            throw new NullPointerException();
        }

        checkIsDir(dir);

        if (!dir.exists()) {
            // Fix the p. So the file can get its parent file.
            dir = new File(dir.getAbsolutePath());

            File parentDir = dir.getParentFile();

            createNewDirIfNotExist(parentDir);

            if (!dir.mkdir()) {
                throw new RuntimeException("bad luck.");
            }
        }
    }

}
