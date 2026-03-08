import java.util.ArrayList;
import java.util.List;

public class FoodSafetyLogger {
    private static final double DANGER_ZONE_MAX_COLD_C = 4.0;
    private final List<TempLog> logs = new ArrayList<>();

    // CREATE
    public boolean addLog(TempLog log) {
        if (findById(log.getId()) != null) {
            System.out.println("Cannot create log: ID " + log.getId() + " already exists.");
            return false;
        }

        logs.add(log);
        System.out.println("Created log for: " + log.getFoodItem());
        return true;
    }

    // READ + ANALYZE
    public void readLogs() {
        System.out.println("\n--- Temperature Logs ---");
        if (logs.isEmpty()) {
            System.out.println("No logs found.");
            return;
        }

        for (TempLog log : logs) {
            System.out.println(log);
            if (log.getTemperatureCelsius() > DANGER_ZONE_MAX_COLD_C) {
                System.out.println("   -> WARNING: " + log.getFoodItem()
                        + " is in the Danger Zone (>" + DANGER_ZONE_MAX_COLD_C + "°C).");
            }
        }
    }

    // UPDATE
    public boolean updateLog(int id, double newTemperatureCelsius) {
        TempLog existing = findById(id);
        if (existing == null) {
            System.out.println("No log found for ID " + id + ". Nothing updated.");
            return false;
        }

        existing.setTemperatureCelsius(newTemperatureCelsius);
        System.out.println("\nUpdated ID " + id + " to " + newTemperatureCelsius + "°C");
        return true;
    }

    // DELETE
    public boolean deleteLog(int id) {
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getId() == id) {
                System.out.println("\nDeleted log for: " + logs.get(i).getFoodItem());
                logs.remove(i);
                return true;
            }
        }

        System.out.println("No log found for ID " + id + ". Nothing deleted.");
        return false;
    }

    private TempLog findById(int id) {
        for (TempLog log : logs) {
            if (log.getId() == id) {
                return log;
            }
        }
        return null;
    }
}
