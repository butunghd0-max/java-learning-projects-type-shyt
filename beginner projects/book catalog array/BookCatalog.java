import java.util.Scanner;

public class BookCatalog {
    private static final int CAPACITY = 20;

    public static void main(String[] args) {
        String[] titles = new String[CAPACITY];
        String[] authors = new String[CAPACITY];
        int[] ratings = new int[CAPACITY];
        int bookCount = 0;

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    if (bookCount >= CAPACITY) {
                        System.out.println("Catalog is full. Cannot add more books.\n");
                    } else {
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine().trim();

                        System.out.print("Enter author: ");
                        String author = scanner.nextLine().trim();

                        int rating = readRating(scanner);

                        titles[bookCount] = title;
                        authors[bookCount] = author;
                        ratings[bookCount] = rating;
                        bookCount++;

                        System.out.println("Book added successfully.\n");
                    }
                    break;
                case 2:
                    viewCatalog(titles, authors, ratings, bookCount);
                    break;
                case 3:
                    searchByAuthor(scanner, titles, authors, ratings, bookCount);
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-4.\n");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== Book Catalog Menu ===");
        System.out.println("1. Add Book");
        System.out.println("2. View Catalog");
        System.out.println("3. Search by Author");
        System.out.println("4. Exit");
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static int readRating(Scanner scanner) {
        while (true) {
            int rating = readInt(scanner, "Enter rating (1-5): ");
            if (rating >= 1 && rating <= 5) {
                return rating;
            }
            System.out.println("Rating must be between 1 and 5.");
        }
    }

    private static void viewCatalog(String[] titles, String[] authors, int[] ratings, int bookCount) {
        if (bookCount == 0) {
            System.out.println("Catalog is empty.\n");
            return;
        }

        System.out.println("\n=== Catalog ===");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". " + titles[i] + " by " + authors[i] + " [" + ratings[i] + "/5]");
        }
        System.out.println();
    }

    private static void searchByAuthor(
            Scanner scanner,
            String[] titles,
            String[] authors,
            int[] ratings,
            int bookCount) {

        if (bookCount == 0) {
            System.out.println("Catalog is empty.\n");
            return;
        }

        System.out.print("Enter author name to search: ");
        String target = scanner.nextLine().trim();

        boolean found = false;
        System.out.println("\n=== Search Results ===");
        for (int i = 0; i < bookCount; i++) {
            if (authors[i].equalsIgnoreCase(target)) {
                System.out.println((i + 1) + ". " + titles[i] + " by " + authors[i] + " [" + ratings[i] + "/5]");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found by that author.");
        }
        System.out.println();
    }
}
