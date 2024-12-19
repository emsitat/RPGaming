public class KnightmareTest {
    public static void main(String[] args) {
        // Create the boss and hero
        Knightmare knightmare = new Knightmare();
        Warrior hero = new Warrior("Brave Warrior", 500, 500, 0, 1, 0.1, 100);

        System.out.println("=== Battle Start ===");
        System.out.println(knightmare.getName() + " appears with " + knightmare.getHp() + " HP.");
        System.out.println(hero.getName() + " enters the battle with " + hero.getHp() + " HP.");

        // Hero hits the boss three times
        for (int i = 1; i <= 30; i++) {
            System.out.println("\n=== Turn " + i + " ===");
            hero.basicAttack(knightmare);
            System.out.println(knightmare.getName() + " has " + knightmare.getHp() + " HP remaining.");

            // Check if the boss has been defeated
            if (knightmare.isDead()) {
                System.out.println(knightmare.getName() + " has been defeated!");
                break;
            }
        }

        // Print the final state of the boss and hero
        System.out.println("\n=== Battle End ===");
        knightmare.displayCharacterInfo();
        hero.displayCharacterInfo();
    }
}