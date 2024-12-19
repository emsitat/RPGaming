
import java.util.concurrent.*;

public class Crusader extends Hero {
    private ScheduledExecutorService protScheduler;
    private ScheduledExecutorService countdownScheduler;

    private double baseProt; // Store original PROT
    private int baseHp; // Store original HP

    public Crusader(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana) {
        super(name, hp, physicalDamage, magicPower, level, prot, maxMana);
        this.protScheduler = Executors.newSingleThreadScheduledExecutor();
        this.countdownScheduler = Executors.newSingleThreadScheduledExecutor();
        this.baseProt = prot; // Initialize with starting PROT
        this.baseHp = hp; // Initialize with starting HP
    }

    // Override useUltimate for Crusader's unique ultimate ability
    @Override
    public void useUltimate(Character target) {
        if (canUseUltimate()) {
            setHp((int) (getHp() * 1.3));
            setProt(getProt() + 0.3);
            System.out.println(getName() + " uses their ultimate ability. Restored 30% HP and gained 30% PROT for 30 seconds.");
            setMana(0); // Reset mana after using the ultimate

            scheduleProtEnd(); // Schedule the end of the PROT effect
            startCountdown();  // Start countdown timer
        } else {
            System.out.println(getName() + " does not have enough mana to use the ultimate.");
        }
    }

    // Schedule the end of the 30% PROT effect after 30 seconds
    private void scheduleProtEnd() {
        protScheduler.schedule(() -> {
            setProt(baseProt); // Restore original PROT
            setHp(Math.min(getHp(), baseHp)); // Clamp HP back to the original max if exceeded
            System.out.println(getName() + "'s PROT effect has ended. HP and PROT restored.");
        }, 30, TimeUnit.SECONDS);
    }

    // Start a countdown timer for the 30-second PROT effect
    private void startCountdown() {
        countdownScheduler.scheduleAtFixedRate(new Runnable() {
            private int timeLeft = 30;

            @Override
            public void run() {
                if (timeLeft > 0) {
                    System.out.println("PROT effect will end in: " + timeLeft + " seconds.");
                    timeLeft--;
                } else {
                    countdownScheduler.shutdown(); // Stop the countdown scheduler when the time is up
                }
            }
        }, 0, 1, TimeUnit.SECONDS); // Update countdown every second
    }

    // Level-up method for Crusader
    @Override
    public void levelUp() {
        if (getLevel() < 5) {
            setLevel(getLevel() + 1);
            int[][] statTable = {{150, 10}, {170, 12}, {200, 15}, {240, 18}, {300, 22}};
            int newLevelIndex = Math.min(getLevel() - 1, statTable.length - 1);
            baseHp = statTable[newLevelIndex][0]; // Update base HP for the new level
            setHp(baseHp); // Set current HP to the new base
            setPhysicalDamage(statTable[newLevelIndex][1]);

            System.out.println(getName() + " leveled up to " + getLevel() + "!");
        } else {
            System.out.println(getName() + " has reached max level.");
        }
    }

    // Shutdown the schedulers when the Crusader is no longer in use (to prevent resource leaks)
    public void shutdownSchedulers() {
        if (protScheduler != null && !protScheduler.isShutdown()) {
            protScheduler.shutdown();
        }
        if (countdownScheduler != null && !countdownScheduler.isShutdown()) {
            countdownScheduler.shutdown();
        }
    }
}