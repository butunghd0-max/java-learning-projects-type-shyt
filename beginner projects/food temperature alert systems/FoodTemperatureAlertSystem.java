public class FoodTemperatureAlertSystem {
    private static final double SAFE_MIN_TEMP_C = 0.0;
    private static final double SAFE_MAX_TEMP_C = 4.0;
    private static final double DANGER_ZONE_MIN_C = 4.0;
    private static final double DANGER_ZONE_MAX_C = 60.0;

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter number of storage units to monitor: ");

        int unitCount = scanner.nextInt();
        if (unitCount <= 0) {
            System.out.println("No units to monitor. Exiting.");
            scanner.close();
            return;
        }

        double[] temperatures = new double[unitCount];
        for (int i = 0; i < unitCount; i++) {
            System.out.print("Enter temperature for Unit " + (i + 1) + " (°C): ");
            temperatures[i] = scanner.nextDouble();
        }

        scanner.close();
        analyzeTemperatures(temperatures);
    }

    private static void analyzeTemperatures(double[] temperatures) {
        int safeCount = 0;
        int riskCount = 0;
        double total = 0.0;

        System.out.println("\n--- Monitoring Report ---");

        for (int i = 0; i < temperatures.length; i++) {
            double temp = temperatures[i];
            total += temp;

            boolean isSafe = temp >= SAFE_MIN_TEMP_C && temp <= SAFE_MAX_TEMP_C;
            if (isSafe) {
                safeCount++;
                System.out.println("Unit " + (i + 1) + ": " + temp + "°C (SAFE)");
            } else {
                riskCount++;
                System.out.println("ALERT: Unit " + (i + 1) + " is at " + temp + "°C. Out of safe storage range!");

                if (temp >= DANGER_ZONE_MIN_C && temp <= DANGER_ZONE_MAX_C) {
                    System.out.println("  -> Potential Spoilage: Temperature is in food danger zone (4°C to 60°C).");
                }
            }
        }

        double average = total / temperatures.length;

        System.out.println("\n--- Summary ---");
        System.out.println("Total units monitored: " + temperatures.length);
        System.out.println("Safe units: " + safeCount);
        System.out.println("At-risk units: " + riskCount);
        System.out.printf("Average temperature: %.2f°C%n", average);
    }
}
