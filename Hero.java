public abstract class Hero extends Character {
    private double prot;  // Giảm dame
    private int mana;
    private int maxMana;

    public Hero(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana) {
        super(name, hp, physicalDamage, magicPower, level);
        this.prot = prot;
        this.mana = 0;
        this.maxMana = maxMana;
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = (int) (damage * (1 - prot));  // giảm dame
        super.takeDamage(reducedDamage);  
    }

    @Override
    public void basicAttack(Character target) {
        super.basicAttack(target);
        mana++;  // Hero tăng mana sau mỗi dame
        System.out.println(name + " gains 1 mana. Current mana: " + mana + "/" + maxMana);
    }
    
    // Check if ultimate can be used
    public boolean canUseUltimate() {
        return mana >= maxMana;
    }
    
    public abstract void useUltimate(Character target);
    
    public abstract void levelUp();

	public double getProt() {
		return prot;
	}

	public void setProt(double prot) {
		this.prot = prot;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	@Override
	public void displayCharacterInfo() {
	       System.out.println("Name: " + name);
	       System.out.println("Level: " + level);
	       System.out.println("HP: " + hp);
	       System.out.println("Physical Damage: " + physicalDamage);
	       System.out.println("Magic Power: " + magicPower);
	       System.out.println("Max mana " +maxMana);
	       System.out.println("Magic Power: " + magicPower);
	       System.out.println("Prot " + prot*100 +"%");
	       
	    }
}
