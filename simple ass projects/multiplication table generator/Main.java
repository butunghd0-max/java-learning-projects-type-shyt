import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many rows/columns should the table have? ");
        int size = scanner.nextInt();
        if (size <= 0) {
            System.out.println("Please enter a positive integer greater than 0");
            scanner.close();
            return;
        }
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                int product = i * j;

                if (i == j) {
                    System.out.print("[" + product + "]\t");
                } else {
                    System.out.print(product + "\t");
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}