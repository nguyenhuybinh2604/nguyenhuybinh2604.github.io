package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Administrator;
import entity.Manager;
import entity.Marketer;
import handle.InputControl;
import handle.StaffHandle;
import io.InputData;
import service.IEmployee;

public class Menu {
    Scanner sc = new Scanner(System.in);
    InputControl inputControl = new InputControl();
    InputData inputData = new InputData();
    StaffHandle staffHandle = new StaffHandle();
    List<Administrator> administrators = new ArrayList<>();
    List<Manager> managers = new ArrayList<>();
    List<Marketer> marketers = new ArrayList<>();
    List<IEmployee> allEmployees = new ArrayList<>();

    public int inputMain() {
        System.out.println("WELCOME TO RONG VIET SECURITIES");
        System.out.println("1. Load data");
        System.out.println("2. View list of staff");
        System.out.println("3. Update staff info");
        System.out.println("4. Find staff by salary range");
        System.out.println("5. Sort staff by name or income");
        System.out.println("6. View top 5 earners");
        System.out.println("7. Exit");

        return inputControl.getInput(sc, 1, 7);
    }

    public void callMainMenu() {
        int input = inputMain();
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    inputData.readExcel(administrators, managers, marketers);
                    allEmployees = inputData.mergeData(administrators, managers, marketers);
                    break;
                }
                case 2 -> {
                    staffHandle.displayStaffInfo(allEmployees);
                    break;
                }
                case 3 -> {
                    updateStaffInfo();
                    break;
                }
                case 4 -> {
                    staffHandle.findEmployeebyIncome(sc, inputControl, allEmployees);
                    break;
                }
                case 5 -> {
                    sortStaff();
                    break;
                }
                case 6 -> {
                    staffHandle.viewTop5Earners(sc, inputControl, allEmployees);
                    break;
                }
                case 7 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call lai main menu
            input = inputMain();
        } while (input >= 1 && input <= 7);
    }

    public int inputUpdateInfo() {
        System.out.println("CHOOSE WHAT TO DO:");
        System.out.println("1. Delete staff");
        System.out.println("2. Edit info");
        System.out.println("3. Return");

        return inputControl.getInput(sc, 1, 3);
    }

    public void updateStaffInfo() {
        int input = inputUpdateInfo();
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    staffHandle.deleteStaff(sc, inputControl, allEmployees);
                    break;
                }
                case 2 -> {
                    staffHandle.editStaff(sc, inputControl, allEmployees);
                    break;
                }
                case 3 -> {
                    // Return
                    return;
                }
            }
            // Call lai main menu
            input = inputUpdateInfo();
        } while (input >= 1 && input <= 3);
    }

    public int inputSortField() {
        System.out.println("CHOOSE WHAT TO SORT");
        System.out.println("1. By name");
        System.out.println("2. By income");
        System.out.println("3. Return");

        return inputControl.getInput(sc, 1, 3);
    }

    public void sortStaff() {
        int input = inputSortField();
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    staffHandle.sortStaff(sc, inputControl, allEmployees, "name");
                    break;
                }
                case 2 -> {
                    staffHandle.sortStaff(sc, inputControl, allEmployees, "income");
                    break;
                }
                case 3 -> {
                    // Return
                    return;
                }
            }
            // Call lai main menu
            input = inputSortField();
        } while (input >= 1 && input <= 3);
    }

    public void exitProgram() {
        System.out.println("Exiting. Goodbye.");
        System.exit(0);
    }
}
