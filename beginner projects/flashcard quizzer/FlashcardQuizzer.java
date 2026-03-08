import java.util.Scanner;

public class FlashcardQuizzer {
    public static void main(String[] args) {
        String[] questions = {
                "What does KV stand for in motors?",
                "What should you do if a LiPo battery starts to swell?",
                "What PPE must you wear when cutting materials in the lab?",
                "What is the first action before servicing a robot's wiring?",
                "What does an emergency stop button do?"
        };

        String[] answers = {
                "Velocity Constant",
                "Stop using it immediately and isolate it in a fire-safe container",
                "Safety glasses",
                "Disconnect the power source",
                "Immediately cuts power to the system"
        };

        if (questions.length != answers.length || questions.length == 0) {
            System.out.println("Quiz setup error: questions and answers must be the same non-zero length.");
            return;
        }

        int score = 0;
        boolean mustPassSafetyQuiz = true;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Flashcard Quizzer ===");
            System.out.println("Answer each question and press Enter.\n");

            for (int i = 0; i < questions.length; i++) {
                System.out.println("Question " + (i + 1) + ": " + questions[i]);
                System.out.print("Your answer: ");
                String guess = scanner.nextLine().trim();

                if (guess.equalsIgnoreCase(answers[i])) {
                    score++;
                    System.out.println("Correct!\n");
                } else {
                    System.out.println("Not quite. Correct answer: " + answers[i] + "\n");
                }
            }
        }

        double percentage = (score * 100.0) / questions.length;
        System.out.printf("Final score: %d/%d correct (%.0f%%)%n", score, questions.length, percentage);

        if (mustPassSafetyQuiz) {
            if (score == questions.length) {
                System.out.println("Status: Authorized for lab access.");
            } else {
                System.out.println("Status: Not authorized yet. You must score 100% on safety questions.");
            }
        }
    }
}
