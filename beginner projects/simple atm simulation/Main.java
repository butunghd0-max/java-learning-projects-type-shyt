import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double balance = 1000.00;
        final int pin = 1234;
        final int maxPinAttempts = 3;

        System.out.println("=== ATM Simulator ===");

        boolean authenticated = false;
        for (int attempt = 1; attempt <= maxPinAttempts; attempt++) {
            System.out.print("Enter your 4-digit PIN: ");

            try {
                int enteredPin = scanner.nextInt();
                if (enteredPin == pin) {
                    authenticated = true;
                    break;
                }
                System.out.println("Incorrect PIN.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid PIN input. Numbers only.");
                scanner.next();
            }

            int remaining = maxPinAttempts - attempt;
            if (remaining > 0) {
                System.out.println("Attempts remaining: " + remaining);
            }
        }

        if (!authenticated) {
            System.out.println("Too many failed attempts. Session ended.");
            scanner.close();
            return;
        }

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Selection: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid menu choice. Enter 1-4.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.printf("Current Balance: $%.2f%n", balance);
                    break;
                case 2:
                    System.out.print("How much would you like to deposit? $");
                    try {
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount <= 0) {
                            System.out.println("Deposit amount must be greater than zero.");
                            break;
                        }
                        balance += depositAmount;
                        System.out.printf("Deposit successful. New Balance: $%.2f%n", balance);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid amount.");
                        scanner.next();
                    }
                    break;
                case 3:
                    System.out.print("How much would you like to withdraw? $");
                    try {
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAmount <= 0) {
                            System.out.println("Withdrawal amount must be greater than zero.");
                            break;
                        }

                        if (withdrawAmount <= balance) {
                            balance -= withdrawAmount;
                            System.out.printf("Withdrawal successful. New Balance: $%.2f%n", balance);
                        } else {
                            System.out.println("Error: Insufficient Funds.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid amount.");
                        scanner.next();
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid menu choice. Enter 1-4.");
            }
        }
    }
}
