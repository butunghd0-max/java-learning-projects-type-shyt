import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String secretPassword = "OpenSesame2026";
        final int maxAttempts = 3;

        Scanner scanner = new Scanner(System.in);
        boolean granted = false;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.print("Please enter the system password: ");
            String userInput = scanner.nextLine();

            if (secretPassword.equals(userInput)) {
                System.out.println("Access Granted. Welcome bro.");
                granted = true;
                break;
            } else {
                int remaining = maxAttempts - attempt;
                if (remaining > 0) {
                    System.out.println("Access Denied. " + remaining + " attempt(s) remaining.");
                }
            }
        }

        if (!granted) {
            System.out.println("Access Denied. Intruder Alert! System locked.");
        }

        scanner.close();
    }
}
