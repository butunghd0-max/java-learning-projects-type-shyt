import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private static final String ROCK = "Rock";
    private static final String PAPER = "Paper";
    private static final String SCISSORS = "Scissors";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int userScore = 0;
        int computerScore = 0;
        int ties = 0;

        int computerRockCount = 0;
        int computerPaperCount = 0;
        int computerScissorsCount = 0;

        boolean playAgain = true;

        System.out.println("=== Rock Paper Scissors ===");
        System.out.println("Enter Rock/Paper/Scissors or 1/2/3.");

        while (playAgain) {
            String userMove = getUserMove(scanner);
            String computerMove = getComputerMove(random);

            if (computerMove.equals(ROCK)) {
                computerRockCount++;
            } else if (computerMove.equals(PAPER)) {
                computerPaperCount++;
            } else {
                computerScissorsCount++;
            }

            System.out.println("Computer chose: " + computerMove);

            int result = getRoundResult(userMove, computerMove);
            if (result == 0) {
                ties++;
                System.out.println("It's a tie!");
            } else if (result > 0) {
                userScore++;
                System.out.println("You win this round!");
            } else {
                computerScore++;
                System.out.println("Computer wins this round!");
            }

            System.out.println("Score -> You: " + userScore + " | Computer: " + computerScore + " | Ties: " + ties);
            playAgain = shouldPlayAgain(scanner);
        }

        int totalRounds = computerRockCount + computerPaperCount + computerScissorsCount;
        System.out.println("\n=== Session Summary ===");
        System.out.println("Final Score -> You: " + userScore + " | Computer: " + computerScore + " | Ties: " + ties);

        if (totalRounds > 0) {
            double rockPercent = (computerRockCount * 100.0) / totalRounds;
            double paperPercent = (computerPaperCount * 100.0) / totalRounds;
            double scissorsPercent = (computerScissorsCount * 100.0) / totalRounds;

            System.out.printf("Computer chose Rock %.2f%% of the time (%d/%d).%n", rockPercent, computerRockCount, totalRounds);
            System.out.printf("Computer chose Paper %.2f%% of the time (%d/%d).%n", paperPercent, computerPaperCount, totalRounds);
            System.out.printf("Computer chose Scissors %.2f%% of the time (%d/%d).%n", scissorsPercent, computerScissorsCount, totalRounds);
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static String getUserMove(Scanner scanner) {
        while (true) {
            System.out.print("Your move (Rock/Paper/Scissors or 1/2/3): ");
            String input = scanner.nextLine().trim();

            if (input.equals("1") || input.equalsIgnoreCase("rock")) {
                return ROCK;
            }
            if (input.equals("2") || input.equalsIgnoreCase("paper")) {
                return PAPER;
            }
            if (input.equals("3") || input.equalsIgnoreCase("scissors")) {
                return SCISSORS;
            }

            System.out.println("Invalid input. Please type Rock, Paper, Scissors, or 1, 2, 3.");
        }
    }

    private static String getComputerMove(Random random) {
        int moveNumber = random.nextInt(3) + 1;
        if (moveNumber == 1) {
            return ROCK;
        }
        if (moveNumber == 2) {
            return PAPER;
        }
        return SCISSORS;
    }

    private static int getRoundResult(String userMove, String computerMove) {
        if (userMove.equals(computerMove)) {
            return 0;
        }

        if ((userMove.equals(ROCK) && computerMove.equals(SCISSORS))
                || (userMove.equals(PAPER) && computerMove.equals(ROCK))
                || (userMove.equals(SCISSORS) && computerMove.equals(PAPER))) {
            return 1;
        }

        return -1;
    }

    private static boolean shouldPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("Play another round? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }

            System.out.println("Please enter 'y' or 'n'.");
        }
    }
}
