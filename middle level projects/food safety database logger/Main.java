public class Main {
    public static void main(String[] args) {
        FoodSafetyLogger app = new FoodSafetyLogger();

        // CREATE
        app.addLog(new TempLog(1, "Dairy - Milk", 3.2));
        app.addLog(new TempLog(2, "Meat - Chicken", 5.5));

        // READ (includes safety analysis)
        app.readLogs();

        // UPDATE
        app.updateLog(1, 2.8);

        // DELETE
        app.deleteLog(2);

        // READ again to confirm changes
        app.readLogs();
    }
}
