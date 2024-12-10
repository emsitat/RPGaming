public class Crusader extends Hero {
	 private long protEndTime;

    public Crusader(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana) {
        super(name, hp, physicalDamage, magicPower, level, prot, maxMana);
    }

    // Override useUltimate for Crusader's unique ultimate ability
    @Override
    public void useUltimate(Character target) {
        if (canUseUltimate()) {
        	setHp((int) (getHp() * 1.3));
        	
        	setProt(getProt()+0.3);
        	
        	protEndTime = System.currentTimeMillis() + 30000; // PROT lasts for 30 seconds
            System.out.println(getName() + " uses their ultimate ability. Restored 30% HP and gained 30% PROT for 30 seconds.");
            setMana(0); // Reset mana after using the ultimate
        } else {
            System.out.println(getName() + " does not have enough mana to use the ultimate.");
        }
    }
    
 // Method to update the Crusader's PROT status, called every game tick
    public void updateProtection() {
        if (System.currentTimeMillis() > protEndTime) {
            setProt(getProt() - 0.3); // Remove the 30% PROT after 30 seconds
            System.out.println(getName() + "'s PROT effect has ended.");
        }
    }

    // Custom levelUp method for Crusader
    @Override
    public void levelUp() {
        if (getLevel() < 5) {
            setLevel(getLevel() + 1);
            int[][] statTable = {{100, 5}, {110, 6}, {130, 7}, {160, 8}, {200, 10}};
            int newLevelIndex = Math.min(getLevel() - 1, statTable.length - 1);
            setHp(statTable[newLevelIndex][0]);
            setPhysicalDamage(statTable[newLevelIndex][1]);

            System.out.println(getName() + " leveled up to " + getLevel() + "!");
        } else {
            System.out.println(getName() + " has reached max level.");
        }
    }
}
