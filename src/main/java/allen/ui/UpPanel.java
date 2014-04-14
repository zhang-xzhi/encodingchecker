package allen.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import allen.encoding.ExtEncodingUtil;

import allen.main.Config;
import allen.model.Action;
import allen.model.StateListener;
import allen.model.StateMachine;
import allen.model.UserData;
import allen.ui.file.FileHelper;
import allen.util.FileReadUtil;
import allen.util.FileWriteUtil;

public class UpPanel extends JPanel implements StateListener {

    private static final long serialVersionUID     = 1L;
    private static final Log  log                  = LogFactory
                                                           .getLog(UpPanel.class);

    // about ui.
    private JLabel            loadFileLabel        = new JLabel("select file");
    private JButton           loadFileButton       = new JButton("load");
    private JLabel            encodingLabel        = new JLabel(
                                                           "change encoding(only text)");
    private JComboBox         encodingList         = new JComboBox(
                                                           Config.extEncodings);

    private JLabel            convertLabel         = new JLabel(
                                                           "convert To New Encoding File");
    private JButton           convertButton        = new JButton("convert");
    private JLabel            convertEncodingLabel = new JLabel("encoding");
    private JComboBox         convertEncodingList  = new JComboBox(
                                                           Config.extEncodings);

    private UserData          data                 = UserData.userData;

    public UpPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(loadFileLabel);
        this.add(loadFileButton);

        this.add(encodingLabel);
        this.add(encodingList);

        this.add(convertLabel);
        this.add(convertButton);

        this.add(convertEncodingLabel);
        this.add(convertEncodingList);

        encodingList.setSelectedItem(data.getEncoding());
        convertEncodingList.setSelectedItem(data.getEncoding());

        addListener();

        StateMachine.register(this);

    }

    private void addListener() {

        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File file = FileHelper.selectFileForRead(UpPanel.this);

                if (file == null) {
                    return;
                }

                StateMachine.takeAction(Action.InitProgram);

                try {
                    data.setFile(file);
                    data.setFileContent(FileReadUtil.readBytes(file));

                    StateMachine.takeAction(Action.FileLoad);

                } catch (Exception ex) {

                    log.error("error when reading file.", ex);

                    StateMachine.takeAction(Action.InitProgram);
                }

            }
        });

        encodingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String newEncoding = getExtEncoding();

                if (data.getEncoding().equals(newEncoding)) {
                    return;
                }

                data.setEncoding(newEncoding);

                StateMachine.takeAction(Action.ChangeEncoding);
            }
        });

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File saveFile = FileHelper.selectFileForSave(UpPanel.this);

                    if (saveFile == null) {
                        return;
                    }

                    if (!data.hasFile()) {
                        return;
                    }
                    String oldEncoding = getExtEncoding();
                    String newEncoding = getConvertExtEncoding();

                    byte[] newData = ExtEncodingUtil.convertBytes(
                            data.getFileContent(), oldEncoding, newEncoding);

                    FileWriteUtil.save2File(saveFile, newData);

                } catch (Exception ex) {

                    log.error("error when convert file encoding.", ex);

                    JOptionPane.showMessageDialog(null, "error.",
                            "save error.", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private String getExtEncoding() {
        Object obj = encodingList.getSelectedItem();
        return (String) obj;
    }

    private String getConvertExtEncoding() {
        Object obj = convertEncodingList.getSelectedItem();
        return (String) obj;
    }

    @Override
    public void update(Action action) {
        switch (action) {

            case InitProgram:
                convertButton.setEnabled(false);
                break;

            case FileLoad:// fall down.
            case ChangeEncoding:

                String extEncoding = getExtEncoding();
                byte[] contentData = data.getFileContent();

                if (ExtEncodingUtil.isRightEncoding(contentData, extEncoding)) {
                    convertButton.setEnabled(true);
                } else {
                    convertButton.setEnabled(false);
                }
                break;

            default:
                break;
        }
    }
}
