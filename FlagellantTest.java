import java.util.Timer;
import java.util.TimerTask;

public class FlagellantTest {
    public static void main(String[] args) {
        // Create a Flagellant hero and an enemy (e.g., a Goblin)
        Flagellant flagellant = new Flagellant("Flagellant", 90, 5, 20, 1, 0.1, 100, 90);
        Mob goblin = new Goblin();

        System.out.println("Battle starts!");

        // Timer to simulate health regeneration every 10 seconds
        Timer timer = new Timer();
        TimerTask regenerationTask = new TimerTask() {
            @Override
            public void run() {
                flagellant.battleTick();
            }
        };

        // Schedule health regeneration every 10 seconds
        timer.scheduleAtFixedRate(regenerationTask, 0, 10000);

        // Simulate a battle loop
        int turn = 1;
        while (flagellant.getHp() > 0 && goblin.getHp() > 0) {
            System.out.println("\n--- Turn " + turn + " ---");

            // Flagellant performs a basic attack
            flagellant.basicAttack(goblin);
            if (goblin.getHp() <= 0) {
                System.out.println("The Goblin has been defeated!");
                break;
            }

            // Goblin attacks Flagellant
            goblin.basicAttack(flagellant);
            if (flagellant.getHp() <= 0) {
                System.out.println("Flagellant has fallen!");
                break;
            }

            // Use ultimate if conditions are met
            if (flagellant.canUseUltimate() && flagellant.getHp() < flagellant.getMaxHP() * 0.3) {
                flagellant.useUltimate(goblin);
            }

            // Increment turn
            turn++;

            // Simulate a delay between turns for readability
            try {
                Thread.sleep(3000); // 3-second delay between turns
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // End the battle
        flagellant.endBattle();
        timer.cancel(); // Stop the regeneration task
        System.out.println("\nBattle has ended.");
    }
}