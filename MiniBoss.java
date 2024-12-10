public abstract class MiniBoss extends Character {
    public MiniBoss(String name, int hp, int physicalDamage, int magicPower,int level) {
        super(name, hp, physicalDamage, magicPower,level);
    }

    public abstract void useSkill1(Character target);
    public abstract void useSkill2(Character target);
}
