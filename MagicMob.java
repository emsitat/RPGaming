public class MagicMob extends Character {
    public MagicMob(String name, int hp, int physicalDamage, int magicPower, int level) {
        super(name, hp, physicalDamage, magicPower, level);
    }
    
    @Override
    public void basicAttack(Character target) {
        int damage = getMagicPower();
        System.out.println(getName() + " launches a magic-infused attack, dealing " + damage + " magic damage to " + target.getName());
        target.takeDamage(damage);
    }
}
