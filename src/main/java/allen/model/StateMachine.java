package allen.model;

import java.util.ArrayList;
import java.util.List;

public class StateMachine {

	private static List<StateListener> listeners = new ArrayList<StateListener>();

	/**
	 * 注册监听器。
	 * */
	public static void register(StateListener listener) {
		listeners.add(listener);
	}

	/**
	 * 触发动作。
	 * */
	public static void takeAction(Action action) {
		for (StateListener listener : listeners) {
			listener.update(action);
		}
	}
}
