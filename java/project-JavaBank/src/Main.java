
import view.Menu;

public class Main {

    public static void main(String[] args) {


        // System.out.println(users.size());
        // System.out.println();

//        for (Map.Entry<String, Object> entry : users.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            System.out.println(key + ": (user: " + value.toString() + ")");
//        }
//
//        for (Product product:products) {
//            System.out.println(product.toString());
//        }
//
//        System.out.println(products.size());
        Menu menu = new Menu();
        menu.callMainMenu();
//        System.out.printf("%-6s%-30s%-30s%-15s\n","IDs","Name","Username","Credit rating");
    }
}