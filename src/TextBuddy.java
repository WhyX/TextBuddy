/** This class is used to manipulate text in a file
 * It includes all the basic functions: add, delete, display, clear and exit
 * The list of texts will be written into txt file
 * If the txt file input by user does not exist, a new txt file will be auto generated
 */

import java.util.*;
import java.io.*;

/** TextBuddy is a to-do list program. It supports the following functions:
 *		add <item> // item cannot be blank
 *		delete <index of item> // index of item must be positive integer
 *		display
 *		clear
 *		exit
 * @author WhyXce
 *
 */

public class TextBuddy {
	// messages shown to the user 
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use ";
	private static final String MESSAGE_COMMAND = "command: ";
	private static final String MESSAGE_ADD_SUCCESSFUL = "added to %s: \"%s\"";
	private static final String MESSAGE_DELETE_ITEM_SUCCESSFUL = "deleted from %s: \"%s\"";
	private static final String MESSAGE_CLEAR_FILE_CONTENTS_SUCCESSFUL = "all content deleted from %s";
	private static final String MESSAGE_DISPLAY_ITEM = "%s. %s";
	private static final String MESSAGE_FILE_EMPTY = "%s is empty";
	
	// error messages
	private static final String ERROR_INDEX_OUT_OF_BOUND = "Index out of range. Please try again";
	private static final String ERROR_INVALID_COMMAND = "Invalid command. Please try again.";
	private static final String ERROR_NULL_COMMAND = "Nothing is inserted. Please try again.";
	
	// indicate the index of first word and second word in user input
	private static final int INDEX_OF_FIRST_WORD = 1;
	private static final int INDEX_OF_SECOND_WORD = 2;
	
	private static ArrayList<String> textList = new ArrayList<String>();
	private static String fileName = new String();
	private static Scanner sc = new Scanner(System.in);
	private static BufferedReader reader;

	public static void main(String[] args) throws IOException{
		fileName = args[0];
		File file = new File(fileName);
		
		welcomeMessage();
		if(file.exists()){
			accessFile();
		}
		
		proceedUserCommand();
	}
	
	// print out welcome message to user
	private static void welcomeMessage(){
		System.out.println(String.format(MESSAGE_WELCOME, fileName));
	}
	
	// read file if exists 
	private static void accessFile(){
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
	private static void proceedUserCommand(){
		while(true){
			System.out.print(MESSAGE_COMMAND);
			String command = sc.nextLine();
			
			if(command.isEmpty()){
				showErrorMessage(ERROR_NULL_COMMAND);
				continue;
			}
		
			String[] userAction = command.trim().split("\\s+");
			
			proceedUserAction(userAction);
		}
	}
	
	// proceed user's desired action
	private static void proceedUserAction(String[] userAction){		
		String action = userAction[0].toLowerCase();

		switch(action){
			case "add": 
				addText(userAction);
				break;
			case "delete": 
				deleteText(userAction);
				break;
			case "display": 
				displayAllText();
				break;
			case "clear": 
				clearAllText();
				break;
			case "exit": 
				exit();
				break;
			default: 
				showErrorMessage(ERROR_INVALID_COMMAND);
		}
	}
	
	// execute add command which add text into the list
	private static void addText(String[] userAction){
		String textToBeAdded = userAction[INDEX_OF_FIRST_WORD];

		for(int i = INDEX_OF_SECOND_WORD; i < userAction.length; i++){
			textToBeAdded = textToBeAdded.concat(" ");
			textToBeAdded = textToBeAdded.concat(userAction[i]);
		}
		
		textList.add(textToBeAdded);
		
		System.out.println(String.format(MESSAGE_ADD_SUCCESSFUL, fileName, textToBeAdded));
	}

	// execute display command which show all the texts in the list
	private static void displayAllText(){
		if(textList.isEmpty()){
			System.out.println(String.format(MESSAGE_FILE_EMPTY, fileName));
		}
		
		for(int i = 0; i < textList.size(); i++){
			int displayIndex = i + 1;
			String displayText = textList.get(i);
			System.out.println(String.format(MESSAGE_DISPLAY_ITEM, displayIndex, displayText));
		}
	}
	
	// execute delete command which delete the desired text from the list
	private static void deleteText(String[] userAction){
		if(textList.isEmpty()){
			System.out.println(String.format(MESSAGE_FILE_EMPTY, fileName));
			return;
		}
		
		int removedIndex = Integer.valueOf(userAction[1]) - 1;
			
		if(removedIndex < 0 || removedIndex >= textList.size()){
			showErrorMessage(ERROR_INDEX_OUT_OF_BOUND);
		}
		else{
			String removedText = textList.get(removedIndex);
				
			textList.remove(removedIndex);
			System.out.println(String.format(MESSAGE_DELETE_ITEM_SUCCESSFUL, fileName, removedText));
		}
	}
	
	// execute clear command which delete all texts found in the list
	private static void clearAllText(){
		if(textList.isEmpty()){
			System.out.println(String.format(MESSAGE_FILE_EMPTY, fileName));
		}
		else{
			textList.clear();
			System.out.println(String.format(MESSAGE_CLEAR_FILE_CONTENTS_SUCCESSFUL, fileName));
		}
	}
	
	// execute exit command and write the list of texts into txt type file
	private static void exit(){
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
	private static void showErrorMessage(String errorMessage){
		System.out.println(errorMessage);
	}
}
