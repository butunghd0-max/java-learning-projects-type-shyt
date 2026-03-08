import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ExpenseTracker {
    private final List<Expense> purchases = new ArrayList<>();

    public void addExpense(String category, String description, double amount) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        Expense newExpense = new Expense(category.trim(), description.trim(), amount);
        purchases.add(newExpense);
        System.out.printf(Locale.US, "Logged: $%.2f for %s (%s)%n", amount, description, category);
    }

    public String generateReport() {
        Map<String, Double> totalsByCategory = new LinkedHashMap<>();
        double grandTotal = 0.0;

        for (Expense item : purchases) {
            grandTotal += item.getAmount();
            totalsByCategory.merge(item.getCategory(), item.getAmount(), Double::sum);
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("=== MONTHLY BUDGET REPORT ===\n");

        if (purchases.isEmpty()) {
            reportBuilder.append("No expenses logged.\n");
        } else {
            for (Map.Entry<String, Double> entry : totalsByCategory.entrySet()) {
                reportBuilder.append(String.format(Locale.US, "%-18s $%.2f%n", entry.getKey() + ":", entry.getValue()));
            }
        }

        reportBuilder.append("-----------------------------\n");
        reportBuilder.append(String.format(Locale.US, "GRAND TOTAL:        $%.2f%n", grandTotal));
        reportBuilder.append("=============================\n");

        String reportText = reportBuilder.toString();
        System.out.println("\n" + reportText);
        writeReportToFile(reportText, "BudgetReport.txt");
        return reportText;
    }

    private void writeReportToFile(String reportText, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(reportText);
            System.out.println("Success: Report saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error: Could not save the file. " + e.getMessage());
        }
    }
}
