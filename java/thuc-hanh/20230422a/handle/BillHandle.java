package handle;

import java.util.List;
import java.util.Scanner;

import entity.AdminEmployee;
import entity.Customer;

public class BillHandle {
    public void inputInfo(Scanner sc, CustomerHandle customerHandle, InputControl inputControl,
            List<Customer> customers) {
        System.out.println("Nhap ma cong to:");
        String counterId = sc.nextLine();
        if (customerHandle.findCounter(customers, counterId) != null) {
            int index = customerHandle.findCounter(customers, counterId);
            System.out.println("Nhap so cu:");
            double previousNumber = inputControl.getInput(sc, 0, null);
            System.out.println("Nhap so moi:");
            double currentNumber = inputControl.getInput(sc, 0, null);
            AdminEmployee newBill = new AdminEmployee(previousNumber, currentNumber);
            customers.get(index).bills.add(newBill);
            customers.get(index).setPayableAmount();
        } else
            System.out.println("Khong tim thay khach hang.");
    }


}
