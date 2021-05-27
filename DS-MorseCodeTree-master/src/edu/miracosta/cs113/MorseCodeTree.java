package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {
    BinaryTree<Character> tree;
	Scanner input;

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
    public MorseCodeTree() {
		try {
			input = new Scanner(new File("decode.txt"));
			String line, morseCode;
			char letter, currentSymbol;
			BinaryTree<Character> currentTree = new BinaryTree<Character>();
			tree = new BinaryTree<Character>(null, new BinaryTree<Character>(), new BinaryTree<Character>());
			currentTree = tree;
			
			while(input.hasNextLine()) 
			{
				currentTree = tree;
				line = input.nextLine();
				letter = line.charAt(0);
				morseCode = line.substring(2); 
				
				
				for(int i = 0; i < morseCode.length() - 1; i++) {
					currentSymbol = morseCode.charAt(i);
					if(currentSymbol == '*')
					{
						currentTree = currentTree.getLeftSubtree();
					}
					else if(currentSymbol == '-')
					{
						currentTree = currentTree.getRightSubtree();
					}
				}
				
				currentSymbol = morseCode.charAt(morseCode.length()-1);
				if(currentSymbol == '*')
				{
					currentTree.addLeft(letter);
				}
				else if(currentSymbol == '-')
				{
					currentTree.addRight(letter);
				}
			}	
		} catch(FileNotFoundException e) {
			System.out.println("ERORR: " + e.getMessage());
		}
			
	}
    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws IllegalArgumentException{
    	BinaryTree<Character> currentTree = tree;
        char currentSymbol;
        String currentCode, translation = "";
        String[] words = morseCode.split(" ");
        
        for(int j = 0; j < words.length ; j++) 
        {
        	currentCode = words[j];
        	currentTree = tree;
		    for(int i = 0; i < currentCode.length(); i++)
		    {
		    	
		        currentSymbol = currentCode.charAt(i);
		    	if(currentSymbol == '*') {
		        	currentTree = currentTree.getLeftSubtree();
		        }
		    	
		        else if(currentSymbol == '-') {
		        	currentTree = currentTree.getRightSubtree();
		        }
		    	
		        else {
		        	System.out.println("Invalid characters in Morse Code.");
		        	throw new IllegalArgumentException();
		        }
		    	
		 
		    }
		    translation += String.valueOf(currentTree.getData());
        }
	    return translation;
	   
    }
    
} // End of class MorseCodeTree