import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double kv = readPositiveDouble(scanner, "Enter motor KV rating (RPM/V): ");
        double voltage = readPositiveDouble(scanner, "Enter supply voltage (V): ");
        double efficiency = readBoundedDouble(scanner, "Enter efficiency (0.0 to 1.0, e.g. 0.85): ", 0.0, 1.0);
        double theoreticalRPM = kv * voltage;
        double realWorldRPM = theoreticalRPM * efficiency;
        System.out.println();
        System.out.printf("Theoretical max RPM: %d RPM%n", Math.round(theoreticalRPM));
        System.out.printf("Estimated real-world RPM: %d RPM%n", Math.round(realWorldRPM));

        if (theoreticalRPM > 30000 && realWorldRPM > 30000) {
            System.out.println("Warning: High-speed rotation detected. Ensure bearings are rated for this speed.");
        }
        System.out.println();
        System.out.print("Calculate linear speed from spool/wheel diameter? (y/n): ");
        String choice = scanner.next().trim().toLowerCase();

        if (choice.equals("y") || choice.equals("yes")) {
            double diameterMeters = readPositiveDouble(scanner, "Enter spool/wheel diameter in meters: ");
            double circumference = Math.PI * diameterMeters;
            double linearSpeedMetersPerSecond = (realWorldRPM / 60.0) * circumference;

            System.out.printf("Estimated linear speed: %.3f m/s%n", linearSpeedMetersPerSecond);
        }
        scanner.close();
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextDouble()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }
            double value = scanner.nextDouble();
            if (value <= 0) {
                System.out.println("Value must be greater than 0.");
                continue;
            }
            return value;
        }
    }

    private static double readBoundedDouble(Scanner scanner, String prompt, double minInclusive, double maxInclusive) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextDouble()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }
            double value = scanner.nextDouble();
            if (value < minInclusive || value > maxInclusive) {
                System.out.printf("Value must be between %.1f and %.1f.%n", minInclusive, maxInclusive);
                continue;
            }
            return value;
        }
    }
}
