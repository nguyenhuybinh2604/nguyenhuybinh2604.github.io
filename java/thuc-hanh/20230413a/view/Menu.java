package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Worker;
import handle.InputControl;
import handle.PetHandle;

public class Menu {
    InputControl inputControl = new InputControl();
    Scanner sc = new Scanner(System.in);

    public int inputMain(Scanner sc) {
        System.out.println("==========Worker Management==========:");
        System.out.println("1: Xem danh sach cong nhan.");
        System.out.println("2: Them cong nhan.");
        System.out.println("3: Tang luong.");
        System.out.println("4: Giam luong.");
        System.out.println("5: Thoat ra.");

        return inputControl.getInput(sc, 1, 5);
    }

    public void callMainMenu() {
        int input = inputMain(sc);
        Scanner sc = new Scanner(System.in);
        List<Worker> workers = new ArrayList<>();
        List<Worker> salaryLog = new ArrayList<>();
        PetHandle workerHandle = new PetHandle();

        while (input != 5) {
            // Xu ly tung lua chon
            switch (input) {
                case 1: {
                    workerHandle.displayWorker(salaryLog);
                    break;
                }
                case 2: {
                    workerHandle.addWorker(sc, workers, salaryLog);
                    break;
                }
                case 3: {
                    workerHandle.salaryChange(sc, workers, salaryLog, 1);
                    break;
                }
                case 4: {
                    workerHandle.salaryChange(sc, workers, salaryLog, 2);
                    break;
                }
                case 5: {
                    // Exit
                    break;
                }
            }
            // Call lai main menu
            input = inputMain(sc);
        }
    }
}
