import java.io.File;
import java.io.FileNotFoundException;

public class DeleteFile implements CommandInterface {
	// Defines an array of strings for CommandInterface abstraction String[] params
	String[] m_params;
	
	public void setParams(String[] params)
	{
		m_params = params;
	}
	// Has a boolean flag that is set to TRUE if successful, FALSE if operation fails
	public boolean execute()
	{
		// Initial check to see if user inputs an appropriate file/directory
		if (m_params[0] == null)
		{
			System.out.println("Not enough params specified");
			return false;
		}
		// Sets string equal to JVM location + the file/directory of user's input
		// "user.dir" grabs the location where Java is run from
		String path = System.getProperty("user.dir") + "\\" + m_params[0];
		System.out.println("Deleting file: " + path);
		
    	try 
    	{   		 
    		File file = new File(path);
    		// deleteRecursive is defined below.
    		deleteRecursive(file);
    		// Prints confirmation and sets boolean true if deleteRecursive() is successful
    		System.out.println(file.getName() + " has been deleted!");
    		return true;
       	} 
    	catch (Exception e) 
    	{
    		System.out.println("Failed to delete file!");
    		e.printStackTrace();
    		return false;
    	}
	}
	// Performs a cascading delete in case there are files/directories inside the user input
    private static boolean deleteRecursive(File path) throws FileNotFoundException
    {
    	// Initialize error handling if deletion candidate does not exist
        if (!path.exists()) 
        	throw new FileNotFoundException(path.getAbsolutePath());
        // Boolean flag that is used to recursively delete files/directories
        boolean ret = true;
        if (path.isDirectory())
        {
            for (File f : path.listFiles())
            {
                ret = ret && deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }
}
