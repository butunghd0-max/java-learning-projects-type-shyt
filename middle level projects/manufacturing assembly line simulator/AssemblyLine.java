import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class AssemblyLine {
    private static final int DEFECT_RATE_PERCENT = 20;

    // LinkedList used through Queue interface to enforce FIFO operations.
    private final Queue<Product> conveyorBelt = new LinkedList<>();
    private final Random random = new Random();

    public void runProductionBatch(int batchSize) {
        System.out.println("--- Starting Production of " + batchSize + " units ---");

        for (int i = 1; i <= batchSize; i++) {
            boolean defectHappened = random.nextInt(100) < DEFECT_RATE_PERCENT;
            Product newPart = new Product(i, defectHappened);
            conveyorBelt.add(newPart); // Enqueue to the back of the belt.
        }

        System.out.println("Batch complete. " + conveyorBelt.size() + " items on the belt.\n");
    }

    public void runQualityControl() {
        System.out.println("--- Starting Quality Control Inspection ---");
        int passed = 0;
        int scrapped = 0;

        while (!conveyorBelt.isEmpty()) {
            Product currentPart = conveyorBelt.poll(); // Dequeue from the front of the belt.

            if (currentPart.isDefective()) {
                System.out.println("Part #" + currentPart.getId() + " FAILED inspection. Scrapped.");
                scrapped++;
            } else {
                System.out.println("Part #" + currentPart.getId() + " PASSED inspection. Ready to ship.");
                passed++;
            }
        }

        System.out.println("\n--- Shift Report ---");
        System.out.println("Total Passed: " + passed);
        System.out.println("Total Scrapped: " + scrapped);
    }
}
