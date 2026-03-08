public class Prize {
    private final String name;
    private final int weight;
    private final double value;

    public Prize(String name, int weight, double value) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (weight < 1 || weight > 100) {
            throw new IllegalArgumentException("weight must be in range 1..100");
        }
        if (value < 0) {
            throw new IllegalArgumentException("value must be non-negative");
        }

        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }
}
