package keyboard_maps_with_lambdas;

/**
 * The interface for our model class.
 */
public interface IModel {
	/**
	 * Mutator for the model data.
	 * 
	 * @param value The new value for the data.
	 */
	void setString(String value);

	/**
	 * Accessor for the model data.
	 * 
	 * @return the model data
	 */
	String getString();
}
