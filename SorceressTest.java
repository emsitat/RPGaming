public class SorceressTest {
    public static void main(String[] args) {
        Sorceress tat = new Sorceress("tat", 60, 1, 10, 1, 0, 1);
        Mob khải = new Goblin();
        tat.basicAttack(khải);
        khải.basicAttack(tat);
        tat.useUltimate(tat);
        tat.displayCharacterInfo();
        khải.displayCharacterInfo();
    }
}