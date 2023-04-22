import java.util.ArrayList;
import java.util.List;

import entity.AdminEmployee;
import entity.Customer;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        List<AdminEmployee> bills = new ArrayList<>();
        Menu menu = new Menu();
        menu.callMainMenu(customers, bills);
    }
}