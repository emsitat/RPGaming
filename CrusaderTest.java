public class CrusaderTest {
    public static void main(String[] args) {
        Crusader crusader = new Crusader("Crusader", 150, 5, 5, 1, 0.1, 3);
        Character dummyEnemy = new Lich();

        crusader.basicAttack(dummyEnemy);
        crusader.basicAttack(dummyEnemy);
        crusader.basicAttack(dummyEnemy);
        dummyEnemy.basicAttack(crusader);
        crusader.useUltimate(dummyEnemy);
        crusader.displayCharacterInfo();

        try {
            Thread.sleep(35000); // Simulate game runtime for 35 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        crusader.shutdownSchedulers(); // Shutdown schedulers after test
        crusader.displayCharacterInfo();

    }
}