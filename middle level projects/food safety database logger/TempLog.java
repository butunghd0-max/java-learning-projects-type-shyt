public class TempLog {
    private final int id;
    private final String foodItem;
    private double temperatureCelsius;

    public TempLog(int id, String foodItem, double temperatureCelsius) {
        this.id = id;
        this.foodItem = foodItem;
        this.temperatureCelsius = temperatureCelsius;
    }

    public int getId() {
        return id;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Item: " + foodItem + " | Temp: " + temperatureCelsius + "°C";
    }
}
