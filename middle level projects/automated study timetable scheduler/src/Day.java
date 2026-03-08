public class Day {
    private final String name;
    private int availableHours;

    public Day(String name, int availableHours) {
        if (availableHours < 0) {
            throw new IllegalArgumentException("availableHours must be >= 0");
        }
        this.name = name;
        this.availableHours = availableHours;
    }

    public String getName() {
        return name;
    }

    public int getAvailableHours() {
        return availableHours;
    }

    public void reduceAvailableHours(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("hours must be >= 0");
        }
        availableHours = Math.max(0, availableHours - hours);
    }
}