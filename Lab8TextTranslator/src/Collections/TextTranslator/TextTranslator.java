package Collections.TextTranslator;

import java.util.*;
import java.io.*;

public class TextTranslator {

    public static void main(String[] args) {

        // Load acronyms from file into a Map
        Map<String, String> acronyms = loadAcronyms("Lab8TextTranslator/acronyms.txt");

        // Create scanner for user input (use delimiter "\n")
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Enter a sentence: ");

        // Read full sentence from user
        String userInput = in.next();
        
        //Reads full sentence from user and splits sentence into words
        //Scanner in = new Scanner(System.in).useDelimiter("\\n"); 
        //String userInput = in.next(); 
        //String[] words = userInput.split("\\s+"); 

        // Translate sentence using the Map
        String translated = translateSentence(userInput, acronyms);

        // Print the translated sentence
        System.out.println(translated);
    }

    // ------------------------------------------------------
    // Load acronyms from file into a Map
    // ------------------------------------------------------
    public static Map<String, String> loadAcronyms(String filename) {
        Map<String, String> map = new HashMap<>();

        try {
            Scanner fileScanner = new Scanner(new File(filename));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\t", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim().toLowerCase();
                    String value = parts[1].trim();
                    map.put(key, value);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            System.out.println("Make sure acronyms.txt is in the correct location.");
        }

        return map;
    }

    // ------------------------------------------------------
    // Translate full sentence
    // ------------------------------------------------------
    public static String translateSentence(String input, Map<String, String> acronyms) {
        // TODO: Split the sentence into words using regex
        // Hint: String[] words = input.split("\\s+");
    	String[] words = input.split("\\s+");

        // TODO: Use a StringBuilder to build the result
        // Loop through each word, call translateWord(word, acronyms), and append with a space
    	StringBuilder result = new StringBuilder();
    	
    	for (String word : words) {
    		result.append(translateWord(word, acronyms)).append(" ");
    	}
    	
    	//returns result converted to a string with extra spaces removed
        return result.toString().trim(); // placeholder
    }

    // ------------------------------------------------------
    // Translate a single word (handles punctuation)
    // ------------------------------------------------------
    public static String translateWord(String word, Map<String, String> acronyms) {
        // TODO: Identify trailing punctuation at the end of the word
        // Hint: start from the end and collect non-alphanumeric characters
        // Then separate the core word from punctuation
        // Example: "lol." -> core = "lol", punctuation = "."
    	String punctuation = "";
    	
    	//checks that there are characters in word AND if there is punctuation at the end of the word
    	while(word.length() > 0 && !Character.isLetterOrDigit(word.charAt(word.length() - 1))) {
    		//changes value of punctuation to the trailing punctuation
    		punctuation = word.charAt(word.length() - 1) + punctuation;
    		//word equals word minus the trailing punctuation
    		word = word.substring(0, word.length() - 1);
    	}
    	

        // TODO: Convert core to lowercase and look it up in the map
        // If found, replace with expanded form + punctuation
        // If not found, keep original word (with punctuation)
    	word = word.toLowerCase();
    	
    	//checks if word is an acronym and returns the acronym expansion with punctuation
    	//returns the word with punctuation if it's not an acronym
    	if (acronyms.containsKey(word)) {
    		return acronyms.get(word) + punctuation;
    	} else {
    		return word + punctuation;
    	}
    	

        // Hint: You can use a loop from the end of the string to collect punctuation

    }
}