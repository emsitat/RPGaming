public class AsgoreTest {
    public static void main(String[] args) {
        Asgore asgore = new Asgore();

        Character player = new Highwayman("tat", 130, 20, 0, 5,0.1, 20);

        System.out.println("Battle starts! Survive for 10 minutes to win!");

        // Mô phỏng hành động
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(5000); // Mỗi hành động cách nhau 5 giây
                System.out.println(player.getName() + " attacks Asgore!");
                asgore.takeDamage(20);

                asgore.regenerateMana();

                if (asgore.isDead()) {
                    asgore.endBattle();
                    System.out.println(player.getName() + " has defeated Asgore!");
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}