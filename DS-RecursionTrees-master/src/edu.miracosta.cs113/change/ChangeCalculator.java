import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        // TODO:
        // Implement a recursive solution following the given documentation.

        numCombinations = combinationCalculator(cents, 0, 0, 0, 0, 0);
        return numCombinations;

    	//Creating a string for combination
    	
    	// if(numPennies >= 5)
        //     calculateChange(total, numQuarters, numDimes, numNickels + 1, numPennies - 5);
    	// if(numPennies >= 10)
        //     calculateChange(total, numQuarters, numDimes + 1, numNickels, numPennies - 10);
    	// if(numPennies >= 25)
        //     calculateChange(total, numQuarters + 1, numDimes, numNickels, numPennies - 25);
    }

    public static int combinationCalculator (int total, int count, int numQuarters, int numDimes,int numNickels, int numPennies) {
        int quarter = 25, dime = 10, nickel = 5, penny = 1;
        if (count == 0) {
            numQuarters = total % 25;
            total -= numQuarters*quarter;
    	    numDimes = total % 10;
            total -= numDimes*dime;
            numNickels = total % 5;
            total -= numNickels*nickel;
            numPennies = total;
        }

        else {
            if (numQuarters > 0) {
                numQuarters--;
                numDimes += 2;
                numNickels++;
            }
            else if (numDimes > 0) {
                numDimes--;
                numNickels += 2;
            }
            else if (numNickels > 0){
                numNickels--;
                numPennies += 5;
            }
        }
        String output = "[" + numQuarters + ", " + numDimes + ", " + numNickels + ", " + numPennies + "]";
    	
    	if(!combinations.contains(output)) {
    		combinations.add(output);
    		numCombinations++;
    	}

        //numQuarters*quarter + numDimes*dime + numNickels*nickel + numPennies*penny == total
        if(numPennies == total) {
    		return numCombinations;
    	}
        else {
            return combinationCalculator(total, count++, numQuarters, numDimes, numNickels, numPennies);
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
     * @throws FileNotFoundException
     */
    public static void printCombinationsToFile(int cents) throws FileNotFoundException {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.
        outputFile = new PrintWriter(new File("CoinCombinations.txt"));
        calculateChange(cents);
    	outputFile.println(combinations);
    }

} // End of class ChangeCalculator