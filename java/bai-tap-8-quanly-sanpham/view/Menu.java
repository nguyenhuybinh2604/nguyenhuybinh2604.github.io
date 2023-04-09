package view;

import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.RunElement;

import entity.Product;
import handle.ProductHandle;

public class Menu {
    public int inputMainMenu(Scanner sc) {
        int input;
        do {
            System.out.println("Nhap lua chon cua ban (1-6): ");
            System.out.println("1: Xem danh sach san pham.");
            System.out.println("2: Tim san pham theo ten.");
            System.out.println("3: Tim san pham theo id.");
            System.out.println("4: Tim san pham co so luong <n.");
            System.out.println("5: Tim san pham theo muc gia.");
            System.out.println("6: Sap xep danh sach san pham.");
            System.out.println("7: Thoat ra.");
            
            // Yeu cau gia tri la so
            while (!sc.hasNextInt()) {
                System.out.println("Hay nhap mot so (1-6): ");
                sc.nextLine();
            }
            
            // Truyen lua chon vao
            input = Integer.parseInt(sc.nextLine());
        } while (input < 1 || input > 6);
        return input;
    }

public void callMainMenu() {

            // Xu ly tung lua chon
            switch (input) {
                case 1: {
                    productHandle.displayAllProducts(products);
                    break;
                }
                case 2: {
                    System.out.println("Nhap ten san pham muon tim:");
                    String name = sc.nextLine();
                    productHandle.searchProductByName(products, name);
                    break;
                }
                case 3: {
                    int id;

                    // Kiem soat gia tri la so khong am
                    do {
                        System.out.println("Nhap so id san pham muon tim (>=0): ");
                        // Yeu cau gia tri la so
                        while (!sc.hasNextInt()) {
                            System.out.println("Hay nhap mot so (>=0): ");
                            sc.nextLine();
                        }
                        id = Integer.parseInt(sc.nextLine());
                    } while (id < 0);

                    productHandle.searchById(products, sc, id);
                    break;
                }
                case 4: {
                    int n;
                    // Kiem soat gia tri la so khong am
                    do {
                        System.out.println("Nhap so id san pham muon tim (>0): ");
                        // Yeu cau gia tri la so
                        while (!sc.hasNextInt()) {
                            System.out.println("Hay nhap mot so (>0): ");
                            sc.nextLine();
                        }
                        n = Integer.parseInt(sc.nextLine());
                    } while (n <= 0);

                    productHandle.searchByMaxQuantity(products, n);
                    break;
                }
                case 5: {
                    // Nhap vao lua chon 1-4
                    do {
                        System.out.println("Nhap lua chon muc gia cua ban (1-6): ");
                        System.out.println("1: Duoi 50,000.");
                        System.out.println("2: Tu 50,000 den duoi 100,000.");
                        System.out.println("3: Tu 100,000 tro len");
                        System.out.println("4: Thoat ra.");
                        // Yeu cau gia tri la so
                        while (!sc.hasNextInt()) {
                            System.out.println("Hay nhap mot so (1-4): ");
                            sc.nextLine();
                        }
                        input = Integer.parseInt(sc.nextLine());
                    } while (input < 1 || input > 4);

                    // Call ham tim sp theo lua chon da nhap
                    productHandle.searchByPrice(products, input);
                    break;
                }
                case 6: {
                    break;
                }
            }

        } 

    }
}
