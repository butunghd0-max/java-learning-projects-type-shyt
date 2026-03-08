import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double bill = readNonNegativeDouble(scanner, "What is the total bill? $");
        double tipPercent = readNonNegativeDouble(scanner, "What percentage would you like to tip? (e.g., 15, 20, 25): ");
        int people = readPositiveInt(scanner, "How many people are in your party? ");

        double tipAmount = bill * (tipPercent / 100.0);
        double totalBill = bill + tipAmount;
        double perPerson = totalBill / people;

        System.out.printf("%nTip Amount: $%.2f%n", tipAmount);
        System.out.printf("Total Bill: $%.2f%n", totalBill);
        System.out.printf("Each Person Pays: $%.2f%n", perPerson);

        scanner.close();
    }

    private static double readNonNegativeDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                if (value >= 0) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.println("Please enter a valid non-negative number.");
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.println("Please enter a valid whole number greater than 0.");
        }
    }
}
