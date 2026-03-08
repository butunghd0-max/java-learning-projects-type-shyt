public class Main {
    public static void main(String[] args) {
        InventoryManager labManager = new InventoryManager("data/stock.txt");

        labManager.loadInventory();

        labManager.addOrUpdateComponent(new Component("Micro Servo SG90", 24));
        labManager.addOrUpdateComponent(new Component("Arduino Uno R3", 5));
        labManager.addOrUpdateComponent(new Component("ESP32 DevKit V1", 8));

        // Example of changing stock after loading existing data.
        labManager.adjustQuantity("Arduino Uno R3", -1);
        labManager.adjustQuantity("Micro Servo SG90", 6);

        labManager.saveInventory();
        labManager.printInventory();
    }
}
