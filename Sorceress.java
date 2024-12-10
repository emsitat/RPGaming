public class Sorceress extends Hero {

    public Sorceress(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana) {
        super(name, hp, physicalDamage, magicPower, level, prot, maxMana);
    }


    @Override
    public void basicAttack(Character target) {
        int damage = getMagicPower();
        System.out.println(getName() + " launches a magical orb, dealing " + damage + " magic damage to " + target.getName());
        target.takeDamage(damage);
        setMana(getMana()+1);
        System.out.println(getName() + " gains 1 mana. Current mana: " + getMana() + "/" + getMaxMana());
    }

    @Override
    public void useUltimate(Character target) {
        if (canUseUltimate()) {
            // gây 200% sát thương phép trong phạm vi
            int damage = (int) (getMagicPower() * 2.0);
            System.out.println(getName() + " casts a meteor, dealing " + damage + " magic damage in a circular zone.");
            
            //cnay làm theo list target(sẽ fix lại)
            target.takeDamage(damage);
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
                {60, 10},  
                {70, 11},  
                {80, 12}, 
                {90, 13}, 
                {110, 15}  
            };

            int index = getLevel() - 1;
            setHp(statTable[index][0]);
            setMagicPower(statTable[index][1]); 

            System.out.println(getName() + " leveled up to " + getLevel() + "!");
        } else {
            System.out.println(getName() + " has reached max level.");
        }
    }
}
