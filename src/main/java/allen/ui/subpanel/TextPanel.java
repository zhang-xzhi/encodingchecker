package allen.ui.subpanel;

import allen.encoding.ExtEncodingUtil;
import allen.model.Action;

public class TextPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	public TextPanel() {
	}

	@Override
	public void update(Action action) {

		switch (action) {

		case InitProgram:
			jtext.setText("");
			break;

		case ChangeEncoding:
			if (data.hasFile()) {
				jtext.setText(makeText());
			}
			break;

		case FileLoad:
			jtext.setText(makeText());
			break;

		default:
			jtext.setText("not handle action : " + action.name());
			break;
		}

	}

	private String makeText() {
		try {
			return ExtEncodingUtil.decodeBytes(data.getFileContent(),
					data.getEncoding());
		} catch (Exception e) {
			log.error("error.", e);
			return "error! select other encoding.";
		}
	}

	@Override
	protected String getTitle() {
		return "Text";
	}
}
