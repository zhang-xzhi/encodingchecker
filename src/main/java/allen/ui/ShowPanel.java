package allen.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import allen.ui.subpanel.BinaryPanel;
import allen.ui.subpanel.HexPanel;
import allen.ui.subpanel.InfoPanel;
import allen.ui.subpanel.TextPanel;

public class ShowPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ShowPanel() {
		this.setLayout(new GridLayout(2, 2));
		this.add(new InfoPanel());
		this.add(new TextPanel());
		this.add(new HexPanel());
		this.add(new BinaryPanel());
	}
}
