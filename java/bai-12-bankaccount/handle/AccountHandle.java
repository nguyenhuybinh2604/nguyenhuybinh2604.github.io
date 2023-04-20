package handle;

import java.util.Scanner;

import entity.Account;

public class AccountHandle {
    public Account openAccount(Scanner sc, InputControl inputControl) {
        
        System.out.println("Nhap ten cua ban:");
        String name = sc.nextLine();
        System.out.println("Nhap so tai khoan:");
        String accountNumber = sc.nextLine();
        System.out.println("Nhap email:");
        String email = inputControl.getEmail(sc);
        System.out.println("Tao tai khoan thanh cong.");
        Account returnValue = new Account(name, accountNumber, email, 0);
        return returnValue;
    }

    public void changeEmail(Scanner sc, InputControl inputControl, Account account) {
        System.out.println("Email hien tai:");
        System.out.println(account.getEmail());
        System.out.println("Nhap email moi (dang emailaddress@domain.com):");
        String newEmail = inputControl.getEmail(sc); // Doi thanh inputControl
        if (account.getEmail().equals(newEmail)) {
            System.out.println("Email da duoc su dung.");
        } else {
            // Thay email moi
            account.changeEmail(newEmail);
            System.out.println("Email changed.");
        }
    }

    public void recharge(Scanner sc, Account account) {
        System.out.println("Nhap so tien nap them:");       
        try {
            double addedAmount = Double.parseDouble(sc.nextLine());
            if (addedAmount >= 0)
                account.recharge(addedAmount);
            else
                System.out.println("Yeu cau nhap mot so >=0.");
        } catch (Exception e) {
            System.out.println("Nhap mot so hop le");
        }

    }

}
