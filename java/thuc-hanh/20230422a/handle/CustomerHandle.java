package handle;

import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

import entity.Customer;

public class CustomerHandle {

    public void createCustomer(Scanner sc, InputControl inputControl, List<Customer> customers) {
        System.out.println("Nhap ten khach hang su dung dien:");
        String name = inputControl.getString(sc, "ten khach hang");
        System.out.println("Nhap dia chi:");
        String address = sc.nextLine();
        System.out.println("Nhap ma so congto:");
        String counterId = inputControl.getString(sc, "ma so congto");
        if (findCounter(customers, counterId)==null) {
            Customer newCustomer = new Customer(name, address, counterId);
            customers.add(newCustomer);
        } else System.out.println("Ma so congto da duoc dang ky.");
    }

    public Integer findCounter(List<Customer> customers, String findValue) {
        Integer returnValue = null;
        for (Customer customer: customers) {
            if (customer.getCounterId().equals(findValue)) {
                returnValue = customers.indexOf(customer);
                break;
            }
        }
        return returnValue;
    }

    public void displayInfo(List<Customer> customers) {
        if (customers.size()>0)
        for (Customer customer: customers) 
            System.out.println(customer.toString());
        else System.out.println("Khong co khach hang nao.");
    }

    public void displayPayment(List<Customer> customers) {
        if (customers.size()>0)
        for (Customer customer: customers) 
            System.out.println(customer.getName() + ": " + customer.getPayableAmount());
        else System.out.println("Khong co khach hang nao.");
    }
}
