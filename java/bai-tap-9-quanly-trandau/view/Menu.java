package view;

import java.util.List;
import java.util.Scanner;

import entity.Product;
import handle.ProductHandle;
import handle.InputControl;

public class Menu {
    InputControl inputControl = new InputControl();
    Scanner sc = new Scanner(System.in);

    public int inputMain(Scanner sc) {
        System.out.println("Nhap lua chon cua ban (1-8):");
        System.out.println("1: Them san pham.");
        System.out.println("2: Xem danh sach san pham.");
        System.out.println("3: Tim san pham theo ten.");
        System.out.println("4: Tim san pham theo id.");
        System.out.println("5: Tim san pham co so luong <n.");
        System.out.println("6: Tim san pham theo muc gia.");
        System.out.println("7: Sap xep danh sach san pham.");
        System.out.println("8: Thoat ra.");

        return inputControl.getInput(sc, 1, 8);
    }

    public int inputPrice(Scanner sc) {
        System.out.println("Chon muc gia can filter (1-4):");
        System.out.println("1: Duoi 50,000.");
        System.out.println("2: Tu 50,000 den duoi 100,000.");
        System.out.println("3: Tu 100,000 tro len.");
        System.out.println("4: Thoat ra.");

        return inputControl.getInput(sc, 1, 4);
    }

    public int inputField(Scanner sc) {
        System.out.println("Lua chon truong de sap xep (1-7):");
        System.out.println("1: Theo Id.");
        System.out.println("2: Theo ten.");
        System.out.println("3: Theo mo ta.");
        System.out.println("4: Theo don vi tinh.");
        System.out.println("5: Theo so luong.");
        System.out.println("6: Theo gia.");
        System.out.println("7: Thoat ra.");

        return inputControl.getInput(sc, 1, 7);
    }

    public int inputSortDirection(Scanner sc) {
        System.out.println("Lua chon thu tu sap xep (1-2):");
        System.out.println("1: Tang dan.");
        System.out.println("2: Giam dan.");

        return inputControl.getInput(sc, 1, 2);
    }

    public void callMainMenu(ProductHandle productHandle, List<Product> products) {
        int input = inputMain(sc);
        while (input != 8) {
            // Xu ly tung lua chon
            switch (input) {
                case 1: {
                    productHandle.addRecords(sc, inputControl, products);
                    break;
                }
                case 2: {
                    productHandle.displayAll(products);
                    break;
                }
                case 3: {
                    productHandle.searchByName(sc, products);
                    break;
                }
                case 4: {
                    productHandle.searchById(sc, inputControl, products);
                    break;
                }
                case 5: {
                    productHandle.searchByMaxQuantity(sc, inputControl, products);
                    break;
                }
                case 6: {
                    int inputPrice = inputPrice(sc);
                    while (inputPrice != 4) {
                        // Thuc hien tim kiem
                        productHandle.searchByPrice(products, inputPrice);
                        // Sau khi tim kiem call lai menu chon muc gia
                        inputPrice = inputPrice(sc);
                    }
                    break;
                }
                case 7: {
                    int inputField = inputField(sc);
                    while (inputField!= 7) {
                        // Chon thu tu sap xep
                        int sortDirection = inputSortDirection(sc);
                        // Thuc hien sap xep
                        productHandle.sortArray(products, inputField, sortDirection);
                        // Sau khi sap xep call lai menu chon Field can sap xep
                        inputField = inputField(sc);
                    }
                    break;
                }
                case 8: {
                    //Exit
                    break;
                }
            }
            // Call lai main menu
            input = inputMain(sc);
        }
    }
}
