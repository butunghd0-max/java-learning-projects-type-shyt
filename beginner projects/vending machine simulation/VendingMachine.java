import java.util.Scanner;

public class VendingMachine {

    private static final int MAX_PRODUCTS = 20;

    private static final String[] productCodes = new String[MAX_PRODUCTS];
    private static final String[] productNames = new String[MAX_PRODUCTS];
    private static final String[] productCategories = new String[MAX_PRODUCTS];
    private static final double[] productPrices = new double[MAX_PRODUCTS];
    private static final int[] productStock = new int[MAX_PRODUCTS];
    private static final int[] productSoldCount = new int[MAX_PRODUCTS];
    private static int productCount = 0;

    private static double totalRevenue = 0.0;
    private static int totalTransactions = 0;
    private static double currentInsertedMoney = 0.0;

    private static final double[] ACCEPTED_DENOMINATIONS = {
            0.05, 0.10, 0.25, 0.50, 1.00, 2.00, 5.00, 10.00, 20.00
    };

    private static final String ADMIN_PASSWORD = "admin123";

    public static void main(String[] args) {
        loadDefaultInventory();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("========================================");
        System.out.println("    WELCOME TO THE VENDING MACHINE");
        System.out.println("========================================");

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    browseProducts();
                    break;
                case "2":
                    purchaseFlow(scanner);
                    break;
                case "3":
                    running = adminLogin(scanner);
                    break;
                case "4":
                    if (currentInsertedMoney > 0) {
                        System.out.printf("Returning your $%.2f. Don't forget your change!%n", currentInsertedMoney);
                        currentInsertedMoney = 0.0;
                    }
                    System.out.println("Thank you for using the vending machine. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void loadDefaultInventory() {
        addProduct("A1", "Cola Classic", "Drinks", 1.50, 8);
        addProduct("A2", "Sparkling Water", "Drinks", 1.25, 10);
        addProduct("A3", "Orange Juice", "Drinks", 2.00, 6);
        addProduct("A4", "Iced Coffee", "Drinks", 2.75, 5);
        addProduct("B1", "Potato Chips", "Snacks", 1.75, 12);
        addProduct("B2", "Chocolate Bar", "Snacks", 1.50, 10);
        addProduct("B3", "Granola Bar", "Snacks", 2.25, 7);
        addProduct("B4", "Trail Mix", "Snacks", 2.50, 6);
        addProduct("C1", "Gummy Bears", "Candy", 1.00, 15);
        addProduct("C2", "Mints", "Candy", 0.75, 20);
        addProduct("C3", "Sour Strips", "Candy", 1.25, 10);
        addProduct("D1", "Protein Shake", "Health", 3.50, 4);
        addProduct("D2", "Energy Bar", "Health", 2.00, 8);
    }

    private static void addProduct(String code, String name, String category, double price, int stock) {
        if (productCount >= MAX_PRODUCTS) {
            System.out.println("Error: Product catalog is full.");
            return;
        }
        productCodes[productCount] = code;
        productNames[productCount] = name;
        productCategories[productCount] = category;
        productPrices[productCount] = price;
        productStock[productCount] = stock;
        productSoldCount[productCount] = 0;
        productCount++;
    }

    private static void printMainMenu() {
        System.out.println("----------------------------------------");
        System.out.println("  1. Browse Products");
        System.out.println("  2. Purchase Item");
        System.out.println("  3. Admin Panel");
        System.out.println("  4. Exit");
        System.out.println("----------------------------------------");
        if (currentInsertedMoney > 0) {
            System.out.printf("  [Credit: $%.2f]%n", currentInsertedMoney);
        }
        System.out.print("Select an option: ");
    }

    private static void browseProducts() {
        System.out.println("\n========== PRODUCT DISPLAY ==========");

        String currentCategory = "";
        for (int i = 0; i < productCount; i++) {
            if (!productCategories[i].equals(currentCategory)) {
                currentCategory = productCategories[i];
                System.out.println("\n--- " + currentCategory.toUpperCase() + " ---");
            }

            String stockLabel;
            if (productStock[i] == 0) {
                stockLabel = "[SOLD OUT]";
            } else if (productStock[i] <= 2) {
                stockLabel = "[LOW: " + productStock[i] + " left]";
            } else {
                stockLabel = "[In Stock]";
            }

            System.out.printf("  %-4s %-20s $%-6.2f %s%n",
                    productCodes[i], productNames[i], productPrices[i], stockLabel);
        }
        System.out.println("\n=====================================");
    }

    private static void purchaseFlow(Scanner scanner) {
        browseProducts();
        System.out.println("\nEnter a product code to purchase (or 'back' to cancel):");

        while (true) {
            System.out.print("Code: ");
            String code = scanner.nextLine().trim().toUpperCase();

            if (code.equals("BACK") || code.equals("CANCEL")) {
                if (currentInsertedMoney > 0) {
                    System.out.printf("Returning your $%.2f.%n", currentInsertedMoney);
                    currentInsertedMoney = 0.0;
                }
                System.out.println("Purchase cancelled.");
                return;
            }

            int index = findProductIndex(code);
            if (index == -1) {
                System.out.println("Unknown product code '" + code + "'. Try again or type 'back'.");
                continue;
            }

            if (productStock[index] == 0) {
                System.out.println(productNames[index] + " is sold out. Pick another item or type 'back'.");
                continue;
            }

            double price = productPrices[index];
            System.out.printf("Selected: %s — $%.2f%n", productNames[index], price);

            if (currentInsertedMoney < price) {
                System.out.printf("You need to insert $%.2f more.%n", price - currentInsertedMoney);
                insertMoneyFlow(scanner, price);

                if (currentInsertedMoney < price) {
                    System.out.println("Not enough money inserted. Purchase cancelled.");
                    System.out.printf("Returning $%.2f.%n", currentInsertedMoney);
                    currentInsertedMoney = 0.0;
                    return;
                }
            }

            double change = currentInsertedMoney - price;
            productStock[index]--;
            productSoldCount[index]++;
            totalRevenue += price;
            totalTransactions++;
            currentInsertedMoney = 0.0;

            System.out.println();
            System.out.println("*clunk*");
            System.out.println("Dispensing: " + productNames[index]);

            if (change > 0) {
                System.out.printf("Your change: $%.2f%n", change);
                printChangeDenominations(change);
            }

            System.out.println("Enjoy your " + productNames[index] + "!");
            return;
        }
    }

    private static void insertMoneyFlow(Scanner scanner, double targetPrice) {
        System.out.println("\nInsert money. Accepted denominations:");
        System.out.println("  $0.05, $0.10, $0.25, $0.50, $1.00, $2.00, $5.00, $10.00, $20.00");
        System.out.println("Type 'done' when finished or 'cancel' to abort.\n");

        while (currentInsertedMoney < targetPrice) {
            System.out.printf("[Inserted: $%.2f | Remaining: $%.2f] > ",
                    currentInsertedMoney, targetPrice - currentInsertedMoney);

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("done") || input.equals("cancel")) {
                return;
            }

            if (input.startsWith("$")) {
                input = input.substring(1);
            }

            double amount;
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a dollar amount like 1.00 or 0.25.");
                continue;
            }

            if (!isAcceptedDenomination(amount)) {
                System.out.printf("$%.2f is not an accepted denomination. Try again.%n", amount);
                continue;
            }

            currentInsertedMoney += amount;
            currentInsertedMoney = Math.round(currentInsertedMoney * 100.0) / 100.0;
            System.out.printf("Accepted $%.2f. Total credit: $%.2f%n", amount, currentInsertedMoney);
        }

