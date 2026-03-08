import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomWorkoutGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] upperBody = { "Pushups", "Pullups", "Dips", "Pike Pushups", "Plank Shoulder Taps" };
        String[] lowerBody = { "Squats", "Lunges", "Deadlifts", "Step-Ups", "Calf Raises" };
        String[] core = { "Plank", "Crunches", "Russian Twists", "Leg Raises", "Mountain Climbers" };
        String[] cardio = { "Jump Rope", "Burpees", "High Knees", "Jumping Jacks", "Sprint Intervals" };
        List<String> exercisePool = new ArrayList<>();
        exercisePool.addAll(Arrays.asList(upperBody));
        exercisePool.addAll(Arrays.asList(lowerBody));
        exercisePool.addAll(Arrays.asList(core));
        exercisePool.addAll(Arrays.asList(cardio));
        System.out.print("How many exercises do you want in your routine today? ");
        int numExercises = readPositiveInt(scanner);
        int maxAvailable = exercisePool.size();
        if (numExercises > maxAvailable) {
            System.out.println("Only " + maxAvailable + " unique exercises are available.");
            System.out.println("Your routine length has been set to " + maxAvailable + ".");
            numExercises = maxAvailable;
        }
        Collections.shuffle(exercisePool, random);
        System.out.println("\nYour routine:");
        for (int i = 0; i < numExercises; i++) {
            String exercise = exercisePool.get(i);
            int sets = random.nextInt(3) + 3; // 3-5 sets
            int reps = random.nextInt(5) + 8; // 8-12 reps
            System.out.println((i + 1) + ". " + exercise + " - " + sets + " sets x " + reps + " reps");
        }
        System.out.println("\nStay consistent and train safely.");
        scanner.close();
    }

    private static int readPositiveInt(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0) {
                    return value;
                }
                System.out.print("Please enter a positive number: ");
            } else {
                scanner.next();
                System.out.print("Invalid input. Enter a whole number: ");
            }
        }
    }
}
