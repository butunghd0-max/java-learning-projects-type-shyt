import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[10];
        String[] priorities = new String[10];
        int taskCount = 0;

        while (true) {
            System.out.println("\n=== Robot Maintenance Log ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                if (taskCount >= tasks.length) {
                    System.out.println("Task list is full. Max tasks: " + tasks.length);
                    continue;
                }

                System.out.print("Enter task name: ");
                String newTask = scanner.nextLine().trim();
                if (newTask.isEmpty()) {
                    System.out.println("Task cannot be empty.");
                    continue;
                }

                String priority;
                while (true) {
                    System.out.print("Enter priority (High/Medium/Low): ");
                    priority = scanner.nextLine().trim();

                    if (priority.equalsIgnoreCase("High")
                            || priority.equalsIgnoreCase("Medium")
                            || priority.equalsIgnoreCase("Low")) {
                        break;
                    }
                    System.out.println("Invalid priority. Please use High, Medium, or Low.");
                }

                tasks[taskCount] = newTask;
                priorities[taskCount] = capitalize(priority);
                taskCount++;
                System.out.println("Task added.");

            } else if (choice.equals("2")) {
                if (taskCount == 0) {
                    System.out.println("No tasks yet.");
                } else {
                    System.out.println("\nCurrent Tasks:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i] + " [" + priorities[i] + "]");
                    }
                }

            } else if (choice.equals("3")) {
                System.out.println("Goodbye.");
                break;

            } else {
                System.out.println("Invalid option. Choose 1, 2, or 3.");
            }
        }

        scanner.close();
    }

    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
