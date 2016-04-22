import java.io.IOException;
import java.io.RandomAccessFile;

public class CreateFile implements CommandInterface {
	
	String[] m_params;
	
	public void setParams(String[] params)
	{
		m_params = params;
	}
	// Has a boolean flag that is set to TRUE if successful, FALSE if operation fails
	public boolean execute()
	{
		// Initial check to ensure user inputs file name and size
		if (m_params[0] == null || m_params[1] == null)
		{
			System.out.println("Not enough params specified");
			return false;
		}
		
		System.out.println("Creating file: " + m_params[0]);
		
    	try 
    	{   
    		// Sets string equal to JVM location + the file/directory of user's input
    		// "user.dir" grabs the location where Java is run from
    		String path = System.getProperty("user.dir") + "\\" + m_params[0];
    		// RandomAccessFile allows file size to be set to user's m_params[1]
    		RandomAccessFile file = new RandomAccessFile(path, "rw");
            file.setLength(Integer.parseInt(m_params[1]));

    		System.out.println("File is created!");
    		file.close();
    		return true;
       	} 
    	// Exception handling if file failed to create
    	catch (IOException e) 
    	{
    		System.out.println("Failed to create file!");
    		e.printStackTrace();
    		return false;
    	}
	}
}
