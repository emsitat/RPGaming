public class WarriorTest {
    public static void main(String[] args) {
        Warrior tat = new Warrior("MTS", 100, 7,0, 1, 0.2, 1);
        Mob khải = new Troll();
        tat.basicAttack(khải);
        khải.basicAttack(tat);
        tat.useUltimate(khải);
        tat.displayCharacterInfo();

    }
}