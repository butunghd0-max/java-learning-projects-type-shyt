import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or phrase: ");
        String originalInput = scanner.nextLine();

        String original = originalInput.toLowerCase().replace(" ", "");
        StringBuilder reversed = new StringBuilder();

        for (int i = original.length() - 1; i >= 0; i--) {
            reversed.append(original.charAt(i));
        }

        if (original.equals(reversed.toString())) {
            System.out.println("\"" + originalInput + "\" is a palindrome.");
        } else {
            System.out.println("\"" + originalInput + "\" is not a palindrome.");
        }

        scanner.close();
    }
}
