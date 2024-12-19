public class Minotaur extends Boss {

    public Minotaur() {
        super("Minotaur", 1378, 47, 0, 1,0);
    }

    @Override
    public void useSkill1(Character target) {
        if (getHp() < 1378 * 0.25) {
            setPhysicalDamage((int) (getPhysicalDamage() * 1.3));
            System.out.println(getName() + " becomes furious! Physical ATK increased by 30%.");
        }
    }

    @Override
    public void useSkill2(Character enemy) {
    	System.out.println(getName() + " steals 10% health from " + enemy.getName() + " upon encounter!");
        
        int stolenHp = (int) (enemy.getHp() * 0.1);
        enemy.setHp(enemy.getHp() - stolenHp); // Giảm HP của đối thủ
        setHp(getHp() + stolenHp); // Tăng HP của Minotaur
        
        System.out.println(enemy.getName() + " loses " + stolenHp + " HP. Current HP: " + enemy.getHp());
        System.out.println(getName() + "'s HP increased to " + getHp() + ".");
    }
}