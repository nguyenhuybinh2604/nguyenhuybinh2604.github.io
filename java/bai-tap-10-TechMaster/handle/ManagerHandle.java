package handle;

import java.util.Scanner;

import entity.Manager;

public class ManagerHandle {
    public Manager createManager(Scanner sc){
        InputControl inputControl = new InputControl();
        System.out.println("Nhap ten cua quan ly:");
        String managerName = sc.nextLine();
        System.out.println("Nhap tuoi cua quan ly:");
        int managerAge = inputControl.getInput(sc, 1, null);
        return new Manager(managerName, managerAge);
    }
}
