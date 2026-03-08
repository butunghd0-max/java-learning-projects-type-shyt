import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] blacklistedPasswords = {
                "123456", "password", "admin", "qwerty", "letmein", "12345678"
        };

        System.out.print("Enter a password to evaluate: ");
        String password = scanner.nextLine();

        if (isBlacklisted(password, blacklistedPasswords)) {
            System.out.println("Rejected: This password is too common and unsafe.");
            scanner.close();
            return;
        }

        int score = 0;

        if (password.length() >= 8) {
            score++;
        }

        boolean hasDigit = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else {
                hasSpecial = true;
            }
        }

        if (hasDigit) {
            score++;
        }
        if (hasUpper && hasLower) {
            score++;
        }
        if (hasSpecial) {
            score++;
        }

        String strength;
        if (score < 2) {
            strength = "Weak";
        } else if (score == 4) {
            strength = "Strong";
        } else {
            strength = "Medium";
        }

        System.out.println("Score: " + score + "/4");
        System.out.println("Strength: " + strength);

        scanner.close();
    }

    private static boolean isBlacklisted(String password, String[] blacklist) {
        for (String common : blacklist) {
            if (password.equalsIgnoreCase(common)) {
                return true;
            }
        }
        return false;
    }
}
