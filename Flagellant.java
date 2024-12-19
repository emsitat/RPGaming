public class Flagellant extends Hero {
    private long lastUltimateUseTime;
    private boolean inBattle;
    private int maxhp; // Maximum HP of the Flagellant

    public Flagellant(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana, int maxhp) {
        super(name, hp, physicalDamage, magicPower, level, prot, maxMana);
        this.lastUltimateUseTime = 0; // Cooldown for ultimate
        this.inBattle = true;
        this.maxhp = maxhp; // Assign maximum HP
    }

    // Getter for maxhp
    public int getMaxHP() {
        return maxhp;
    }

    // Basic Attack: Uses 1 HP to attack
    @Override
    public void basicAttack(Character target) {
        if (getHp() > 1) {  
            setHp(getHp() - 1);  
            int damage = getMagicPower();  
            System.out.println(getName() + " uses 1 HP to attack, dealing " + damage + " magic damage to " + target.getName());
            target.takeDamage(damage);  
        } else {
            System.out.println(getName() + " does not have enough HP to attack.");
        }
    }
    
    @Override
    public void useUltimate(Character target) {
        // Check cooldown for ultimate
        if (canUseUltimate()) {
            if (getHp() < maxhp * 0.3) {  // If HP is below 30%
                int hpRecovered = (int) ((maxhp - getHp()) * 0.4);  // Recover 40% of missing HP
                setHp(getHp() + hpRecovered);
                System.out.println(getName() + " recovers " + hpRecovered + " HP.");

                int magicDamage = 100;  // Deals 100 magic damage
                target.takeDamage(magicDamage);
                System.out.println(getName() + " deals " + magicDamage + " magic damage to " + target.getName());
            }
            lastUltimateUseTime = System.currentTimeMillis();  // Record the ultimate usage time
        } else {
            System.out.println(getName() + "'s ultimate is on cooldown.");
        }
    }

    // Check if the ultimate can be used
    public boolean canUseUltimate() {
        long currentTime = System.currentTimeMillis();
        return currentTime - lastUltimateUseTime >= 180000;  // 180,000ms = 3 minutes cooldown
    }

    // Passive: Regenerate 2 HP every 10 seconds during battle
    public void regenerateHealth() {
        if (inBattle) {
            setHp(Math.min(getHp() + 2, maxhp));  // Regenerate 2 HP but don't exceed max HP
            System.out.println(getName() + " regenerates 2 HP. Current HP: " + getHp());
        }
    }

    // Trigger this method every 10 seconds to activate passive during battle
    public void battleTick() {
        regenerateHealth();
    }

    // End the battle and stop regeneration
    public void endBattle() {
        inBattle = false;
    }
    
    public void levelUp() {
        if (getLevel() < 5) {
            setLevel(getLevel() + 1);

            int[][] statTable = {
                {90, 2},  
                {110, 4},  
                {130, 6}, 
                {150, 8}, 
                {170, 10}  
            };

            int index = getLevel() - 1;
            setHp(statTable[index][0]);
            setMagicPower(statTable[index][1]);

            // Update maxhp to reflect the new max HP after level up
            maxhp = statTable[index][0];

            System.out.println(getName() + " leveled up to " + getLevel() + "!");
        } else {
            System.out.println(getName() + " has reached max level.");
        }
    }
}