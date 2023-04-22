package view;

import java.util.Scanner;

import entity.Account;
import handle.AccountHandle;
import handle.InputControl;

public class Menu {

    public int inputMain(Scanner sc, InputControl inputControl) {
        System.out.println("Nhap lua chon cua ban:");
        System.out.println("1: Nap them tien.");
        System.out.println("2. Thay email.");
        System.out.println("3. Xem thong tin tai khoan.");
        System.out.println("4: Thoat chuong trinh.");

        return inputControl.getInput(sc, 1, 4);
    }

    public void callMainMenu(Scanner sc, InputControl inputControl, AccountHandle accountHandle, Account account) {
        int input = inputMain(sc, inputControl);
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    accountHandle.recharge(sc, account);
                    break;
                }
                case 2 -> {
                    accountHandle.changeEmail(sc, inputControl, account);
                    break;
                }
                case 3 -> {
                    account.displayInfo();
                }
                case 4 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call lai main menu
            input = inputMain(sc, inputControl);
        } while (input >= 1 && input <= 4);
    }

    public void exitProgram() {
        System.out.println("Thoat chuong trinh. Tam biet.");
        System.exit(0);
    }
}
