/**
 * A small game in which the user guesses a number between 1 and some defined constant. 
 */

/**
 * @author Sean
 * 
 */

import java.util.Scanner;  //import the scanner  

public class GuessingGame {
	
	//constant that establishes the upper bounds for the game.
	private static int max = 100; 
	
	//variable array that tracks game statistics. 
	//{total games, total guesses, best game}
	private static int[] gameStats = {0,0,10000}; 
	
	// Main method
	public static void main(String[] args) {  	//Executed when ran 
		boolean play = true;
		introduceGame();  						//introduces the game
		while(play) {							//game continues until user says no
			playGame();							//starts the game
			play = askPlayAgain();				//asks user if they want to play again
		}
		reportStats();							//reports the stats
	}
	
	public static void introduceGame() { 
		/* 
		 * Outputs text to console explaining the game. 
		 * Text chosen based on program specifications. 
		 */
		System.out.println("This program allows you to play a guessing game.");

		System.out.println("I will think of a number between 1 and"); 

		System.out.println(max + " and will allow you to guess until");

		System.out.println("you get it. For each guess, I will tell you");

		System.out.println("whether the right answer is higher or lower");

		System.out.println("than your guess.");
	}
	
	public static void playGame() { 
		/*
		 * Executes all the logic for a single game.  
		 * Loops through guesses until correct then updates the stats.
		 */ 
		//established what number is guessed
		int target = selectTarget();
		int guess = 0;		//initializes guess at zero so it never equals target to start
		int count = 1; 		// sets the count to one, this tracks how many guesses have been made
		while(guess != target) { 
			//established new scanner object
			Scanner userGuess = new Scanner(System.in);
			try {
				System.out.print("Your Guess? ");
				guess = userGuess.nextInt();
			} catch (Exception e) {
				System.out.println("Sorry that response is not valid...");
				userGuess.reset();
				continue;
			}
			// Switch case does not seem to be usable here as the cases are mostly unrelated
			if (guess > target ) {    
				count++;
				System.out.println("It's lower."); 
			} else if (guess < target) {
				count++;
				System.out.println("It's higher.");
			} else if (count == 1){                  //only evaluates when guess is equal to target. 
				System.out.println("You got it right in 1 guess"); 
			} else {
				System.out.println("You got it right in " + count + " guesses");
			}
		}
		//iterate the game count by one
		gameStats[0] = gameStats[0] + 1;
		//adds the amount of guesses to the total guesses
		gameStats[1] = gameStats[1] + count;
		//replaces best game score with count if and only if count is lower.
		gameStats[2] = gameStats[2] > count ? count: gameStats[2];
	}
	
	private static void reportStats() {
		//reports the stats from the game
		
		//
		double average = (double) gameStats[1] / (double) gameStats[0];
		
		//Print statements
		System.out.println("Overall results:");
		System.out.println("    total games   = " + gameStats[0]);
		System.out.println("    total guesses = " + gameStats[1]);
		System.out.println("    guesses/game  = " + average);
		System.out.println("    best game     = " + gameStats[2]);
	}
	
	private static int selectTarget() {
		//returns an integer between 1 and max
		// Math.random() returns random double between 0 and 1
		int t = (int) (Math.random() * (max - 1)) + 1; 
		return t;
	}
	
	private static boolean askPlayAgain() {
		//Asks the player if they would like to play another game.
		
		String response;  //defines the variable response
		Scanner userInput = new Scanner(System.in);  //establish new scanner object
		while(true) {							//while loop continues until a proper response is received. 
			System.out.print("Would you like to play again? ");
			try {
				//records the user input
				response = userInput.nextLine();    
				//ensures response is the upper case of the first character
				char rupper = Character.toUpperCase(response.charAt(0));	
				//switch case statement returns based boolean based on input
				switch(rupper) {								            
					case 'Y':
						return true;
					case 'N': 
						return false;
					default: 
						System.out.println("Sorry that response is not valid...");
				}
			} catch (Exception e) {
				System.out.println("Sorry that response is not valid...");
				userInput.reset();
			}
		}
	}

}
