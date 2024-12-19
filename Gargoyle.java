public class Gargoyle extends Boss {
    public Gargoyle() {
        super("Gargoyle", 2078, 40, 0, 1, 0.1);
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(getName() + " slams, dealing " + getPhysicalDamage() + " damage.");
        target.takeDamage(getPhysicalDamage());
    }

    @Override
    public void useSkill1(Character target) {
        double newProt = getProt() + 0.1; // Tăng 10% PROT
        setProt(Math.min(newProt, 1.0)); // Đảm bảo PROT không vượt quá 100%
        System.out.println(getName() + " uses Hardening, permanently gaining 10% PROT.");
    }

    @Override
    public void useSkill2(Character target) {
        System.out.println(getName() + " uses Slam, dealing 80 flat damage to all enemies.");
        target.takeDamage(80);
    }

}