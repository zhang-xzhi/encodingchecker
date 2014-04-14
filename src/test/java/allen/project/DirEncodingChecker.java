package allen.project;

import java.io.File;

import allen.encoding.Encoding;
import allen.encoding.ExtEncodingUtil;
import allen.util.FileReadUtil;

public class DirEncodingChecker {

    public static void main(String[] args) throws Exception {
        String path = "D:\\allen_code_git\\simplehbase\\src";
        String encoding = Encoding.UTF_8;

        File file = new File(path);
        checkEncodingDir(file, encoding);
        System.out.println("Done");
    }

    private static void checkEncodingFile(File file, String encoding)
            throws Exception {
        byte[] data = FileReadUtil.readBytes(file);
        if (!ExtEncodingUtil.isRightEncoding(data, encoding)) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static void checkEncodingDir(File file, String encoding)
            throws Exception {
        File[] fs = file.listFiles();
        if (fs != null) {
            for (File f : fs) {
                if (f.isDirectory()) {
                    checkEncodingDir(f, encoding);
                } else {
                    checkEncodingFile(f, encoding);
                }
            }
        }
    }
}
