import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Prize> prizePool = List.of(
                new Prize("Teddy Bear", 45, 4.00),
                new Prize("Plush Dragon", 60, 6.00),
                new Prize("Mini Duck", 25, 2.00),
                new Prize("Robot Toy", 55, 5.00)
        );

        Claw claw = new Claw(35);
        GameSession session = new GameSession(2.00, 0.35);

        Random picker = new Random();
        int plays = 25;

        for (int i = 0; i < plays; i++) {
            Prize target = prizePool.get(picker.nextInt(prizePool.size()));
            session.play(claw, target);
        }

        session.printStats();
    }
}
