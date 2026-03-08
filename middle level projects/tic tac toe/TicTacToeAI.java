import java.util.Scanner;

public class TicTacToeAI {
    private static final char AI = 'X';
    private static final char HUMAN = 'O';
    private static final char EMPTY = '_';

    private static final char[] board = {
            EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY
    };

    private static final int[][] WIN_CONDITIONS = {
            { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
            { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
            { 0, 4, 8 }, { 2, 4, 6 }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic-Tac-Toe: You are O, AI is X");
        printBoardWithPositions();

        while (true) {
            printBoard();
            int humanMove = readHumanMove(scanner);
            board[humanMove] = HUMAN;

            if (isWinner(HUMAN)) {
                printBoard();
                System.out.println("You win!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw.");
                break;
            }

            int aiMove = findBestMove();
            board[aiMove] = AI;
            System.out.println("AI plays at position: " + (aiMove + 1));

            if (isWinner(AI)) {
                printBoard();
                System.out.println("AI wins.");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw.");
                break;
            }
        }

        scanner.close();
    }

    private static int readHumanMove(Scanner scanner) {
        while (true) {
            System.out.print("Enter your move (1-9): ");
            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid input. Enter a number from 1 to 9.");
                continue;
            }

            int position = scanner.nextInt();
            if (position < 1 || position > 9) {
                System.out.println("Out of range. Enter a number from 1 to 9.");
                continue;
            }

            int index = position - 1;
            if (board[index] != EMPTY) {
                System.out.println("That cell is already taken. Try another.");
                continue;
            }

            return index;
        }
    }

    private static int evaluateBoard() {
        for (int[] combo : WIN_CONDITIONS) {
            char a = board[combo[0]];
            char b = board[combo[1]];
            char c = board[combo[2]];

            if (a == b && b == c) {
                if (a == AI) {
                    return 10;
                }
                if (a == HUMAN) {
                    return -10;
                }
            }
        }
        return 0;
    }

    private static int minimax(int depth, boolean isMaximizing) {
        int score = evaluateBoard();

        if (score == 10) {
            return score - depth;
        }
        if (score == -10) {
            return score + depth;
        }
        if (isBoardFull()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == EMPTY) {
                    board[i] = AI;
                    bestScore = Math.max(bestScore, minimax(depth + 1, false));
                    board[i] = EMPTY;
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == EMPTY) {
                    board[i] = HUMAN;
                    bestScore = Math.min(bestScore, minimax(depth + 1, true));
                    board[i] = EMPTY;
                }
            }
            return bestScore;
        }
    }

    private static int findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i] == EMPTY) {
                board[i] = AI;
                int moveScore = minimax(0, false);
                board[i] = EMPTY;

                if (moveScore > bestScore) {
                    bestScore = moveScore;
                    bestMove = i;
                }
            }
        }

        return bestMove;
    }

    private static boolean isWinner(char player) {
        for (int[] combo : WIN_CONDITIONS) {
            if (board[combo[0]] == player
                    && board[combo[1]] == player
                    && board[combo[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (char cell : board) {
            if (cell == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private static void printBoardWithPositions() {
        System.out.println("Board positions:");
        System.out.println("1 | 2 | 3");
        System.out.println("4 | 5 | 6");
        System.out.println("7 | 8 | 9");
        System.out.println();
    }

    private static void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print(board[i]);
            if (i % 3 != 2) {
                System.out.print(" | ");
            } else {
                System.out.println();
            }
        }
        System.out.println();
    }
}
