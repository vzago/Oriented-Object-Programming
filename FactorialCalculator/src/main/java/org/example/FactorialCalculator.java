package org.example;

/*
Modifications to Make:
Input from User: Modify the program to accept an integer input from the user instead of using a hard-coded value. Use Scanner for input.
Error Handling: Add error handling to ensure the program only accepts positive integers. Display an error message for invalid inputs.
Additional Functionality:
Add a function that calculates the sum of all integers from 1 to n (inclusive), where n is the input number. Implement this function iteratively.
Modify the main method to call this new function and display its result.
 */

import java.util.Scanner;

public class FactorialCalculator {

    // Recursive method to calculate factorial
    public static int factorialRecursive(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    // Iterative method to calculate factorial
    public static int factorialIterative(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    /*
    Method to calculate the iterative sum from 1 to N.
     */
    public static int sumIterative(int n){
        int sum = 0;
        for(int i = 0; i <= n; i++){
            sum = sum + i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which number do you want to calculate the factorial ? ");
        int number;
        //Try-catch to handle a non-positive input from user
        try{
            number = input.nextInt();
            if(number < 0) {
                throw new IllegalArgumentException("Number must be non-negative");
            }
            System.out.println("Factorial of " + number + " (Recursive): " + factorialRecursive(number));
            System.out.println("Factorial of " + number + " (Iterative): " + factorialIterative(number));
            //Calling the method sum iterative and printing his result;
            System.out.println("Sum of " + number + " (Iterative): " + sumIterative(number));
        }catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}