package handle;

import java.util.Scanner;

import entity.Product;

public class ProductHandle {
    
    // Khoi tao san pham va tra ve dang product
    public Product createProduct(Scanner sc, int i) {

        System.out.println("Nhap ten san pham thu " + (i + 1) + ": ");
        String name = sc.nextLine();

        System.out.println("Nhap mo ta san pham thu " + (i + 1) + ": ");
        String description = sc.nextLine();

        int quantity;
        double price;

        // Kiem soat gia tri la so khong am
        do {
            System.out.println("Nhap so luong (>=0): ");
            // Yeu cau gia tri la so
            while (!sc.hasNextInt()) {
                System.out.println("Hay nhap mot so (>=0): ");
                sc.nextLine();
            }
            quantity = Integer.parseInt(sc.nextLine());
        } while (quantity < 0);

        // Kiem soat gia tri la so khong am
        do {
            System.out.println("Nhap gia san pham (>=0): ");
            // Yeu cau gia tri la so
            while (!sc.hasNextDouble()) {
                System.out.println("Hay nhap mot so (>=0): ");
                sc.nextLine();
            }
            price = Double.parseDouble(sc.nextLine());
        } while (price < 0);

        System.out.println("Nhap don vi san pham thu " + (i + 1) + ": ");
        String unit = sc.nextLine();

        // Khoi tao ban ghi student theo tham so
        Product product = new Product(name, description, unit, quantity, price);

        // Tra ve ban ghi
        return product;
    }



    public void displayAllProducts(Product[] products) {
        for (Product pr : products) {
            System.out.println(pr);
        }
    }

    public void searchProductByName(Product[] products, String name) {
        int n = products.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                count++;
            }
        }
        if (count > 0) {
            System.out.println("So san pham co ten '" + name + "' la: " + count);
            System.out.println("Danh sach san pham co ten '" + name + "': ");
            for (int i = 0; i < n; i++) {
                if (products[i].getName().equalsIgnoreCase(name)) {
                    System.out.println(products[i]);
                }
            }
        } else {
            System.out.println("Khong tim thay san pham ten '" + name + "'.");
        }
    }

    public void searchById(Product[] products, Scanner sc, int id) {
        int n = products.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (products[i].getId() == id) {
                count++;
            }
        }
        if (count > 0) {
            System.out.println("San pham co id '" + id + "': ");
            for (int i = 0; i < n; i++) {
                if (products[i].getId() == id) {
                    System.out.println(products[i]);
                }
            }

            //Call phan cap nhat so luong san pham
            int quantity;
            // Kiem soat gia tri la so khong am
            do {
                System.out.println("Nhap so luong moi cho san pham (>=0): ");
                // Yeu cau gia tri la so
                while (!sc.hasNextInt()) {
                    System.out.println("Hay nhap mot so (>=0): ");
                    sc.nextLine();
                }
                quantity = Integer.parseInt(sc.nextLine());
            } while (quantity < 0);
            updateQuantity(products, id, quantity);
        } else {
            System.out.println("Khong tim thay san pham co id '" + id + "'.");
        }
    }

    public void updateQuantity(Product[] products, int id, int quantity) {
        int n = products.length;

        for (int i = 0; i < n; i++) {
            if (products[i].getId() == id) {
                products[i].setQuantity(quantity);
            }
        }
    }

    public void searchByMaxQuantity(Product[] products, int quantity) {
        int n = products.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (products[i].getQuantity() < quantity) {
                count++;
            }
        }
        if (count > 0) {
            System.out.println("So san pham co so luong duoi '" + quantity + "' la: " + count);
            System.out.println("Danh sach san pham co so luong duoi '" + quantity + "': ");
            for (int i = 0; i < n; i++) {
                if (products[i].getQuantity() < quantity) {
                    System.out.println(products[i]);
                }
            }
        } else {
            System.out.println("Khong tim thay san pham co so luong duoi '" + quantity + "'.");
        }
    }

    public void searchByPrice(Product[] products, int input) {
        int n = products.length;
        int count = 0;

        switch (input) {
            case 1: {
                for (int i = 0; i < n; i++) {
                    if (products[i].getPrice() < 50000) {
                        count++;
                    }
                }
                if (count > 0) {
                    System.out.println("So san pham co gia duoi 50,000 la: " + count);
                    System.out.println("Danh sach san pham co gia duoi 50,000: ");
                    for (int i = 0; i < n; i++) {
                        if (products[i].getPrice() < 50000) {
                            System.out.println(products[i]);
                        }
                    }
                } else {
                    System.out.println("Khong tim thay san pham co gia duoi 50,000.");
                }
                break;
            }

            case 2: {
                for (int i = 0; i < n; i++) {
                    if (products[i].getPrice() >= 50000 && products[i].getPrice() < 100000) {
                        count++;
                    }
                }
                if (count > 0) {
                    System.out.println("So san pham co gia tu 50,000 den duoi 100,000 la: " + count);
                    System.out.println("Danh sach san pham co gia tu 50,000 den duoi 100,000: ");
                    for (int i = 0; i < n; i++) {
                        if (products[i].getPrice() >= 50000 && products[i].getPrice() < 100000) {
                            System.out.println(products[i]);
                        }
                    }
                } else {
                    System.out.println("Khong tim thay san pham co gia tu 50,000 den duoi 100,000.");
                }
                break;
            }

            case 3: {
                for (int i = 0; i < n; i++) {
                    if (products[i].getPrice() >= 100000) {
                        count++;
                    }
                }
                if (count > 0) {
                    System.out.println("So san pham co gia tu 100,000 tro len la: " + count);
                    System.out.println("Danh sach san pham co gia tu 100,000 tro len: ");
                    for (int i = 0; i < n; i++) {
                        if (products[i].getPrice() >= 100000) {
                            System.out.println(products[i]);
                        }
                    }
                } else {
                    System.out.println("Khong tim thay san pham co gia tu 100,000 tro len.");
                }
                break;
            }

            case 4: {
                // Khong lam gi ca - exit
                break;
            }
        }

    }

}
