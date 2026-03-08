import java.util.Scanner;

public class StudyTimer {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Study Timer ===");
            System.out.println("1) Custom countdown");
            System.out.println("2) Pomodoro (4 x 25/5 + long break)");
            System.out.print("Choose a mode (1 or 2): ");

            int mode = readPositiveInt(scanner);
            if (mode == 2) {
                runPomodoro();
            } else {
                System.out.print("How many minutes would you like to study? ");
                int minutes = readPositiveInt(scanner);
                runCountdown(minutes * 60, "Study Session");
                announce("TIME IS UP! Take a break!");
            }
        }
    }

    private static int readPositiveInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Keep prompting.
            }
            System.out.print("Enter a positive whole number: ");
        }
    }

    private static void runPomodoro() {
        final int workMinutes = 25;
        final int shortBreakMinutes = 5;
        final int longBreakMinutes = 15;

        for (int cycle = 1; cycle <= 4; cycle++) {
            System.out.printf("%nCycle %d/4 - Focus time%n", cycle);
            runCountdown(workMinutes * 60, "Focus");
            announce("Focus block complete.");

            if (cycle < 4) {
                System.out.printf("%nShort break (%d minutes)%n", shortBreakMinutes);
                runCountdown(shortBreakMinutes * 60, "Short Break");
                announce("Break over. Back to focus!");
            }
        }

        System.out.printf("%nLong break (%d minutes)%n", longBreakMinutes);
        runCountdown(longBreakMinutes * 60, "Long Break");
        announce("Pomodoro set complete. Great work!");
    }

    private static void runCountdown(int totalSeconds, String label) {
        while (totalSeconds > 0) {
            int m = totalSeconds / 60;
            int s = totalSeconds % 60;
            System.out.printf("%s %02d:%02d\r", label, m, s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\nTimer interrupted.");
                return;
            }
            totalSeconds--;
        }

        System.out.printf("%s 00:00%n", label);
    }

    private static void announce(String message) {
        System.out.print("\u0007");
        System.out.println(message);
    }
}
