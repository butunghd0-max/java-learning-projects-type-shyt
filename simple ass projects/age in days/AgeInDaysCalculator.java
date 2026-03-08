import java.util.Scanner;

public class AgeInDaysCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How old are you? ");
        int ageYears = scanner.nextInt();
        if (ageYears < 0) {
            System.out.println("Negative age? Time traveler detected.");
            scanner.close();
            return;
        }
        int totalDays = ageYears * 365;
        int leapDays = ageYears/4;
        totalDays += leapDays;
        System.out.println("You have been alive for approximately " + totalDays + " days!");
        System.out.println(getAgeMessage(ageYears));
        scanner.close();
    }
    private static String getAgeMessage(int ageYears) {
        if (ageYears <= 2) {
            return "baby, still sleeping 8 hours a day bruh";
        } else if (ageYears <= 5) {
            return "Tiny af";
        } else if (ageYears <= 12) {
            return "bocil";
        } else if (ageYears <= 17) {
            return "puberty";
        } else if (ageYears <= 24) {
            return "Young adult tired af but pretending it's fine.";
        } else if (ageYears <= 34) {
            return "Adult";
        } else if (ageYears <= 49) {
            return "Prime years...plus random back pain.";
        } else if (ageYears <= 64) {
            return "grandpa grandma";
        } else {
            return "ur old";
        }
    }
}
