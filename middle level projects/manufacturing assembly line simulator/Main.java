public class Main {
    public static void main(String[] args) {
        AssemblyLine factory = new AssemblyLine();

        // Produce 10 items and place them on the queue-based conveyor.
        factory.runProductionBatch(10);

        // Inspect items in strict FIFO order.
        factory.runQualityControl();
    }
}
