/********************************************************************************************
 * Programmer: Kevin Ngo
 * Instructor: Dr. Shih-Hsi "Alex" Liu
 * CSCI 152 Project 1
 * 
 * This assignment performs file system operations such as directory and file creation,
 * file deletion, and directory/file location and size returns. The main purpose of this
 * project is to demonstrate the Composite design pattern by making a program that 
 * uses abstraction to perform each user-inputed operation by calling classes that inherit
 * methods from an interface. The interface contains methods that are commonly used in each
 * class. The program also distinguishes between files and directories when creating, deleting,
 * and scanning through the file system.
 * 
 * INSTRUCTIONS: Type in one of the 7 commands that the program will interpret, followed by 
 * the parameters requested (e.g. <dirname>, <filename>, or <size>). Make sure to separate
 * the commands with a space! Also, keep in mind that the program is case sensitive, so type
 * exactly as what the program requests.
 * 
 * NOTE: This project does not simulate a file system. Directories and files are creating
 * in the JVM folder. This means that the deletion operation is potentially DANGEROUS to your
 * system if used incorrectly. Make sure that you are not deleting the wrong item!
 */

import java.util.Scanner;

public class main {
	// Declare global scanner to handle user input
	static Scanner m_scanner = new Scanner(System.in);
	// Function that prints out user commands
	private static void printHelp()
	{
		System.out.println("Welcome! Please select one of the following operations: \n"
			+ "mkdir <dirname> \n"
			+ "create <filename> <size> \n"
			+ "cd <dirname> | .. \n"
			+ "del <dirname> | <filename> \n"
			+ "size <dirname> | <filename> \n"
			+ "ls <dirname> | <filename> \n"
			+ "quit\n");
	}
	// Function that reads user input as split strings and invokes the appropriate class function.
	// Has a boolean flag initialized to TRUE that is set to FALSE if user quits program.
	private static boolean processCommand()
	{
		String input = m_scanner.nextLine();
		String[] fields = input.split(" ");
		String command = "";
		String[] params = new String[2];
		// If statements that initialize the split strings from user input
		// First part of string is interpreted as user command
		// Second/third parts of string are interpreted as parameters for method invocation
		if (fields.length > 0)
			command = fields[0];
	
		if (fields.length > 1)
			params[0] = fields[1];
		
		if (fields.length > 2)
			params[1] = fields[2];
		// Switch handles user command input and calls appropriate method
		switch (command)
		{
		case "mkdir":
			MakeDirectory mkdir = new MakeDirectory();
			mkdir.setParams(params);
			mkdir.execute();
			break;
		case "create":
			CreateFile create = new CreateFile();
			create.setParams(params);
			create.execute();
			break;
		// Note: cd command is not abstracted from CommandInterface since it does not use setParams() method
		case "cd":		
			String currentDir = System.getProperty("user.dir");
			if (params[0].startsWith(".."))
			{
				// dir1/dir2/dir3
				if (currentDir.contains("\\"))
				{
					int endIndex = currentDir.lastIndexOf("\\");
					if (endIndex != -1)  
						currentDir = currentDir.substring(0, endIndex);
				}
				else
				{
					currentDir = "";
				}
			}
			else
			{
				if (!currentDir.isEmpty())
					currentDir = currentDir + "\\" + params[0];
				else
					currentDir = params[0];
			}
			System.setProperty("user.dir", currentDir);
			System.out.println("Current directory: " +  System.getProperty("user.dir"));
			break;
		case "del":
			DeleteFile delete = new DeleteFile();
			delete.setParams(params);;
			delete.execute();
			break;
		case "size":
			ReturnSize size = new ReturnSize();
			size.setParams(params);
			size.execute();
			break;
		case "ls":
			ReturnList list = new ReturnList();
			list.setParams(params);
			list.execute();
			break;
		case "quit":
			System.out.println("Quitting");
			return false;
		// Default case so if user inputs anything other than predefined command
		// then the list of user commands is reprinted
		default:
			printHelp();
			break;
		}
		return true;
	}

	public static void main(String[] args) {
		// printHelp() is called if user inputs anything other than an appropriate command
		printHelp();
		// While loop that allows user to continue until 'quit' is inputed
		while (processCommand()) 
		{
		}
	}
}
