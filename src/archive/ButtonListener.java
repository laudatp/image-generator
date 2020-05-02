package keyboard_maps_with_lambdas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Implementation of a button listener that keeps track of all our actions.
 */
public class ButtonListener implements ActionListener {
	/** Map of button to their action. */
	private Map<String, Runnable> buttonClickedActions;

	/** Default constructor. */
	public ButtonListener() {
		buttonClickedActions = null;
	}

	/**
	 * Set the map for key typed events. Key typed events in Java Swing are
	 * characters.
	 * 
	 * @param map The map of actions to use.
	 */
	public void setButtonClickedActionMap(Map<String, Runnable> map) {
		buttonClickedActions = map;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (buttonClickedActions.containsKey(e.getActionCommand())) {

			buttonClickedActions.get(e.getActionCommand()).run();
		}
	}
}
