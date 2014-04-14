package allen.ui.subpanel;

import java.util.ArrayList;
import java.util.List;

import allen.encoding.ExtEncodingUtil;
import allen.main.Config;
import allen.model.Action;

public class InfoPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	public InfoPanel() {
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
			jtext.setText(makeInfoForData());
			break;
		default:
			jtext.setText("not handle action : " + action.name());
			break;
		}
	}

	private String makeInfoForData() {
		StringBuilder sb = new StringBuilder();
		sb.append("Working file path:\n");

		String fileName = data.getFile().getAbsolutePath();
		sb.append(fileName + "\n\n");

		List<String> supports = new ArrayList<String>();
		List<String> notSupports = new ArrayList<String>();

		testEncoding(Config.extEncodings, supports, notSupports);

		sb.append("possible encoding:\n");
		for (String s : supports) {
			sb.append(s + "\n");
		}
		sb.append("\n\n");

		sb.append("impossible encoding:\n");
		for (String s : notSupports) {
			sb.append(s + "\n");
		}
		sb.append("\n\n");
		sb.append("BOM\n");
		sb.append("UTF-8        EF BB BF\n");
		sb.append("UTF-16 (BE)  FE FF\n");
		sb.append("UTF-16 (LE)  FF FE\n");
		sb.append("UTF-32 (BE)  00 00 FE FF\n");
		sb.append("UTF-32 (LE)  FF FE 00 00\n");

		return sb.toString();
	}

	private void testEncoding(String[] encodings, List<String> supports,
			List<String> notSupports) {

		byte[] bytes = data.getFileContent();

		for (String encoding : encodings) {
			if (ExtEncodingUtil.isRightEncoding(bytes, encoding)) {
				supports.add(encoding);
			} else {
				notSupports.add(encoding);
			}

		}
	}

	@Override
	protected String getTitle() {
		return "Base info";
	}
}
