package org.example;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    // Variables to represent the game status
    private int number_of_guesses;
    private int target_number;
    private boolean feedback;

    // Constructor to initialize the game
    public GuessingGame() {
        this.number_of_guesses = 0; // Initialize the number of guesses to zero
        Random number = new Random();
        this.target_number = number.nextInt(101); // Select a random number between 0 and 100
    }

    // Method to set up the game
    public void setup() {
        System.out.println("==========WELCOME TO THE GUESSING GAME==========");
        System.out.println("Try to guess a number between 0 and 100");
        Scanner input = new Scanner(System.in);
        int user_input;

        // Loop until the user guesses the correct number
        while (true) {
            user_input = getUserInput(input); // Get the user's guess
            if (feedback(user_input)) { // Provide feedback and check if the guess is correct
                break; // Exit the loop if the guess is correct
            }
            System.out.println("Total Guesses: " + getNumber_of_guesses()); // Display the number of guesses
            System.out.println("===========================================");
        }

        System.out.println("Game Over! The correct number was: " + getTarget_number());
    }

    // Method to get user input
    private int getUserInput(Scanner input) {
        int guess = -1;
        while (true) {
            System.out.print("Enter your guess: ");
            // Check if the input is a valid integer
            if (input.hasNextInt()) {
                guess = input.nextInt();
                break; // Exit the loop if a valid integer is entered
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                input.next(); // Discard the invalid input
            }
        }
        return guess;
    }

    // Method to accept a guess
    public void acceptGuess(int guess) {
        // Validate the guess to ensure it's within the acceptable range
        try {
            if (guess < 0 || guess > 100) {
                throw new IllegalArgumentException("Number must be in the range between 0 and 100");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to provide feedback on the user's guess
    public boolean feedback(int guess) {
        acceptGuess(guess); // Validate the guess

        // Provide feedback based on the user's guess
        if (guess < this.getTarget_number()) {
            System.out.println("The target number is higher than this.");
            setFeedback(false);
        } else if (guess > this.getTarget_number()) {
            System.out.println("The target number is lower than this.");
            setFeedback(false);
        } else {
            System.out.println("Congratulations! You found the correct number.");
            setFeedback(true);
        }

        countGuess(); // Increment the guess count
        return isFeedback(); // Return whether the guess was correct
    }

    // Method to count the number of guesses
    public void countGuess() {
        setNumber_of_guesses(getNumber_of_guesses() + 1); // Increment the guess count
    }

    // Getters and Setters for the variables
    public int getNumber_of_guesses() {
        return number_of_guesses;
    }

    public void setNumber_of_guesses(int number_of_guesses) {
        this.number_of_guesses = number_of_guesses;
    }

    public int getTarget_number() {
        return target_number;
    }

    public void setTarget_number(int target_number) {
        this.target_number = target_number;
    }

    public boolean isFeedback() {
        return feedback;
    }

    public void setFeedback(boolean feedback) {
        this.feedback = feedback;
    }

    // Main method to instantiate and start the game
    public static void main(String[] args) {
        GuessingGame game = new GuessingGame(); // Create a new game instance
        game.setup(); // Set up and start the game
    }
}
