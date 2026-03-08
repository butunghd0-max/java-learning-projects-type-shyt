import java.util.Scanner;

public class GradePointAverager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numSubjects = 5;
        double total = 0;

        System.out.println("=== Grade Point Averager ===");
        System.out.println("Enter " + numSubjects + " grades (0 to 100):");

        for (int i = 1; i <= numSubjects; i++) {
            double grade;

            while (true) {
                System.out.print("Grade " + i + ": ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    continue;
                }

                grade = scanner.nextDouble();
                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100.");
                    continue;
                }
                break;
            }

            total += grade;
        }

        double average = total / numSubjects;
        char letterGrade;
        if (average >= 90) {
            letterGrade = 'A';
        } else if (average >= 80) {
            letterGrade = 'B';
        } else if (average >= 70) {
            letterGrade = 'C';
        } else if (average >= 60) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
        System.out.printf("%nAverage: %.2f%n", average);
        System.out.println("Letter Grade: " + letterGrade);

        scanner.close();
    }
}
