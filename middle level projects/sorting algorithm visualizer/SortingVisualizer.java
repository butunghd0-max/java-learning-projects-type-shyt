import java.util.Random;
import java.util.Scanner;

public class SortingVisualizer {
    private static final int ARRAY_SIZE = 12;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 20;
    private static final int FRAME_DELAY_MS = 150;

    static class SortStats {
        long outerLoops;
        long innerLoops;
        long comparisons;
        long swaps;
        long framesDrawn;
        long durationMs;
    }

    public static void drawGraph(int[] arr) {
        clearConsole();
        for (int value : arr) {
            System.out.printf("%2d | ", value);
            for (int i = 0; i < value; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }

    public static SortStats bubbleSort(int[] arr) throws InterruptedException {
        SortStats stats = new SortStats();
        long startTime = System.nanoTime();
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            stats.outerLoops++;
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                stats.innerLoops++;
                stats.comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    stats.swaps++;
                    stats.framesDrawn++;

                    drawGraph(arr);
                    Thread.sleep(FRAME_DELAY_MS);
                }
            }

            if (!swapped) {
                break;
            }
        }

        stats.durationMs = (System.nanoTime() - startTime) / 1_000_000;
        return stats;
    }

    public static SortStats selectionSort(int[] arr) throws InterruptedException {
        SortStats stats = new SortStats();
        long startTime = System.nanoTime();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            stats.outerLoops++;
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                stats.innerLoops++;
                stats.comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                stats.swaps++;
                stats.framesDrawn++;

                drawGraph(arr);
                Thread.sleep(FRAME_DELAY_MS);
            }
        }

        stats.durationMs = (System.nanoTime() - startTime) / 1_000_000;
        return stats;
    }

    public static int[] randomArray(int size, int min, int max) {
        Random random = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }

        return arr;
    }

    private static void clearConsole() {
        // ANSI clear for terminals that support it; fallback spacing still works.
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static int chooseSort(Scanner scanner) {
        while (true) {
            System.out.println("Choose sorting algorithm:");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.print("Enter 1 or 2: ");

            String input = scanner.nextLine().trim();
            if ("1".equals(input) || "2".equals(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Invalid input. Please type 1 or 2.");
            System.out.println();
        }
    }

    private static void printReport(String algorithmName, SortStats stats) {
        System.out.println();
        System.out.println("Sort Report (" + algorithmName + ")");
        System.out.println("Outer loop runs : " + stats.outerLoops);
        System.out.println("Inner loop runs : " + stats.innerLoops);
        System.out.println("Comparisons     : " + stats.comparisons);
        System.out.println("Swaps           : " + stats.swaps);
        System.out.println("Frames drawn    : " + stats.framesDrawn);
        System.out.println("Duration (ms)   : " + stats.durationMs);
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int[] data = randomArray(ARRAY_SIZE, MIN_VALUE, MAX_VALUE);
        int choice = chooseSort(scanner);
        String algorithmName = choice == 1 ? "Bubble Sort" : "Selection Sort";
        SortStats stats;

        System.out.println("Starting sorting visualizer (" + algorithmName + ")...");
        Thread.sleep(700);

        drawGraph(data);
        Thread.sleep(500);

        if (choice == 1) {
            stats = bubbleSort(data);
        } else {
            stats = selectionSort(data);
        }

        drawGraph(data);
        System.out.println("Sorting complete.");
        printReport(algorithmName, stats);
        scanner.close();
    }
}
