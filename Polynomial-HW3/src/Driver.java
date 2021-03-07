import java.util.LinkedList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		String firstPolynomial = "";
		String secondPolynomial = "";
		String polynomial1 = "", polynomial2 = "";
		
		LinkedList<Term> firstPolynomialList = new LinkedList<Term>();
		LinkedList<Term> secondPolynomialList = new LinkedList<Term>();
		
		int choice;
		
		Scanner keyboard = new Scanner(System.in);
		
		do {
		System.out.println("~~Polynomial Calculator~~");
		System.out.println("Enter (1) to input the first polynomial"
				+ "\nEnter (2) to input the second polynomial"
				+ "\nEnter (3) to display the results"
				+ "\nEnter (4) to CLEAR first polynomial"
				+ "\nEnter (5) to CLEAR second polynomial"
				+ "\nEnter (6) to exit");
		
		choice = keyboard.nextInt();
		keyboard.nextLine();
		
		switch (choice) {
			case 1:
				System.out.println("\nEnter terms with exponents in descending order, include coefficients of 1.");
				firstPolynomial = keyboard.nextLine();
				polynomial1 += firstPolynomial + " + ";
				Polynomial.split(firstPolynomial, firstPolynomialList);
				break;
			
			case 2:
				System.out.println("\nEnter terms with exponents in descending order, include coefficients of 1.");
				secondPolynomial = keyboard.nextLine();
				polynomial2 += secondPolynomial + " + ";
				Polynomial.split(secondPolynomial, secondPolynomialList);
				break;
			
			case 3:
				if (firstPolynomial != "" && secondPolynomial != "") {
					Polynomial.add(firstPolynomialList, secondPolynomialList);
					System.out.println("\n(" + polynomial1 + "0) + (" + polynomial2 + "0) = " + Polynomial.print());
				}
				else
					System.out.println("Please enter two polynomials to add.");
				break;
			
			case 4: 
				firstPolynomialList.clear();
				firstPolynomial = "";
				polynomial1 = "";
				System.out.println("First polynomial has been cleared.");
				break;
				
			case 5: 
				secondPolynomialList.clear();
				secondPolynomial = "";
				polynomial2 = "";
				System.out.println("Second polynomial has been cleared.");
				break;
				
			case 6: 
				System.exit(0);
				break;
			
			default:
				System.out.println("Please choose a number between 1 - 6.\n");
		}
		
		System.out.println();
		}while (choice != 6);
		
		keyboard.close();
	}

}
