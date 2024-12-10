public abstract class Character {
    protected String name;
    protected int hp;
    protected int physicalDamage;
    protected int magicPower;
    protected int level;

    public Character(String name, int hp, int physicalDamage, int magicPower, int level) {
        this.name = name;
        this.hp = hp;
        this.physicalDamage = physicalDamage;
        this.magicPower = magicPower;
        this.level = level;
    }


    public void takeDamage(int damage) {
        setHp(getHp() - damage);

        if (getHp() > 0) {
            System.out.println(name + " takes " + damage + " damage. Remaining HP: " + hp);
        } else {
            System.out.println(name + " has died!");
        }
    }

    public void basicAttack(Character target) {
        System.out.println(name + " performs a basic attack on " + target.getName() + ", dealing " + physicalDamage + " damage.");
        target.takeDamage(physicalDamage);
    }

    public void displayCharacterInfo() {
        System.out.println("Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp);
        System.out.println("Physical Damage: " + physicalDamage);
        System.out.println("Magic Power: " + magicPower);
    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}


	public int getPhysicalDamage() {
		return physicalDamage;
	}


	public void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}


	public int getMagicPower() {
		return magicPower;
	}


	public void setMagicPower(int magicPower) {
		this.magicPower = magicPower;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}
}
