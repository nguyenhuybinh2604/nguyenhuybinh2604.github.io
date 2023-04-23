package handle;

import java.util.List;
import java.util.Scanner;

import entity.Administrator;
import entity.Employee;
import entity.Manager;
import entity.Marketer;

public class StaffHandle {
    public <EStaff> void displayStaffInfo(List<EStaff> eStaffs) {
        for (EStaff eStaff : eStaffs) {
            System.out.println(eStaff);
        }
    }

    public Integer findManager(List<Manager> managers, String employeeId) {
        Integer returnValue = null;
        for (Manager manager : managers) {
            if (manager.getEmployeeId().equals(employeeId)) {
                returnValue = managers.indexOf(manager);
                break;
            }
        }
        return returnValue;
    }

    public Integer findAdministrator(List<Administrator> administrators, String employeeId) {
        Integer returnValue = null;
        for (Administrator administrator : administrators) {
            if (administrator.getEmployeeId().equals(employeeId)) {
                returnValue = administrators.indexOf(administrator);
                break;
            }
        }
        return returnValue;
    }

    public Integer findMarketer(List<Marketer> marketers, String employeeId) {
        Integer returnValue = null;
        for (Marketer marketer : marketers) {
            if (marketer.getEmployeeId().equals(employeeId)) {
                returnValue = marketers.indexOf(marketer);
                break;
            }
        }
        return returnValue;
    }

    public int inputTypeOfEmployee(Scanner sc, InputControl inputControl) {
        System.out.println("SELECT TYPE OF EMPLOYEE:");
        System.out.println("1: Administrative");
        System.out.println("2. Managers");
        System.out.println("3: Marketers");

        return inputControl.getInput(sc, 1, 3);
    }

    public void deleteStaff(Scanner sc, InputControl inputControl, List<Administrator> administrators,
            List<Manager> managers,
            List<Marketer> marketers) {
        int typeOfEmployee = inputTypeOfEmployee(sc, inputControl);
        System.out.println("Enter employee's Id:");
        String employeeId = sc.nextLine();
        switch (typeOfEmployee) {
            case 1: {
                if (findAdministrator(administrators, employeeId) != null) {
                    administrators.remove(findAdministrator(administrators, employeeId));
                    System.out.println("Staff Id " + employeeId + " has been removed.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
            case 2: {
                if (findManager(managers, employeeId) != null) {
                    managers.remove(findManager(managers, employeeId));
                    System.out.println("Staff Id " + employeeId + " has been removed.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
            case 3: {
                if (findMarketer(marketers, employeeId) != null) {
                    marketers.remove(findMarketer(marketers, employeeId));
                    System.out.println("Staff Id " + employeeId + " has been removed.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
        }
    }

    public void editStaff(List<Administrator> administrators, List<Manager> managers, List<Marketer> marketers) {

    }
}
