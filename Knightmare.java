public class Knightmare extends Boss {
    private boolean isHealthRestored = false;  // Kiểm tra xem đã kích hoạt phục hồi HP chưa
    private static final int COOLDOWN_ULTIMATE = 10 * 60 * 1000; // 10 minutes cooldown 
    private long lastUsedUlockTime = 0;
    private long lastUsedCorruptionFormTime = 0;
    
    public Knightmare() {
        super("Knightmare Corruptor Iblee", 6272, 50, 100, 1,0.2);
    }

    @Override
    public void useSkill1(Character target) {
        if (System.currentTimeMillis() - lastUsedUlockTime >= COOLDOWN_ULTIMATE) {
            lastUsedUlockTime = System.currentTimeMillis();
            System.out.println(getName() + " uses U-lock: Putting Physical and Magic ATK of " + target.getName() + " to 0 for 1 minute.");
            // Giảm ATK của kẻ địch xuống 0 trong 1 phút
            int a = target.getPhysicalDamage();
            int b = target.getMagicPower();
            target.setPhysicalDamage(0);
            target.setMagicPower(0);

            // Sau 1 phút, khôi phục lại giá trị ATK ban đầu
            new Thread(() -> {
                try {
                    Thread.sleep(60000);  // Đợi 1 phút
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                target.setPhysicalDamage(a);
                target.setMagicPower(b);
                System.out.println("The effect of U-lock has expired. " + target.getName() + "'s ATK has been restored.");
            }).start();
        } else {
            System.out.println(getName() + " cannot use U-lock yet. Cooldown not over.");
        }
    }

    @Override
    public void useSkill2(Character target) {
        int damage = (int) (getPhysicalDamage() + getMagicPower());
        System.out.println(getName() + " uses her signature weapon, dealing " + damage + " damage.");
        target.takeDamage(damage);
    }
    
    //fix time sau
    @Override
    public void useSkill3() {
        // Kỹ năng: Corruption form - Tăng 20% Physical ATK và Magic ATK
        System.out.println(getName() + " enters Corruption Form, gaining 20% more Physical and Magic ATK permanently.");
        setPhysicalDamage((int) (getPhysicalDamage() * 1.2)); // Tăng 20% Physical ATK
        setMagicPower((int) (getMagicPower() * 1.2)); // Tăng 20% Magic ATK
    }

    @Override
    public void usePassive() {
        // Passive: Bye, have a great time - Khi HP đạt 20%, phục hồi 100% HP
        if (!isHealthRestored && getHp() <= 0.2 * 6272) {
            setHp(6272); // Phục hồi toàn bộ HP
            isHealthRestored = true;
            System.out.println(getName() + " restores full HP due to passive skill!");
        }
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        usePassive();
    }
}
