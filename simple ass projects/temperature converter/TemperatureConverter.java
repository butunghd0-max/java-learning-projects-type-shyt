import java.util.Scanner;
import java.text.DecimalFormat;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat tempFormat = new DecimalFormat("0.##");

        System.out.println("Temperature Converter");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = scanner.nextInt();
        double inputTemp;
        double result;

        switch (choice) {
            case 1:
                System.out.print("Enter temperature in Celsius: ");
                inputTemp = scanner.nextDouble();
                result = (inputTemp * 1.8) + 32;
                System.out.printf("%s°C = %s°F%n", tempFormat.format(inputTemp), tempFormat.format(result));
                break;
            case 2:
                System.out.print("Enter temperature in Fahrenheit: ");
                inputTemp = scanner.nextDouble();
                result = (inputTemp - 32) / 1.8;
                System.out.printf("%s°F = %s°C%n", tempFormat.format(inputTemp), tempFormat.format(result));
                break;
            default:
                System.out.println("Invalid choice. Please run the program again and enter 1 or 2.");
        }

        scanner.close();
    }
}
