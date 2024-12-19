public abstract class Boss extends Character {
    private double prot;

    public Boss(String name, int hp, int physicalDamage, int magicPower, int level, double prot) {
        super(name, hp, physicalDamage, magicPower, level);
        this.prot = prot;
    }

	public double getProt() {
		return prot;
	}

	public void setProt(double prot) {
		this.prot = prot;
	}
    public abstract void useSkill1(Character target);
    public abstract void useSkill2(Character target);
    public void useSkill3() {
    }
    @Override
    public void takeDamage(int damage) {
        int reducedDamage = (int) (damage * (1 - getProt()));
        super.takeDamage(reducedDamage);
    }
}

