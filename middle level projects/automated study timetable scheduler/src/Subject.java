public class Subject {
    private final String name;
    private int hoursNeeded;

    public Subject(String name, int hoursNeeded) {
        if (hoursNeeded < 0) {
            throw new IllegalArgumentException("hoursNeeded must be >= 0");
        }
        this.name = name;
        this.hoursNeeded = hoursNeeded;
    }

    public String getName() {
        return name;
    }

    public int getHoursNeeded() {
        return hoursNeeded;
    }

    public void reduceHoursNeeded(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("hours must be >= 0");
        }
        hoursNeeded = Math.max(0, hoursNeeded - hours);
    }
}