import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a whole number: ");
        int num = scanner.nextInt();
        if (num % 2 == 0) {
            System.out.println("That number is Even!");
        } else {
            System.out.println("That number is Odd!");
        }
        scanner.close();
    }
}