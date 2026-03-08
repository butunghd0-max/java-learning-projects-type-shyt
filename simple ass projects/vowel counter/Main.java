import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine().trim().toLowerCase();

        if (sentence.isEmpty()) {
            System.out.println("Input empty.");
            scanner.close();
            return;
        }

        int vowelCount = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                vowelCount++;
        }

        System.out.println("Total vowels: " + vowelCount);
        scanner.close();
    }
}
