package handle;

import entity.Customer;

import java.util.Map;
import java.util.Scanner;

public class CustomerHandle {
    public void viewInfo(Map<String, Object> users, String username) {
        Customer customer = (Customer) users.get(username);
        System.out.printf("%-6s%-30s%-30s%-15s\n", "IDs", "Name", "Username", "Credit rating");
        System.out.printf("%-6d%-30s%-30s%-15s\n", customer.getCustomerId(), customer.getName(), customer.getUsername(), customer.getCreditRating());
        System.out.println(customer.getProducts());
    }

    public void editInfo(Scanner sc, InputControl inputControl, Map<String, Object> users, int input, String username) {


        switch (input) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return;
            case 9:
                exi
                protected String personId; ->user input, nonblank, unique

                protected String email; ->user input, nonblank, unique

                private String password;->user input, nonblank
                protected String name; ->user input, nonblank
                protected String sex; ->user input, nonblank
                protected int age; ->user input, nonblank
                protected String address; ->user input, nonblank
        }
    }

}
