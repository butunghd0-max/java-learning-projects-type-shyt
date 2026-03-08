import java.util.Scanner;

public class Main {
    private static final double BREADBOARD_CAUTION_AMPS = 0.5;
    private static final double BREADBOARD_MAX_AMPS = 1.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ohm's Law Calculator");
        System.out.println("1. Calculate Voltage (V)");
        System.out.println("2. Calculate Current (I)");
        System.out.println("3. Calculate Resistance (R)");
        System.out.print("Enter your choice (1-3): ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter Current (Amps): ");
                double currentForVoltage = scanner.nextDouble();
                System.out.print("Enter Resistance (Ohms): ");
                double resistanceForVoltage = scanner.nextDouble();

                double voltage = currentForVoltage * resistanceForVoltage;
                System.out.println("The calculated Voltage is: " + voltage + " Volts");
                printSafetyCheck(currentForVoltage);
                break;

            case 2:
                System.out.print("Enter Voltage (Volts): ");
                double voltageForCurrent = scanner.nextDouble();
                System.out.print("Enter Resistance (Ohms): ");
                double resistanceForCurrent = scanner.nextDouble();

                if (resistanceForCurrent == 0) {
                    System.out.println("Resistance cannot be 0 when calculating current.");
                    break;
                }

                double current = voltageForCurrent / resistanceForCurrent;
                System.out.println("The calculated Current is: " + current + " Amps");
                printSafetyCheck(current);
                break;

            case 3:
                System.out.print("Enter Voltage (Volts): ");
                double voltageForResistance = scanner.nextDouble();
                System.out.print("Enter Current (Amps): ");
                double currentForResistance = scanner.nextDouble();

                if (currentForResistance == 0) {
                    System.out.println("Current cannot be 0 when calculating resistance.");
                    break;
                }

                double resistance = voltageForResistance / currentForResistance;
                System.out.println("The calculated Resistance is: " + resistance + " Ohms");
                printSafetyCheck(currentForResistance);
                break;

            default:
                System.out.println("Invalid choice. Please run again and choose 1, 2, or 3.");
        }

        scanner.close();
    }

    private static void printSafetyCheck(double currentAmps) {
        System.out.println("Safety Check:");

        if (currentAmps > BREADBOARD_MAX_AMPS) {
            System.out.println("WARNING: " + currentAmps
                    + " A is above a typical solderless breadboard limit (~1.0 A).");
            System.out.println("Use a safer setup (thicker wiring, terminal blocks, or perfboard).");
        } else if (currentAmps > BREADBOARD_CAUTION_AMPS) {
            System.out.println("Caution: " + currentAmps
                    + " A is in a higher-current range for breadboards.");
            System.out.println("Keep runs short and monitor for heat.");
        } else {
            System.out.println("Current is in a generally breadboard-friendly range.");
        }
    }
}
