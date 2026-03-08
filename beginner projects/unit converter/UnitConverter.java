import java.util.Scanner;

public class UnitConverter {
    private static final double INCH_TO_CM = 2.54;
    private static final double KG_TO_LBS = 2.204;
    private static final double JOULE_TO_CAL = 0.239;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Length (Inches <-> cm)");
        System.out.println("2. Weight (kg <-> lbs)");
        System.out.println("3. Energy (Joules <-> Calories)");
        System.out.print("Select a category (1-3): ");
        int category = scanner.nextInt();

        System.out.print("Convert Forward (1) or Reverse (2)? ");
        int direction = scanner.nextInt();

        System.out.print("Enter the value you wish to convert: ");
        double inputValue = scanner.nextDouble();

        double factor;
        String fromUnit;
        String toUnit;

        switch (category) {
            case 1:
                factor = INCH_TO_CM;
                fromUnit = "Inches";
                toUnit = "cm";
                break;
            case 2:
                factor = KG_TO_LBS;
                fromUnit = "kg";
                toUnit = "lbs";
                break;
            case 3:
                factor = JOULE_TO_CAL;
                fromUnit = "Joules";
                toUnit = "Calories";
                break;
            default:
                System.out.println("Invalid category selected.");
                scanner.close();
                return;
        }

        double result;
        if (direction == 1) {
            result = inputValue * factor;
        } else if (direction == 2) {
            result = inputValue / factor;
            String temp = fromUnit;
            fromUnit = toUnit;
            toUnit = temp;
        } else {
            System.out.println("Invalid direction selected.");
            scanner.close();
            return;
        }

        System.out.print("Use scientific notation for output? (y/n): ");
        String sciChoice = scanner.next();
        boolean useScientific = sciChoice.equalsIgnoreCase("y");

        if (useScientific) {
            System.out.printf("%.2e %s is %.2e %s%n", inputValue, fromUnit, result, toUnit);
        } else {
            System.out.printf("%.2f %s is %.2f %s%n", inputValue, fromUnit, result, toUnit);
        }

        scanner.close();
    }
}
