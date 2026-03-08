public class Component {
    private final String name;
    private final int quantity;

    public Component(String name, int quantity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Component name cannot be blank.");
        }
        if (name.contains(",")) {
            throw new IllegalArgumentException("Component name cannot contain commas.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        this.name = name.trim();
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toFileString() {
        return name + "," + quantity;
    }
}
