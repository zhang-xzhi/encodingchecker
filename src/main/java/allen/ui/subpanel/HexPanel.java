package allen.ui.subpanel;

import allen.encoding.EncodingUtil;
import allen.model.Action;

public class HexPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private int byteInLine = 16;

	public HexPanel() {
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
			jtext.setText(makeHexStr());
			break;

		default:
			jtext.setText("not handle action : " + action.name());
			break;
		}
	}

	private String makeHexStr() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.getFileContent().length; i++) {
			sb.append(EncodingUtil.toHexString(data.getFileContent()[i]));
			sb.append("  ");
			if (i % byteInLine == (byteInLine - 1)) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	@Override
	protected String getTitle() {
		return "Hex";
	}

}
