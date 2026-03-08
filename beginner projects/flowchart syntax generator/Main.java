import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder output = new StringBuilder("graph TD\n");

        System.out.print("How many steps are in your process? ");
        int stepCount = readPositiveInt(scanner);

        String[] stepNames = new String[stepCount];
        String[] stepShapes = new String[stepCount];

        for (int i = 0; i < stepCount; i++) {
            System.out.print("Enter name for step " + (i + 1) + ": ");
            stepNames[i] = scanner.nextLine().trim();
            if (stepNames[i].isEmpty()) {
                stepNames[i] = "Untitled Step " + (i + 1);
            }

            System.out.print("Is this a Process or Decision? ");
            stepShapes[i] = readShape(scanner);
        }

        if (stepCount == 1) {
            String nodeId = "Step0";
            output.append(nodeId).append(formatNode(stepShapes[0], stepNames[0])).append("\n");
        } else {
            for (int i = 0; i < stepCount - 1; i++) {
                String currentNodeId = "Step" + i;
                String nextNodeId = "Step" + (i + 1);
                output
                        .append(currentNodeId)
                        .append(formatNode(stepShapes[i], stepNames[i]))
                        .append(" --> ")
                        .append(nextNodeId)
                        .append(formatNode(stepShapes[i + 1], stepNames[i + 1]))
                        .append("\n");
            }
        }

        System.out.println("\nGenerated Mermaid Flowchart Syntax:");
        System.out.println(output);
        System.out.println("Copy-paste this into a Mermaid live editor or Markdown file to render your chart.");
    }

    private static int readPositiveInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Keep prompting until valid input.
            }
            System.out.print("Please enter a valid positive whole number: ");
        }
    }

    private static String readShape(Scanner scanner) {
        while (true) {
            String shape = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if (shape.equals("process") || shape.equals("p")) {
                return "process";
            }
            if (shape.equals("decision") || shape.equals("d")) {
                return "decision";
            }
            System.out.print("Enter Process (P) or Decision (D): ");
        }
    }

    private static String formatNode(String shape, String name) {
        if ("decision".equals(shape)) {
            return "{" + escapeLabel(name) + "}";
        }
        return "[" + escapeLabel(name) + "]";
    }

    private static String escapeLabel(String label) {
        return label.replace("\"", "\\\"");
    }
}
