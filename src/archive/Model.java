package keyboard_maps_with_lambdas;

/**
 * Implementation of a simple model for MVC.
 */
public class Model implements IModel {
	/** The data. */
	private String input;

	/** Default constructor. */
	public Model() {
		input = "";
	}

	@Override
	public void setString(String i) {
		input = i;
	}

	@Override
	public String getString() {
		return input;
	}
}