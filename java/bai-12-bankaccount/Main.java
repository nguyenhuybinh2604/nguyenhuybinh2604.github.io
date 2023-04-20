import java.util.Scanner;

import entity.Account;
import handle.AccountHandle;
import handle.InputControl;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InputControl inputControl = new InputControl();
        AccountHandle accountHandle = new AccountHandle();

        Account account = new Account();
        account = accountHandle.openAccount(sc, inputControl);
        Menu menu = new Menu();

        menu.callMainMenu(sc, inputControl, accountHandle, account);
    }
}