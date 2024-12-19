public class Warrior extends Hero {

    public Warrior(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana) {
        super(name, hp, physicalDamage, magicPower, level, prot, maxMana);
    }

    @Override
    public void useUltimate(Character target) {
        if (canUseUltimate()) {

            // dame increased
            int damage = (int) (getPhysicalDamage() * 1.5);
            target.takeDamage(damage);

            System.out.println(getName() + " executes a forward slam, dealing " + damage +"damage");
            setMana(0); // Reset  
        } else {
            System.out.println(getName() + " does not have enough mana to use the ultimate.");
        }
    }

    @Override
    public void levelUp() {
        if (getLevel() < 5) {
            setLevel(getLevel() + 1);

            int[][] statTable = {
                {100, 7},  
                {110, 9},  
                {120, 11}, 
                {130, 13}, 
                {150, 15}  
            };

            int index = getLevel() - 1;
            setHp(statTable[index][0]);
            setPhysicalDamage(statTable[index][1]);

            System.out.println(getName() + " leveled up to " + getLevel() + "!");
        } else {
            System.out.println(getName() + " has reached max level.");
        }
    }
}