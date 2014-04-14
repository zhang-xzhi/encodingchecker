package allen.ui.file;

/**
 * File exception.
 * */
public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileException(String msg) {
		this(msg, null);
	}

	public FileException(String msg, Throwable t) {
		super(msg, t);
	}

	public FileException(Throwable t) {
		this(null, t);
	}

}
