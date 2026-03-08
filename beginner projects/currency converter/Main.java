import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hardcoded exchange rates for a beginner project.
        final double usdToEur = 0.92;
        final double usdToGbp = 0.79;

        System.out.println("=== Currency Converter ===");
        System.out.println("1) USD -> EUR");
        System.out.println("2) EUR -> USD");
        System.out.println("3) USD -> GBP");
        System.out.println("4) GBP -> USD");
        System.out.println("5) EUR -> GBP");
        System.out.println("6) GBP -> EUR");
        System.out.print("Choose a conversion (1-6): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number from 1 to 6.");
            scanner.close();
            return;
        }

        int choice = scanner.nextInt();

        System.out.print("Enter amount to convert: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount. Please enter a numeric value.");
            scanner.close();
            return;
        }

        double amount = scanner.nextDouble();
        double result;
        String fromCurrency;
        String toCurrency;
        String fromSymbol;
        String toSymbol;

        switch (choice) {
            case 1:
                result = amount * usdToEur;
                fromCurrency = "USD";
                toCurrency = "EUR";
                fromSymbol = "$";
                toSymbol = "€";
                break;
            case 2:
                result = amount / usdToEur;
                fromCurrency = "EUR";
                toCurrency = "USD";
                fromSymbol = "€";
                toSymbol = "$";
                break;
            case 3:
                result = amount * usdToGbp;
                fromCurrency = "USD";
                toCurrency = "GBP";
                fromSymbol = "$";
                toSymbol = "£";
                break;
            case 4:
                result = amount / usdToGbp;
                fromCurrency = "GBP";
                toCurrency = "USD";
                fromSymbol = "£";
                toSymbol = "$";
                break;
            case 5:
                result = amount / usdToEur * usdToGbp;
                fromCurrency = "EUR";
                toCurrency = "GBP";
                fromSymbol = "€";
                toSymbol = "£";
                break;
            case 6:
                result = amount / usdToGbp * usdToEur;
                fromCurrency = "GBP";
                toCurrency = "EUR";
                fromSymbol = "£";
                toSymbol = "€";
                break;
            default:
                System.out.println("Invalid choice. Please run again and choose 1-6.");
                scanner.close();
                return;
        }

        System.out.printf("%s%.2f %s = %s%.2f %s%n",
                fromSymbol, amount, fromCurrency, toSymbol, result, toCurrency);

        scanner.close();
    }
}
