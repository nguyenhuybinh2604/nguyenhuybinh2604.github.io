package view;

import java.util.Scanner;

import entity.Techmaster;
import handle.InputControl;
import handle.TechmasterHandle;

public class Menu {
    InputControl inputControl = new InputControl();
    Scanner sc = new Scanner(System.in);

    public int inputMain(Scanner sc) {
        System.out.println("Nhap lua chon cua ban (1-6):");
        System.out.println("1: Khoi tao TechMaster.");
        System.out.println("2: Xem thong tin TechMaster.");
        System.out.println("3: Them hoc vien.");
        System.out.println("4: Cap nhat hoc luc cua hoc vien.");
        System.out.println("5: Xoa hoc vien.");
        System.out.println("6: Thoat ra.");

        return inputControl.getInput(sc, 1, 6);
    }

    public void callMainMenu() {
        int input = inputMain(sc);
        Scanner sc = new Scanner(System.in);
        TechmasterHandle techmasterHandle = new TechmasterHandle();
        Techmaster techmaster = new Techmaster();
        while (input != 6) {
            // Xu ly tung lua chon
            switch (input) {
                case 1: {
                    techmaster = techmasterHandle.createTechmaster(sc);
                    break;
                }
                case 2: {
                    System.out.println(techmaster);
                    break;
                }
                case 3: {
                    techmasterHandle.addStudent(sc, techmaster);
                    break;
                }
                case 4: {
                    techmasterHandle.updateRank(sc, techmaster);
                    break;
                }
                case 5: {
                    techmasterHandle.removeStudent(sc, techmaster);
                    break;
                }
                case 6: {
                    // Exit
                    break;
                }
            }
            // Call lai main menu
            input = inputMain(sc);
        }
    }
}
