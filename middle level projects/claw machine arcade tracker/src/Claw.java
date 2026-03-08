import java.util.Random;

public class Claw {
    private final int baseGripStrength;
    private final Random random;

    public Claw(int baseGripStrength) {
        this(baseGripStrength, new Random());
    }

    public Claw(int baseGripStrength, Random random) {
        if (baseGripStrength < 1 || baseGripStrength > 100) {
            throw new IllegalArgumentException("baseGripStrength must be in range 1..100");
        }
        if (random == null) {
            throw new IllegalArgumentException("random must not be null");
        }
        this.baseGripStrength = baseGripStrength;
        this.random = random;
    }

    public boolean tryGrab(Prize targetPrize, double payoutPressure) {
        if (targetPrize == null) {
            throw new IllegalArgumentException("targetPrize must not be null");
        }

        int adjustedGrip = adjustedGrip(payoutPressure);
        double successRate = successRate(adjustedGrip, targetPrize.getWeight());
        return random.nextDouble() < successRate;
    }

    private int adjustedGrip(double payoutPressure) {
        double normalized = clamp(payoutPressure, -1.0, 1.0);
        int delta = (int) Math.round(normalized * 25);
        return clamp(baseGripStrength + delta, 1, 100);
    }

    private double successRate(int adjustedGrip, int prizeWeight) {
        double rate = (adjustedGrip - prizeWeight + 35) / 100.0;
        return clamp(rate, 0.02, 0.95);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
