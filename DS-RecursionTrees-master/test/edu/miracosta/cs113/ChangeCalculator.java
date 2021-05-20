package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {
    public static PrintWriter outputFile;
	public static ArrayList<String> combinations = new ArrayList<String>();
	public static int numCombinations = 0;

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        combinations.clear();
    	numCombinations = 0;
    	combinationCalculator(cents, 0, 0, 0, cents);
        return numCombinations;    	
    }

    public static void combinationCalculator (int total, int numQuarters, int numDimes,int numNickels, int numPennies) {
        int quarter = 25, dime = 10, nickel = 5, penny = 1;

        String output = "[" + numQuarters + ", " + numDimes + ", " + numNickels + ", " + numPennies + "]\n";
        if(!combinations.contains(output)) {
    		combinations.add(output);
    		numCombinations++;
    	}

        if(numPennies >= 5)
            combinationCalculator(total, numQuarters, numDimes, numNickels + 1, numPennies - 5);
    	
        if(numPennies >= 10)
            combinationCalculator(total, numQuarters, numDimes + 1, numNickels, numPennies - 10);
    	
        if(numPennies >= 25)
            combinationCalculator(total, numQuarters + 1, numDimes, numNickels, numPennies - 25);    	
    
        if(numQuarters*quarter + numDimes*dime + numNickels*nickel + numPennies*penny != total) {
    		return;
    	}
    }


    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        try {
            outputFile = new PrintWriter(new File("src/edu.miracosta.cs113/change/CoinCombinations.txt"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        calculateChange(cents);
        outputFile.println(combinations);
        outputFile.close();
    }

} // End of class ChangeCalculator