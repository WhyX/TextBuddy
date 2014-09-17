/** This class is used to manipulate text in a file
 * It includes all the basic functions: add, delete, display, clear and exit
 * The list of texts will be written into txt file
 * If the txt file input by user does not exist, a new txt file will be auto generated
 */

import java.util.*;
import java.io.*;

/** TextBuddy is a CLI (Command Line Interface) program. It supports the following functions:
 *		add <text> // text cannot be blank 
 *		delete <index of item> // index of item must be positive integer
 *		display
 *		clear
 *		sort
 *		search <key word> // key word must be alphabets, case does not matter
 *		exit
 * @author WhyXce
 *
 */

public class TextBuddy {
	// messages shown to the user 
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use ";
	private static final String MESSAGE_COMMAND = "Command: ";
	private static final String MESSAGE_ADD_SUCCESSFUL = "Added successfully to %s: \"%s\"";
	private static final String MESSAGE_DELETE_SUCCESSFUL = "Deleted successfully from %s: \"%s\"";
	private static final String MESSAGE_SORT_SUCCESSFUL = "After sorting:\n%s";
	private static final String MESSAGE_SEARCH_SUCCESSFUL = "Desired text found in %s successfully";
	private static final String MESSAGE_SEARCH_FAILED = "%s is not found in %s";
	private static final String MESSAGE_CLEAR_TEXTLIST_SUCCESSFUL = "All texts deleted from %s successfully";
	private static final String MESSAGE_DISPLAY_ITEM = "%s. %s";
	private static final String MESSAGE_FILE_EMPTY = "%s is empty";
	
	// error messages
	private static final String ERROR_INDEX_OUT_OF_BOUND =  "Index out of range. Please try again";
	private static final String ERROR_INVALID_COMMAND = "Invalid command. Please try again.";
	private static final String ERROR_NULL_COMMAND = "Nothing is inserted. Please try again.";
	
	// indicate the index of first word and second word in user input
	private static final int INDEX_OF_FIRST_WORD = 1;
	private static final int INDEX_OF_SECOND_WORD = 2;
	
	// variable declared for the later use in the program
	private ArrayList<String> textList;
	private String fileName;
	private static BufferedReader reader;
	
	// variable declared for the whole class to use
	private static Scanner sc = new Scanner(System.in);

	// TextBuddy constructor to initialize textList and fileName
	public TextBuddy(String name){
		textList = new ArrayList<String>();
		fileName = new String();
		
		fileName = name;
	}
	
	// getter method to get the name of the file
	public String getFileName(){
		return fileName;
	}
	
	public static void main(String[] args) throws IOException{
		TextBuddy newTextBuddy = new TextBuddy(args[0]);
		String fileName = newTextBuddy.getFileName();
		File file = new File(fileName);
		
		welcomeMessage(fileName);
		if(file.exists()){
			newTextBuddy.accessFile(fileName);
		}
		
		newTextBuddy.proceedUserCommand();
	}
	
	// print out welcome message to user
	private static void welcomeMessage(String fileName){
		System.out.println(String.format(MESSAGE_WELCOME, fileName));
	}
	
	// read file if exists 
	private void accessFile(String fileName){
    	try{ 		 
  	      reader = new BufferedReader(new FileReader(fileName)); 
  	      String s;  
  	      while ((s = reader.readLine()) != null) {
  	    	  textList.add(s);
  	      }
      	} 
    	catch (IOException e){
  	      e.printStackTrace();
    	}
	}
	 
	// handle user commands
	private void proceedUserCommand(){
		while(true){
			System.out.print(MESSAGE_COMMAND);
			String command = sc.nextLine();
			
			if(command.isEmpty()){
				displayMessage(ERROR_NULL_COMMAND);
				continue;
			}
		
			String[] userAction = command.trim().split("\\s+");
			
			displayMessage(proceedUserAction(userAction));
		}
	}
	
	// proceed user's desired action
	public String proceedUserAction(String[] userAction){		
		String action = userAction[0].toLowerCase();
		String messageToUser = new String();
		
		switch(action){
			case "add": 
				messageToUser = addText(userAction);
				break;
			case "delete": 
				messageToUser = deleteText(userAction);
				break;
			case "display": 
				messageToUser = displayTextList();
				break;
			case "clear": 
				messageToUser = clearTextList();
				break;
			case "sort":
				messageToUser = sortTextList();
				break;
			case "search":
				messageToUser = searchText(userAction);
			case "exit": 
				exit();
				break;
			default: 
				messageToUser = ERROR_INVALID_COMMAND;
		}
		
		return messageToUser;
	}
	
	// execute add command which add text into the list
	 public String addText(String[] userAction){
		String textToBeAdded = userAction[INDEX_OF_FIRST_WORD];

		for(int i = INDEX_OF_SECOND_WORD; i < userAction.length; i++){
			textToBeAdded = textToBeAdded.concat(" ");
			textToBeAdded = textToBeAdded.concat(userAction[i]);
		}
		
		textList.add(textToBeAdded);
		
		return String.format(MESSAGE_ADD_SUCCESSFUL, fileName, textToBeAdded);
	}
	
	// execute delete command which delete the desired text from the list
	private String deleteText(String[] userAction){
		if(textList.isEmpty()){
			return String.format(MESSAGE_FILE_EMPTY, fileName);
		}
		
		int removedIndex = Integer.valueOf(userAction[1]) - 1;
			
		if(removedIndex < 0 || removedIndex >= textList.size()){
			return ERROR_INDEX_OUT_OF_BOUND;
		}
		else{
			String removedText = textList.get(removedIndex);
				
			textList.remove(removedIndex);
			return String.format(MESSAGE_DELETE_SUCCESSFUL, fileName, removedText);
		}
	}
	
	// execute display command which show all the texts in the list
	private String displayTextList(){
		if(textList.isEmpty()){
			return String.format(MESSAGE_FILE_EMPTY, fileName);
		}
		
		String displayList = "";
		
		for(int i = 0; i < textList.size(); i++){
			int displayIndex = i + 1;
			String displayText = textList.get(i);
			displayList = displayList.concat(String.format(MESSAGE_DISPLAY_ITEM, displayIndex, displayText));
			if(i != textList.size() - 1){
				displayList = displayList.concat("\n");
			}
		}
		
		return displayList;
	}
	
	// execute clear command which delete all texts found in the list
	private String clearTextList(){
		if(textList.isEmpty()){
			return String.format(MESSAGE_FILE_EMPTY, fileName);
		}
		else{
			textList.clear();
			return String.format(MESSAGE_CLEAR_TEXTLIST_SUCCESSFUL, fileName);
		}
	}
	
	// execute sort command which will sort text alphabetically and then return the sorted text list
	private String sortTextList(){
		if(textList.isEmpty()){
			return MESSAGE_FILE_EMPTY;
		}
		
		Collections.sort(textList);
		
		String sortedList = displayTextList();
		
		return String.format(MESSAGE_SORT_SUCCESSFUL, sortedList);
	}
	
	// execute search command which will search for the key word in the text file and
	// return all text containing the word regardless of the letter cases
	private String searchText(String[] userAction){
		return null;
	}
	
	// execute exit command and write the list of texts into txt type file
	private void exit(){
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			for(int i = 0; i < textList.size(); i++){
				int displayIndex = i + 1;
				String displayText = textList.get(i);
				writer.write(String.format(MESSAGE_DISPLAY_ITEM, displayIndex, displayText));
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.exit(0);
	}
	
	// show error message to user if invalid situation occurs
	private static void displayMessage(String message){
		System.out.println(message);
	}
}
