import java.util.Scanner;

public class ClawMachineDropTimer {
    private static final double SAFETY_BUFFER_SECONDS = 0.1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Claw Machine Drop Timer ===");
        System.out.println("Choose input mode:");
        System.out.println("1) Direct distance");
        System.out.println("2) X/Y coordinates");

        int mode = readIntInRange(scanner, "Enter 1 or 2: ", 1, 2);
        double speedCmPerSec = readPositiveDouble(scanner, "Motor speed (cm/s): ");

        double distanceCm;
        if (mode == 1) {
            distanceCm = readPositiveDouble(scanner, "Distance to target (cm): ");
        } else {
            double x = readDouble(scanner, "Target X offset from home (cm): ");
            double y = readDouble(scanner, "Target Y offset from home (cm): ");
            distanceCm = Math.sqrt((x * x) + (y * y));
            System.out.printf("Calculated diagonal distance: %.2f cm%n", distanceCm);
        }

        double travelSeconds = distanceCm / speedCmPerSec;
        double totalDelaySeconds = travelSeconds + SAFETY_BUFFER_SECONDS;
        long delayMs = Math.round(totalDelaySeconds * 1000);

        System.out.printf("%nTravel time: %.3f s%n", travelSeconds);
        System.out.printf("Safety buffer: %.3f s%n", SAFETY_BUFFER_SECONDS);
        System.out.printf("Total delay: %.3f s (%d ms)%n", totalDelaySeconds, delayMs);

        System.out.println("\nMoving to target...");
        try {
            Thread.sleep(delayMs);
            System.out.println("TARGET REACHED: Dropping Claw NOW!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Movement interrupted before drop.");
        }
    }

    private static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.printf("Please enter a whole number between %d and %d.%n", min, max);
        }
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            double value = readDouble(scanner, prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a value greater than 0.");
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            scanner.next();
            System.out.println("Please enter a valid number.");
        }
    }
}
