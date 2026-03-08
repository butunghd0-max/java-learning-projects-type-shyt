import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    private static final DecimalFormat OUTPUT_FORMAT = new DecimalFormat("0.###############");

    private static String formatNumber(double value) {
        return OUTPUT_FORMAT.format(value);
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double num1 = reader.nextDouble();
        System.out.print("Choose operator (+, -, *, /): ");
        String op = reader.next();
        System.out.print("Enter second number: ");
        double num2 = reader.nextDouble();
        switch (op) {
            case "+":
                System.out.println("Result: " + formatNumber(num1 + num2));
                break;
            case "-":
                System.out.println("Result: " + formatNumber(num1 - num2));
                break;
            case "*":
                System.out.println("Result: " + formatNumber(num1 * num2));
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("Error: Cannot divide by zero.");
                } else {
                    System.out.println("Result: " + formatNumber(num1 / num2));
                }
                break;
            default:
                System.out.println("Error: Invalid operator.");
        }
        reader.close();
    }
}
