import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Product;
import handle.ProductHandle;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.println("Nhap so luong san pham:");

        // Kiem soat gia tri la so khong am
        do {
            System.out.println("Nhap so luong (>=0): ");
            // Yeu cau gia tri la so
            while (!sc.hasNextInt()) {
                System.out.println("Hay nhap mot so (>=0): ");
                sc.nextLine();
            }
            n = Integer.parseInt(sc.nextLine());
        } while (n < 0);

        ProductHandle productHandle = new ProductHandle();
        
        List<Product> sections = new ArrayList <Product>();
        

        for (int i = 0; i < n; i++) {
            products[i] = productHandle.createProduct(sc, i);
        }

        Menu menu = new Menu();
        menu.requestInput(sc, productHandle, products);

    }
}