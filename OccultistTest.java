public class OccultistTest {
    public static void main(String[] args) {
        Occultist tat = new Occultist("tat", 30, 5, 5, 1, 0, 1);
        Mob khải = new Goblin();
        tat.basicAttack(khải);
        khải.basicAttack(tat);
        tat.displayCharacterInfo();
        tat.useUltimate(khải);
        tat.displayCharacterInfo();
    }
}