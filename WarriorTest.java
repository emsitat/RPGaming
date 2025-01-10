public class WarriorTest {
    public static void main(String[] args) {
        Warrior tat = new Warrior("MTS", 100, 7,0, 1, 0.2, 1);
        MagicMob khải = new Mimic();
        tat.basicAttack(khải);
        khải.basicAttack(tat);
        tat.useUltimate(khải);
        tat.displayCharacterInfo();

    }
}