import java.io.File;

public class MakeDirectory implements CommandInterface {
	// Defines an array of strings for CommandInterface abstraction
	String[] m_params;

	public void setParams(String[] params)
	{
		m_params = params;
	}
	// Has a boolean flag that is set to TRUE if successful, FALSE if operation fails
	public boolean execute()
	{
		// Initial check to see if user input is null
		if (m_params[0] == null)
		{
			System.out.println("No params specified");
			return false;
		}
		
		System.out.println("Making directory: " + m_params[0]);
		// Sets string equal to JVM location + the file/directory of user's input
		// "user.dir" grabs the location where Java is run from
		String path = System.getProperty("user.dir") + "\\" + m_params[0];
		File file = new File(path);
		// Checks if directory does not exist
		if (!file.exists()) 
		{
			// If it does not already exist, attempt to create it, return error if it fails
			if (file.mkdir()) 
			{
				System.out.println("Directory is created!");
				return true;
			} else 
			{
				System.out.println("Failed to create directory!");
				return false;
			}
		}
		// Returns FALSE if directory already exists
		else
		{
			System.out.println("Directory already exists!");
			return false;
		}
	}
}
