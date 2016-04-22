import java.io.File;

public class ReturnSize implements CommandInterface {
	
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
		// Calls method that handles the file/directory scanning for information
		return recursiveList(m_params[0]);
	}
	
	// Method is recursively called if a sub-directory is found within a directory
	private boolean recursiveList(String dirname) {
		
		// Sets string equal to JVM location + the file/directory of user's input
		// "user.dir" grabs the location where Java is run from
		String path = System.getProperty("user.dir") + "\\" + dirname;
		File file = new File(path);
		
		// Initial check to see if file exists
		if (!file.exists())
		{
			System.out.println("File or diectory does not exist.");
			return false;
		}
		// If files exists and is a directory, scan through the directory and print the file sizes
		if (file.isDirectory())
		{
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++)
			{
				// If indexed item is a file, print size
				if (listOfFiles[i].isFile())
				{
					System.out.println(listOfFiles[i].length() + " bytes");
				}
				// If indexed item is a directory, scan through the directory for files
				else if (listOfFiles[i].isDirectory())
				{
					recursiveList(dirname + "\\" + listOfFiles[i].getName());
				}
			}
			return true;
		}
		// If item exists and is a file, print file size
		if (file.isFile())
		{
			System.out.println(file.length() + " bytes");
			return true;
		}
		return false;
	}
}


