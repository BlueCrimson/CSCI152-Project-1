// Interface contains an abstract method with an array of strings as a parameter
// Abstract method execute is used in each class to perform unique operations
// Allows the program to reuse the same class/method structure in multiple classes
public interface CommandInterface {
	public void setParams(String[] params);
	public boolean execute();
}
