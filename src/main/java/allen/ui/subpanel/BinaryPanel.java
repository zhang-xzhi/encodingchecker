package allen.ui.subpanel;

import allen.encoding.EncodingUtil;
import allen.model.Action;

public class BinaryPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	public BinaryPanel() {
	}

	@Override
	public void update(Action action) {
		switch (action) {

		case InitProgram:
			jtext.setText("");
			break;

		case ChangeEncoding:
			break;

		case FileLoad:
			jtext.setText(makeBinaryString());
			break;

		default:
			jtext.setText("not handle action : " + action.name());
			break;
		}
	}

	private String makeBinaryString() {
		StringBuilder sb = new StringBuilder();
		for (byte b : data.getFileContent()) {
			sb.append(EncodingUtil.toBinaryString(b));
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	protected String getTitle() {
		return "Binary";
	}

}
