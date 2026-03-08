public class Main {
    public static void main(String[] args) {
        ExpenseTracker myBudget = new ExpenseTracker();

        System.out.println("--- Entering Receipts ---");
        myBudget.addExpense("Hardware", "Stepper Motors", 45.50);
        myBudget.addExpense("Food", "Pizza for build team", 28.00);
        myBudget.addExpense("Hardware", "Jumper Wires", 12.25);
        myBudget.addExpense("Misc", "Extension Cord", 15.00);

        myBudget.generateReport();
    }
}
