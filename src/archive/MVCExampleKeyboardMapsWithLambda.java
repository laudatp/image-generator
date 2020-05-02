package keyboard_maps_with_lambdas;

/**
 * This example shows how to specify the (Key,Runnable) keyboard map using
 * shorter syntax using the ability of java 8 to support lambda expressions.
 */
public class MVCExampleKeyboardMapsWithLambda {
	/**
	 * Starting point for the program.
	 * 
	 * @param args not used.
	 */
	public static void main(String[] args) {
		IModel model = new Model();
		Controller controller = new Controller(model);
		IView view = new JFrameView("Echo a string");
		controller.setView(view);

	}
}
