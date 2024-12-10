public class Main {
    public static void main(String[] args) {
        Goblin goblin = new Goblin();
        
        // Tạo một đối tượng Character (mục tiêu bị tấn công)
        Crusader crusader = new Crusader("tat",100,5,0,1,0.3,30);
        
        // Hiển thị thông tin trước khi tấn công
        goblin.displayCharacterInfo();
        crusader.displayCharacterInfo();
        
        // Goblin thực hiện basic attack lên target
        goblin.basicAttack(crusader);
        
        // Hiển thị thông tin sau khi tấn công
        crusader.displayCharacterInfo();
        crusader.basicAttack(goblin);
        goblin.displayCharacterInfo();
    }
}
