package allen.main;

import javax.swing.SwingUtilities;

import allen.ui.MainFrame;

/**
 * Check file's encoding, convert encoding.
 * */
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                try {
                    MainFrame frame = new MainFrame();
                    frame.initAndShow();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
    }
}
