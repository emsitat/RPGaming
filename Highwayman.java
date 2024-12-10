public class Highwayman extends Hero {

    public Highwayman(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana) {
        super(name, hp, physicalDamage, magicPower, level, prot, maxMana);
    }

    @Override
    public void useUltimate(Character target) {
        if (canUseUltimate()) {
            if (target instanceof Boss) {
                Boss Target = (Boss) target;
                double newProt = Target.getProt() - 0.1;
                Target.setProt(Math.max(newProt, 0)); // Không cho PROT âm
                System.out.println(target.getName() + "'s PROT reduced by 10%.");
            }

            // Gây 200% sát thương vật lý
            int damage = (int) (getPhysicalDamage() * 2.0);
            target.takeDamage(damage);

            System.out.println(getName() + " shoots a silver bullet, dealing " + damage + " damage.");
            setMana(0); // Reset mana sau khi dùng Ultimate
        } else {
            System.out.println(getName() + " does not have enough mana to use the ultimate.");
        }
    }

    public void levelUp() {
        if (getLevel() < 5) {
            setLevel(getLevel() + 1);

            int[][] statTable = {
                {70, 12},  
                {75, 14},  
                {85, 16}, 
                {100, 18}, 
                {130, 20}  
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
