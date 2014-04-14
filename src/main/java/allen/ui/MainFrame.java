package allen.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import allen.model.Action;
import allen.model.StateMachine;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public void initAndShow() {
        initMenu();
        initComponent();
        initSelf();

        StateMachine.takeAction(Action.InitProgram);
    }

    private void initMenu() {
        JMenuBar menubar = new JMenuBar();
        menubar.add(getFileMenu());
        menubar.add(getAboutMenu());
        this.setJMenuBar(menubar);
    }

    private JMenu getFileMenu() {

        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JSeparator jSeparator = new JSeparator(SwingConstants.HORIZONTAL);

        fileMenu.add(jSeparator);
        fileMenu.add(exitItem);
        return fileMenu;
    }

    private JMenu getAboutMenu() {

        JMenu aboutMenu = new JMenu("About");
        return aboutMenu;
    }

    private void initComponent() {
        this.getContentPane().add(new UpPanel(), BorderLayout.NORTH);
        this.getContentPane().add(new ShowPanel(), BorderLayout.CENTER);
    }

    private void initSelf() {
        this.setPreferredSize(new Dimension(1000, 800));
        this.setMinimumSize(new Dimension(1000, 800));
        this.setTitle("Encoding checker v0.5");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
