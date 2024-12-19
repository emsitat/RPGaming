public class Knightmare extends Boss {
    private boolean isHealthRestored = false; // Check if passive has already triggered
    private static final int COOLDOWN_ULTIMATE = 10 * 60 * 1000; // 10 minutes cooldown
    private long lastUsedUlockTime = 0;

    public Knightmare() {
        super("Knightmare Corruptor Iblee", 6272, 50, 100, 1, 0.2);
    }

    @Override
    public void useSkill1(Character target) {
        if (System.currentTimeMillis() - lastUsedUlockTime >= COOLDOWN_ULTIMATE) {
            lastUsedUlockTime = System.currentTimeMillis();
            System.out.println(getName() + " uses U-lock: Setting Physical and Magic ATK of " + target.getName() + " to 0 for 1 minute.");
            int originalPhysicalDamage = target.getPhysicalDamage();
            int originalMagicPower = target.getMagicPower();
            target.setPhysicalDamage(0);
            target.setMagicPower(0);

            // Restore ATK after 1 minute
            new Thread(() -> {
                try {
                    Thread.sleep(60000); // Wait 1 minute
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                target.setPhysicalDamage(originalPhysicalDamage);
                target.setMagicPower(originalMagicPower);
                System.out.println("The effect of U-lock has expired. " + target.getName() + "'s ATK has been restored.");
            }).start();
        } else {
            System.out.println(getName() + " cannot use U-lock yet. Cooldown not over.");
        }
    }

    @Override
    public void useSkill2(Character target) {
        int damage = (int) (getPhysicalDamage() + getMagicPower());
        System.out.println(getName() + " uses her signature weapon, dealing " + damage + " damage.");
        target.takeDamage(damage);
    }

    @Override
    public void useSkill3() {
        System.out.println(getName() + " enters Corruption Form, gaining 20% more Physical and Magic ATK permanently.");
        setPhysicalDamage((int) (getPhysicalDamage() * 1.2)); // Increase Physical ATK by 20%
        setMagicPower((int) (getMagicPower() * 1.2)); // Increase Magic ATK by 20%
    }

    public void usePassive() {
        if (!isHealthRestored && getHp() <= 0.2 * 6272) {
            setHp(6272); // Restore full HP
            isHealthRestored = true; // Ensure this only triggers once per battle
            System.out.println(getName() + " restores full HP due to passive skill!");
        }
    }
    
        // Method to check if the Knightmare is dead
    public boolean isDead() {
        return getHp() <= 0;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        usePassive();
    }

    // Reset battle state for a new fight
    public void resetBattleState() {
        isHealthRestored = false; // Reset the passive trigger
    }
}