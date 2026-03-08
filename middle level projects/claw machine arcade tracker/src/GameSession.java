import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private final double costPerPlay;
    private final double targetPayoutRate;
    private int totalPlays;
    private int totalWins;
    private double totalSpent;
    private double totalPrizeValuePaid;
    private final List<Prize> prizesWon;

    public GameSession(double costPerPlay, double targetPayoutRate) {
        if (costPerPlay <= 0) {
            throw new IllegalArgumentException("costPerPlay must be > 0");
        }
        if (targetPayoutRate <= 0 || targetPayoutRate >= 1) {
            throw new IllegalArgumentException("targetPayoutRate must be between 0 and 1");
        }
        this.costPerPlay = costPerPlay;
        this.targetPayoutRate = targetPayoutRate;
        this.prizesWon = new ArrayList<>();
    }

    public void play(Claw claw, Prize prize) {
        if (claw == null) {
            throw new IllegalArgumentException("claw must not be null");
        }
        if (prize == null) {
            throw new IllegalArgumentException("prize must not be null");
        }

        totalPlays++;
        totalSpent += costPerPlay;

        double payoutPressure = payoutPressure();
        boolean won = claw.tryGrab(prize, payoutPressure);

        if (won) {
            totalWins++;
            totalPrizeValuePaid += prize.getValue();
            prizesWon.add(prize);
            System.out.printf("PLAY %d: WIN -> %s ($%.2f)%n", totalPlays, prize.getName(), prize.getValue());
        } else {
            System.out.printf("PLAY %d: MISS -> %s%n", totalPlays, prize.getName());
        }
    }

    private double payoutPressure() {
        double desiredPayoutValue = totalSpent * targetPayoutRate;
        if (desiredPayoutValue <= 0.0) {
            return 0.0;
        }

        double gap = desiredPayoutValue - totalPrizeValuePaid;
        return clamp(gap / desiredPayoutValue, -1.0, 1.0);
    }

    public void printStats() {
        double winRate = totalPlays == 0 ? 0.0 : (totalWins * 100.0) / totalPlays;
        double payoutRate = totalSpent == 0.0 ? 0.0 : (totalPrizeValuePaid * 100.0) / totalSpent;

        System.out.println();
        System.out.println("===== SESSION STATS =====");
        System.out.printf("Total Plays: %d%n", totalPlays);
        System.out.printf("Total Wins: %d%n", totalWins);
        System.out.printf("Win Rate: %.2f%%%n", winRate);
        System.out.printf("Money Spent: $%.2f%n", totalSpent);
        System.out.printf("Prize Value Paid: $%.2f%n", totalPrizeValuePaid);
        System.out.printf("Payout Rate: %.2f%%%n", payoutRate);
        System.out.printf("Target Payout Rate: %.2f%%%n", targetPayoutRate * 100.0);
        System.out.printf("Prizes Won: %d%n", prizesWon.size());
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
