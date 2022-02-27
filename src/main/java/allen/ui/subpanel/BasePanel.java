package allen.ui.subpanel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import allen.model.StateListener;
import allen.model.StateMachine;
import allen.model.UserData;

abstract public class BasePanel extends JPanel implements StateListener {

    private static final long serialVersionUID = 1L;

    protected final Log       log              = LogFactory.getLog(getClass());

    protected JTextArea       jtext            = new JTextArea();
    protected UserData        data             = UserData.userData;

    public BasePanel() {

        JScrollPane jScrollPane = new JScrollPane(jtext);

        this.setLayout(new BorderLayout());
        this.add(jScrollPane, BorderLayout.CENTER);

        TitledBorder border = BorderFactory.createTitledBorder(getTitle());
        setBorder(border);

        StateMachine.register(this);
    }

    /**
     * 子panel的标题。
     * */
    abstract protected String getTitle();

}
