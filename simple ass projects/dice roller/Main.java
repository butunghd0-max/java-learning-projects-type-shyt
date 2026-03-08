import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Dice Roller ===");
        int sides = readSides(scanner);

        String playAgain = "y";
        while (playAgain.equalsIgnoreCase("y")) {
            System.out.println("\nPress Enter to roll the die!");
            scanner.nextLine();

            int roll = (int) (Math.random() * sides) + 1;
            System.out.println("You rolled a: " + roll);

            System.out.print("Roll again? (y/n): ");
            playAgain = scanner.nextLine().trim();
        }

        System.out.println("Thanks for playing.");
        scanner.close();
    }

    private static int readSides(Scanner scanner) {
        while (true) {
            System.out.print("How many sides does the die have? ");
            String input = scanner.nextLine().trim();

            try {
                int sides = Integer.parseInt(input);
                if (sides > 1) {
                    return sides;
                }
                System.out.println("Please enter a number greater than 1.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
