import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int secret = (int) (Math.random() * 100) + 1;
        int guess = 0;
        int attempts = 0;
        final int maxAttempts = 10;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I picked a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " tries to guess it.");

        while (guess != secret && attempts < maxAttempts) {
            System.out.print("Enter your guess: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            guess = scanner.nextInt();
            attempts++;

            if (guess > secret) {
                System.out.println("Too high! Try again.");
            } else if (guess < secret) {
                System.out.println("Too low! Try again.");
            }
        }

        if (guess == secret) {
            System.out.println("Congratulations! You found it!");
            System.out.println("It took you " + attempts + " tries to win!");
        } else {
            System.out.println("You lose! You used all " + maxAttempts + " tries.");
            System.out.println("The secret number was " + secret + ".");
        }

        scanner.close();
    }
}