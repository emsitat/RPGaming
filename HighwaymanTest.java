public class HighwaymanTest {
    public static void main(String[] args) {
        Highwayman highwayman = new Highwayman("khải",70,12,0,1, 0.1,2);
        Boss gargoyle = new Gargoyle();
        highwayman.basicAttack(gargoyle);
        highwayman.basicAttack(gargoyle);
        highwayman.useUltimate(gargoyle);
        gargoyle.displayCharacterInfo();
    }
}