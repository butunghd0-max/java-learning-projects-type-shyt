import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word or sentence: ");
        String original = scanner.nextLine();
        String reversed = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed = reversed + original.charAt(i);
        }
        StringBuilder optimized = new StringBuilder();
        for (int i = original.length() - 1; i >= 0; i--) {
            optimized.append(original.charAt(i));
        }
        System.out.println("Original: " + original);
        System.out.println("Reversed (String): " + reversed);
        System.out.println("Reversed (StringBuilder): " + optimized);

        scanner.close();
    }
}
