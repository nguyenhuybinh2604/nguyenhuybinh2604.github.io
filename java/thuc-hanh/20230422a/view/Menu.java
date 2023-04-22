package view;

import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import entity.AdminEmployee;
import entity.Customer;
import handle.BillHandle;
import handle.CustomerHandle;
import handle.InputControl;

public class Menu {
    CustomerHandle customerHandle = new CustomerHandle();
    BillHandle billHandle = new BillHandle();
    InputControl inputControl = new InputControl();
    Scanner sc = new Scanner(System.in);

    public int inputMain(Scanner sc) {
        System.out.println("Nhap lua chon cua ban (1-5):");
        System.out.println("1: Them ho su dung dien.");
        System.out.println("2: Xem thong tin ho su dung dien.");
        System.out.println("3: Nhap thong tin bien lai.");
        System.out.println("4: Tinh tien dien phai tra.");
        System.out.println("5: Thoat ra.");

        return inputControl.getInput(sc, 1, 5);
    }

    public void callMainMenu(List<Customer> customers, List<AdminEmployee> bills) {
        int input = inputMain(sc);
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1: {
                    customerHandle.createCustomer(sc, inputControl, customers);
                    break;
                }
                case 2: {
                    customerHandle.displayInfo(customers);
                    break;
                }
                case 3: {
                    billHandle.inputInfo(sc, customerHandle, inputControl, customers);
                    break;
                }
                case 4: {
                    customerHandle.displayPayment(customers);
                    break;
                }
                case 5: {
                    //Exit
                    return;
                }
            }
                input = inputMain(sc);
            } while (input <= 5 && input >=1);
            // Call lai main menu            
    }
}
