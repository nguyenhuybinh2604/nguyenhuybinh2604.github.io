import entity.Lucian;
import entity.Yasuo;

public class Main {
    public static void main(String[] args) {
        Yasuo yasuo = new Yasuo("Red Hair", "Sword");
        Lucian lucian = new Lucian("Red Hair", "Sword");
        System.out.println("Yasuo:");
        yasuo.surf();

        System.out.println("Lucian:");
        lucian.surf();
        lucian.shoot();
    }

    
}