package allen.ui.file;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * An UI File helper class.
 * */
public class FileHelper {

    /**
     * Select a dir, return null when user cancel it.
     * */
    public static File selectDir(Component parent) {

        // Create a file chooser
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // In response to a button click:
        int returnVal = fc.showOpenDialog(parent);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return null;
        }

        throw new FileException("file exception when select dir.");
    }

    /**
     * Select a file for save opt. return null when user cancel it.
     * */
    public static File selectFileForSave(Component parent) {
        return selectFileForSave(parent, ".");
    }

    /**
     * Select a file for save opt. return null when user cancel it.
     * */
    public static File selectFileForSave(Component parent,
            String currentDirectoryPath) {

        // Create a file chooser
        final JFileChooser fc = new JFileChooser(currentDirectoryPath);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // In response to a button click:
        int returnVal = fc.showSaveDialog(parent);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            if (file.exists()) {
                int result = JOptionPane.showConfirmDialog(parent,
                        "File override confirm!", "Warning",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    return file;
                } else {
                    return null;
                }
            } else {
                return file;
            }
        }

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return null;
        }

        throw new FileException("file exception when input save file.");
    }

    /**
     * Select a file for read opt. return null when user cancel it.
     * */
    public static File selectFileForRead(Component parent) {
        return selectFileForRead(parent, ".");
    }

    /**
     * Select a file for read opt. return null when user cancel it.
     * */
    public static File selectFileForRead(Component parent,
            String currentDirectoryPath) {
        // Create a file chooser
        final JFileChooser fc = new JFileChooser(currentDirectoryPath);

        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // In response to a button click:
        int returnVal = fc.showOpenDialog(parent);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return null;
        }

        throw new FileException("file exception when select file.");
    }

    /**
     * Select a files for read opt. return null when user cancel it.
     * */
    public static File[] selectFilesForRead(Component parent) {
        return selectFilesForRead(parent, ".");
    }

    /**
     * Select a files for read opt. return null when user cancel it.
     * */
    public static File[] selectFilesForRead(Component parent,
            String currentDirectoryPath) {
        // Create a file chooser
        final JFileChooser fc = new JFileChooser(currentDirectoryPath);

        fc.setMultiSelectionEnabled(true);

        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // In response to a button click:
        int returnVal = fc.showOpenDialog(parent);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFiles();
        }

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return null;
        }

        throw new FileException("file exception when select file.");
    }

}
