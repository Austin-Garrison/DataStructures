package edu.miracosta.cs113;

import java.util.Random;
import java.util.Scanner;

import model.AssistantJack;
import model.Theory;

public class DetectiveJill {
	public static void main(String[] args) {
        Theory answer;
        int answerSet, murder = 1, weapon = 1, location = 1;
        Scanner keyboard = new Scanner(System.in);

        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        AssistantJack jack;
        jack = new AssistantJack(answerSet);
        int solution = jack.checkAnswer(weapon, location, murder);
        

        while (solution != 0) {
        	if (solution == 1)
        		weapon++;
        	else if (solution == 2)
        		location++;
        	else if (solution == 3)
        		murder++;
        	
            solution = jack.checkAnswer(weapon, location, murder);
            System.out.println("Solution: " + solution);
        };

        answer = new Theory(weapon, location, murder);

        // OUTPUT
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well be called Batman!");
        }

    }

}


