public class Occultist extends Hero {

    public Occultist(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana) {
        super(name, hp, physicalDamage, magicPower, level, prot, maxMana);
    }

    // Override basic attack for Occultist's area damage (50% Physical ATK + 50% Magic ATK)
    @Override
    public void basicAttack(Character target) {
        int damage = (int) (0.5 * getPhysicalDamage() + 0.5 * getMagicPower());
        System.out.println(getName() + " deals " + damage + " area damage (50% physical + 50% magic) to " + target.getName());
        target.takeDamage(damage);
    }
    
    //Implement out phòng sẽ làm sau
    @Override
    public void useUltimate(Character target) {
        if (canUseUltimate()) {
            // Hồi phục 50% HP hiện tại
            int healAmount = (int) (0.5 * getHp());
            setHp(getHp() + healAmount);
            System.out.println(getName() + " uses ultimate and regains " + healAmount + " HP. Current HP: " + getHp());

            setMana(0);  // Reset 
        } else {
            System.out.println(getName() + " does not have enough mana to use the ultimate.");
        }
    }

    @Override
    public void levelUp() {
        if (getLevel() < 5) {
            setLevel(getLevel() + 1);

            int[][] statTable = {
                {30, 5},  
                {40, 6},  
                {55, 7}, 
                {70, 8}, 
                {100, 9}  
            };

            int index = getLevel() - 1;
            setHp(statTable[index][0]);
            setPhysicalDamage(statTable[index][1]);
            setMagicPower(statTable[index][1]); 

            System.out.println(getName() + " leveled up to " + getLevel() + "!");
        } else {
            System.out.println(getName() + " has reached max level.");
        }
    }
}
