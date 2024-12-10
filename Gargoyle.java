public class Gargoyle extends MiniBoss {
	private double prot;
    public Gargoyle() {
        super("Gargoyle", 2078, 40, 0, 1);
        this.prot = 0.1;
    }

    @Override
    public void basicAttack(Character target) {
        System.out.println(getName() + " slams, dealing " + getPhysicalDamage() + " damage.");
        target.takeDamage(getPhysicalDamage());
    }
    
    @Override
    public void takeDamage(int damage) {
        int reducedDamage = (int) (damage * (1 - prot));  
        super.takeDamage(reducedDamage);  
    }    
    
    //chưa fix time 
    @Override
    public void useSkill1(Character target) {
        prot = prot +0.1;
        System.out.println(getName() + " uses Hardening, permanently gaining 10% PROT.");
    }

    @Override
    public void useSkill2(Character target) {
        System.out.println(getName() + " uses Slam, dealing 80 flat damage to all enemies.");
        target.takeDamage(80);
    }
}
