package allen.model;

import java.util.ArrayList;
import java.util.List;

public class StateMachine {

	private static List<StateListener> listeners = new ArrayList<StateListener>();

	/**
	 * ×¢²á¼àÌıÆ÷¡£
	 * */
	public static void register(StateListener listener) {
		listeners.add(listener);
	}

	/**
	 * ´¥·¢¶¯×÷¡£
	 * */
	public static void takeAction(Action action) {
		for (StateListener listener : listeners) {
			listener.update(action);
		}
	}
}
