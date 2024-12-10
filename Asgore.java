import java.util.ArrayList;
import java.util.List;

public class Asgore extends MiniBoss {
    private double prot;
    private int mana;
    private List<Character> cursedEnemies = new ArrayList<>();
    private static final int MAX_MANA = 30;
    private static final int FIRE_DAMAGE = 40;

    public Asgore() {
        super("Asgore", 100, 0, 40, 1);
        this.prot = 1.0;  // 100% PROT
        this.mana = 0; 
    }
    
    @Override
    public void useSkill1(Character target) {
        if (mana >= 20) {
            mana -= 20;
            cursedEnemies.add(target);
            System.out.println(getName() + " curses " + target.getName() + " with Endless Demise! They will be reduced to 1 HP in 30 seconds.");
        } else {
            System.out.println(getName() + " does not have enough mana for Endless Demise.");
        }
    }

    @Override
    public void useSkill2(Character target) {
        if (mana >= 15) {
            mana -= 15;
            System.out.println(getName() + " casts Fire Ring, dealing " + FIRE_DAMAGE + " magic damage to all enemies.");
        } else {
            System.out.println(getName() + " does not have enough mana for Fire Ring.");
        }
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = (int) (damage * (1 - prot));
        if (reducedDamage > 0) {
            super.takeDamage(reducedDamage);
        } else {
            System.out.println(getName() + " takes no damage due to 100% PROT.");
        }
    }

    public void applyEndlessDemise() {
        for (Character enemy : cursedEnemies) {
            System.out.println(enemy.getName() + " is cursed! Reducing their HP to 1.");
            enemy.setHp(1);
        }
        cursedEnemies.clear();
    }

    public void regenerateMana() {
        if (mana < MAX_MANA) {
            mana++;
            System.out.println(getName() + " regenerates 1 mana. Current mana: " + mana + "/" + MAX_MANA);
        }
    }
}
