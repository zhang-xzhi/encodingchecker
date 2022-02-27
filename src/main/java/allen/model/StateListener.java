package allen.model;

/**
 * 状态变换监听器。
 * */
public interface StateListener {

	/**
	 * 状态变化。
	 * */
	public void update(Action action);
}
