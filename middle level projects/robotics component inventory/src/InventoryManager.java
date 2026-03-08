import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InventoryManager {
    private final Path filePath;
    private final Map<String, Integer> inventory = new LinkedHashMap<>();

    public InventoryManager(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    public void loadInventory() {
        inventory.clear();

        if (!Files.exists(filePath)) {
            System.out.println("No inventory file found. Starting with empty inventory.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            int lineNumber = 0;

            for (String line : lines) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",", 2);
                if (parts.length != 2) {
                    System.out.println("Skipping invalid line " + lineNumber + ": " + line);
                    continue;
                }

                String name = parts[0].trim();
                String quantityText = parts[1].trim();

                if (name.isBlank()) {
                    System.out.println("Skipping invalid line " + lineNumber + ": missing component name.");
                    continue;
                }

                try {
                    int quantity = Integer.parseInt(quantityText);
                    if (quantity < 0) {
                        System.out.println("Skipping invalid line " + lineNumber + ": quantity cannot be negative.");
                        continue;
                    }
                    inventory.put(name, quantity);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid line " + lineNumber + ": quantity is not a number.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    public void addOrUpdateComponent(Component component) {
        inventory.put(component.getName(), component.getQuantity());
    }

    public void adjustQuantity(String componentName, int delta) {
        if (componentName == null || componentName.isBlank()) {
            throw new IllegalArgumentException("Component name cannot be blank.");
        }

        int current = inventory.getOrDefault(componentName.trim(), 0);
        int updated = current + delta;

        if (updated < 0) {
            throw new IllegalArgumentException("Cannot reduce below zero for component: " + componentName);
        }

        inventory.put(componentName.trim(), updated);
    }

    public void saveInventory() {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                    Component component = new Component(entry.getKey(), entry.getValue());
                    writer.write(component.toFileString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void printInventory() {
        System.out.println();
        System.out.println("--- Current Lab Inventory ---");

        if (inventory.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Part: " + entry.getKey() + " | Stock: " + entry.getValue());
        }
    }
}
