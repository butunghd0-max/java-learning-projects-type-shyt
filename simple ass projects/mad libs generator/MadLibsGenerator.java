import java.util.Scanner;

public class MadLibsGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Robot Maintenance Report Mad Lib ===");
        System.out.print("Enter an adjective: ");
        String adjective = scanner.nextLine();

        System.out.print("Enter a robot part name: ");
        String partName = scanner.nextLine();

        System.out.print("Enter an error code: ");
        String errorCode = scanner.nextLine();

        System.out.print("Enter a tool: ");
        String tool = scanner.nextLine();

        System.out.print("Enter a place in the lab: ");
        String place = scanner.nextLine();

        System.out.print("Enter a color: ");
        String color = scanner.nextLine();

        String story = "\n--- YOUR MAD LIB STORY ---\n"
                + "At 07:42, the " + adjective + " maintenance unit detected fault " + errorCode
                + " in the " + partName + ". "
                + "Technician Alpha used a " + color + " " + tool
                + " to recalibrate the assembly near the " + place + ". "
                + "After diagnostics, the system declared the robot \"mostly stable\" and ready for deployment.\n";

        System.out.println(story);
        scanner.close();
    }
}
