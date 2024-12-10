
public class DarkElf extends Mob {
	public DarkElf() {
		super("DarkElf",160,32,0,1);
	}
	@Override
    public void basicAttack(Character target) {
        super.basicAttack(target);
        // Check passive skill
       // executeIfPossible(target);
    }

    // Phương thức Passive: Execute kẻ địch có HP < 20% max HP
	//thêm thuộc tính MaxHp() sau
    //private void executeIfPossible(Character target) {
        //if (target.getHp() < target.getMaxHp() * 0.2) {
            //System.out.println(getName() + " executes " + target.getName() + " with a deadly strike!");
            //target.setHp(0); 
        }
    }
}