        System.out.println("Sufficient funds inserted.");
    }

    private static boolean isAcceptedDenomination(double amount) {
        for (double d : ACCEPTED_DENOMINATIONS) {
            if (Math.abs(amount - d) < 0.001) {
                return true;
            }
        }
        return false;
    }

    private static void printChangeDenominations(double changeAmount) {
        System.out.print("  Change breakdown: ");
        int remainingCents = (int) Math.round(changeAmount * 100);

        int[] denomCents = { 2000, 1000, 500, 200, 100, 50, 25, 10, 5 };
        String[] denomLabels = {
                "$20.00", "$10.00", "$5.00", "$2.00", "$1.00",
                "$0.50", "$0.25", "$0.10", "$0.05"
        };

        StringBuilder breakdown = new StringBuilder();
        for (int i = 0; i < denomCents.length; i++) {
            int count = remainingCents / denomCents[i];
            if (count > 0) {
                remainingCents -= count * denomCents[i];
                if (breakdown.length() > 0) {
                    breakdown.append(" + ");
                }
                breakdown.append(count).append("x ").append(denomLabels[i]);
            }
        }

        if (remainingCents > 0) {
            if (breakdown.length() > 0) {
                breakdown.append(" + ");
            }
            breakdown.append(String.format("$0.%02d (rounded)", remainingCents));
        }

        System.out.println(breakdown);
    }

    private static boolean adminLogin(Scanner scanner) {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine().trim();

        if (!ADMIN_PASSWORD.equals(password)) {
            System.out.println("Access denied.");
            return true;
        }

        System.out.println("Admin access granted.\n");
        adminMenu(scanner);
        return true;
    }

    private static void adminMenu(Scanner scanner) {
        boolean inAdmin = true;

        while (inAdmin) {
            System.out.println("--- ADMIN PANEL ---");
            System.out.println("1. View Sales Report");
            System.out.println("2. Check Inventory");
            System.out.println("3. Restock Item");
            System.out.println("4. Change Price");
            System.out.println("5. Add New Product");
            System.out.println("6. Low Stock Alerts");
            System.out.println("7. Exit Admin Panel");
            System.out.print("Admin choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewSalesReport();
                    break;
                case "2":
                    viewInventory();
                    break;
                case "3":
                    restockItem(scanner);
                    break;
                case "4":
                    changePrice(scanner);
                    break;
                case "5":
                    addNewProduct(scanner);
                    break;
                case "6":
                    lowStockAlerts();
                    break;
                case "7":
                    inAdmin = false;
                    System.out.println("Exiting admin panel.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }

    private static void viewSalesReport() {
        System.out.println("\n========== SALES REPORT ==========");
        System.out.printf("Total Transactions: %d%n", totalTransactions);
        System.out.printf("Total Revenue:      $%.2f%n", totalRevenue);

        if (totalTransactions == 0) {
            System.out.println("No sales yet.");
            System.out.println("==================================");
            return;
        }

        System.out.printf("Average Sale Value: $%.2f%n", totalRevenue / totalTransactions);
        System.out.println("\nPer-Product Breakdown:");
        System.out.printf("  %-4s %-20s %6s %10s%n", "Code", "Name", "Sold", "Revenue");
        System.out.println("  " + "-".repeat(44));

        String bestSellerName = "";
        int bestSellerCount = 0;

        for (int i = 0; i < productCount; i++) {
            if (productSoldCount[i] > 0) {
                double itemRevenue = productSoldCount[i] * productPrices[i];
                System.out.printf("  %-4s %-20s %6d %9s%n",
                        productCodes[i], productNames[i], productSoldCount[i],
                        String.format("$%.2f", itemRevenue));
            }
            if (productSoldCount[i] > bestSellerCount) {
                bestSellerCount = productSoldCount[i];
                bestSellerName = productNames[i];
            }
        }

        if (!bestSellerName.isEmpty()) {
            System.out.println("\nBest Seller: " + bestSellerName + " (" + bestSellerCount + " sold)");
        }

        System.out.println("==================================");
    }

    private static void viewInventory() {
        System.out.println("\n========== INVENTORY ==========");
        System.out.printf("  %-4s %-20s %-10s %6s %6s%n", "Code", "Name", "Category", "Price", "Stock");
        System.out.println("  " + "-".repeat(52));

        int totalItems = 0;
        for (int i = 0; i < productCount; i++) {
            System.out.printf("  %-4s %-20s %-10s $%-5.2f %6d%n",
                    productCodes[i], productNames[i], productCategories[i],
                    productPrices[i], productStock[i]);
            totalItems += productStock[i];
        }

        System.out.println("  " + "-".repeat(52));
        System.out.println("  Total products: " + productCount + " | Total items in stock: " + totalItems);
        System.out.println("================================");
    }

    private static void restockItem(Scanner scanner) {
        System.out.print("Enter product code to restock: ");
        String code = scanner.nextLine().trim().toUpperCase();

        int index = findProductIndex(code);
        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println(productNames[index] + " current stock: " + productStock[index]);
        System.out.print("How many units to add? ");
        int addQty = readPositiveInt(scanner);

        productStock[index] += addQty;
        System.out.println("Restocked. New stock for " + productNames[index] + ": " + productStock[index]);
    }

    private static void changePrice(Scanner scanner) {
        System.out.print("Enter product code: ");
        String code = scanner.nextLine().trim().toUpperCase();

        int index = findProductIndex(code);
        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }

        System.out.printf("%s current price: $%.2f%n", productNames[index], productPrices[index]);
        System.out.print("Enter new price: $");
        double newPrice = readPositiveDouble(scanner);

        double oldPrice = productPrices[index];
        productPrices[index] = Math.round(newPrice * 100.0) / 100.0;
        System.out.printf("Updated %s: $%.2f -> $%.2f%n", productNames[index], oldPrice, productPrices[index]);
    }

    private static void addNewProduct(Scanner scanner) {
        if (productCount >= MAX_PRODUCTS) {
            System.out.println("Product catalog is full. Cannot add more.");
            return;
        }

        System.out.print("Enter product code (e.g., E1): ");
        String code = scanner.nextLine().trim().toUpperCase();

        if (findProductIndex(code) != -1) {
            System.out.println("Product code '" + code + "' already exists.");
            return;
        }
        if (code.isEmpty()) {
            System.out.println("Product code cannot be empty.");
            return;
        }

        System.out.print("Enter product name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        System.out.print("Enter category (Drinks/Snacks/Candy/Health): ");
        String category = capitalize(scanner.nextLine().trim());

        System.out.print("Enter price: $");
        double price = readPositiveDouble(scanner);

        System.out.print("Enter initial stock: ");
        int stock = readPositiveInt(scanner);

        addProduct(code, name, category, Math.round(price * 100.0) / 100.0, stock);
        System.out.println("Added " + name + " (" + code + ") to the machine.");
    }

    private static void lowStockAlerts() {
        System.out.println("\n--- LOW STOCK ALERTS ---");
        boolean anyLow = false;

        for (int i = 0; i < productCount; i++) {
            if (productStock[i] == 0) {
                System.out.println("  [EMPTY] " + productCodes[i] + " " + productNames[i]);
                anyLow = true;
            } else if (productStock[i] <= 3) {
                System.out.println("  [LOW: " + productStock[i] + "]  " + productCodes[i] + " " + productNames[i]);
                anyLow = true;
            }
        }

        if (!anyLow) {
            System.out.println("  All products are well stocked.");
        }
    }

    private static int findProductIndex(String code) {
        for (int i = 0; i < productCount; i++) {
            if (productCodes[i].equalsIgnoreCase(code)) {
                return i;
            }
        }
        return -1;
    }

    private static int readPositiveInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
                System.out.print("Please enter a positive number: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    private static double readPositiveDouble(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.startsWith("$")) {
                input = input.substring(1);
            }
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                }
                System.out.print("Please enter a positive amount: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount. Try again: ");
            }
        }
    }

    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
