/* 
* Author: Austin Garrison
* Lab: 1
* Problem statement:  Write a program that prints the numbers from 1 to 100, but for multiples of 3 print “Fizz” instead of the number and for multiples of 5 print “Buzz”. For numbers that are multiples of both three and five print “FizzBuzz”.
*
*/

class Main {
  public static void main(String[] args) {

    for (int i = 1; i <= 100; i++) {
      if (i % 15 == 0) {
        System.out.println("FizzBuzz");
      }

      else if (i % 3 == 0) {
        System.out.println("Fizz");
      }

      else if (i % 5 == 0) {
        System.out.println("Buzz");
      }

      else {
        System.out.println(i);
      }
    }
    
  }
}