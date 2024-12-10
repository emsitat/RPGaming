public class Flagellant extends Hero {
    private long lastUltimateUseTime;
    private boolean inBattle;
    private int maxhp;

    public Flagellant(String name, int hp, int physicalDamage, int magicPower, int level, double prot, int maxMana,int maxhp) {
        super(name, hp, physicalDamage, magicPower, level, prot, maxMana);
        this.lastUltimateUseTime = 0; // thời gian cooldown cho Ulti
        this.inBattle = true; 
        this.maxhp = maxhp;
    }

    // Basic Attack: Sử dụng 1 HP để tấn công
    @Override
    public void basicAttack(Character target) {
        if (getHp() > 1) {  
            setHp(getHp() - 1);  
            int damage = getMagicPower();  
            System.out.println(getName() + " uses 1 HP to attack, dealing " + damage + " magic damage to " + target.getName());
            target.takeDamage(damage);  
        } else {
            System.out.println(getName() + " does not have enough HP to attack.");
        }
    }
    
    @Override
    public void useUltimate(Character target) {
        // Kiểm tra điều kiện cooldown
        if (canUseUltimate()) {
            if (getHp() < maxhp * 0.3) {  // Nếu HP dưới 30%
                int hpRecovered = (int) ((maxhp - getHp()) * 0.4);  // Hồi phục 40% HP đã mất
                setHp(getHp() + hpRecovered);
                System.out.println(getName() + " recovers " + hpRecovered + " HP.");

                int magicDamage = 100;  // Gây 100 magic damage
                target.takeDamage(magicDamage);
                System.out.println(getName() + " deals " + magicDamage + " magic damage to " + target.getName());
            }
            lastUltimateUseTime = System.currentTimeMillis();  // Đặt thời gian sử dụng ultimate
        } else {
            System.out.println(getName() + "'s ultimate is on cooldown.");
        }
    }

    // Kiểm tra xem ultimate có thể được sử dụng hay không
    public boolean canUseUltimate() {
        long currentTime = System.currentTimeMillis();
        return currentTime - lastUltimateUseTime >= 180000;  // 180000ms = 3 phút cooldown
    }

    // Passive: Hồi phục 2 HP mỗi 10 giây khi trong trận chiến
    public void regenerateHealth() {
        if (inBattle) {
            setHp(getHp() + 2);  // Hồi phục 2 HP
            System.out.println(getName() + " regenerates 2 HP. Current HP: " + getHp());
        }
    }

    // Gọi phương thức này mỗi 10 giây để kích hoạt Passive trong trận chiến
    public void battleTick() {
        regenerateHealth();
    }
    
    //for (int i = 0; i < 5; i++) {
        //flagellant.battleTick();  // Simulate a battle tick every 10 seconds

    // Cập nhật trạng thái khi ra khỏi trận chiến
    public void endBattle() {
        inBattle = false;
    }
    
    public void levelUp() {
        if (getLevel() < 5) {
            setLevel(getLevel() + 1);

            int[][] statTable = {
                {90, 2},  
                {110, 4},  
                {130, 6}, 
                {150, 8}, 
                {170, 10}  
            };

            int index = getLevel() - 1;
            setHp(statTable[index][0]);
            setMagicPower(statTable[index][1]);

            System.out.println(getName() + " leveled up to " + getLevel() + "!");
        } else {
            System.out.println(getName() + " has reached max level.");
        }
    }
}