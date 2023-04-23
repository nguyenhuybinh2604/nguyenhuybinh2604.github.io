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

    // public Integer testFind(List<Employee> employees, String findString) {
    // Integer returnValue = null;
    // for (Employee employee : employees) {
    // if (employee.getEmployeeId().equals(findString)) {
    // returnValue = employees.indexOf(employee);
    // break;
    // }
    // }
    // return returnValue;
    // }

    // public void testVoid (Scanner sc, InputControl inputControl,
    // List<Administrator> administrators,
    // List<Manager> managers,
    // List<Marketer> marketers) {
    // Integer testInt = testFind(administrators, "admin001");
    // }

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
        System.out.println("1. Administrative");
        System.out.println("2. Managers");
        System.out.println("3. Marketers");

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
                    int staffIndex = findAdministrator(administrators, employeeId);
                    administrators.remove(staffIndex);
                    System.out.println("Staff Id " + employeeId + " has been removed.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
            case 2: {
                if (findManager(managers, employeeId) != null) {
                    int staffIndex = findManager(managers, employeeId);
                    managers.remove(staffIndex);
                    System.out.println("Staff Id " + employeeId + " has been removed.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
            case 3: {
                if (findMarketer(marketers, employeeId) != null) {
                    int staffIndex = findMarketer(marketers, employeeId);
                    marketers.remove(staffIndex);
                    System.out.println("Staff Id " + employeeId + " has been removed.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
        }
    }

    public int inputEditedField(Scanner sc, InputControl inputControl, String selectedClass) {
        System.out.println("SELECT FIELD TO EDIT:");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Address");
        System.out.println("4. Basic salary");
        int maxInput = 0;
        switch (selectedClass) {
            case "Administrator": {
                maxInput = 4;
                break;
            }
            case "Manager": {
                System.out.println("5. Role salary");
                maxInput = 5;
                break;
            }
            case "Marketer": {
                System.out.println("5. Sales");
                System.out.println("6. Rate of bonus");
                maxInput = 6;
                break;
            }
        }
        return inputControl.getInput(sc, 1, maxInput);
    }

    public void editSelectedField(Scanner sc, InputControl inputControl, List<Administrator> administrators,
            List<Manager> managers, List<Marketer> marketers, String selectedClass, int staffIndex, int selectedField) {
        System.out.println("Enter new value:");
        switch (selectedField) {
            case 1: {
                String newName = sc.nextLine();
                if (selectedClass.equals("Administrator"))
                    administrators.get(staffIndex).setName(newName);
                else if (selectedClass.equals("Manager"))
                    managers.get(staffIndex).setName(newName);
                else
                    marketers.get(staffIndex).setName(newName);
                break;
            }
            case 2: {
                int newAge = inputControl.getInput(sc, 1, null);
                if (selectedClass.equals("Administrator"))
                    administrators.get(staffIndex).setAge(newAge);
                else if (selectedClass.equals("Manager"))
                    managers.get(staffIndex).setAge(newAge);
                else
                    marketers.get(staffIndex).setAge(newAge);
                break;
            }
            case 3: {
                String newAddress = sc.nextLine();
                if (selectedClass.equals("Administrator"))
                    administrators.get(staffIndex).setAddress(newAddress);
                else if (selectedClass.equals("Manager"))
                    managers.get(staffIndex).setAddress(newAddress);
                else
                    marketers.get(staffIndex).setAddress(newAddress);
                break;
            }
            case 4: {
                double newBasicSalary = inputControl.getInput(sc, 0, null);
                if (selectedClass.equals("Administrator"))
                    administrators.get(staffIndex).setSalaryBasic(newBasicSalary);
                else if (selectedClass.equals("Manager"))
                    managers.get(staffIndex).setSalaryBasic(newBasicSalary);
                else
                    marketers.get(staffIndex).setSalaryBasic(newBasicSalary);
                break;
            }
            case 5: {
                if (selectedClass.equals("Manager")) {
                    double newRoleSalary = inputControl.getInput(sc, 0, null);
                    managers.get(staffIndex).setSalaryRole(newRoleSalary);
                } else {
                    int newSales = inputControl.getInput(sc, 0, null);
                    marketers.get(staffIndex).setSales(newSales);
                }
                break;
            }
            case 6: {
                double newRateOfBonus = inputControl.getInput(sc, 0, 1);
                marketers.get(staffIndex).setRateOfBonus(newRateOfBonus);
                break;
            }
        }
    }

    public void editStaff(Scanner sc, InputControl inputControl, List<Administrator> administrators,
            List<Manager> managers, List<Marketer> marketers) {
        int typeOfEmployee = inputTypeOfEmployee(sc, inputControl);
        System.out.println("Enter employee's Id:");
        String employeeId = sc.nextLine();
        switch (typeOfEmployee) {
            case 1: {
                if (findAdministrator(administrators, employeeId) != null) {
                    int staffIndex = findAdministrator(administrators, employeeId);
                    int inputEditedField = inputEditedField(sc, inputControl, "Administrator");
                    editSelectedField(sc, inputControl, administrators, managers, marketers, "Administrator",
                            staffIndex, inputEditedField);
                    System.out.println("Staff Id " + employeeId + " has been edited.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
            case 2: {
                if (findManager(managers, employeeId) != null) {
                    int staffIndex = findManager(managers, employeeId);
                    int inputEditedField = inputEditedField(sc, inputControl, "Manager");
                    editSelectedField(sc, inputControl, administrators, managers, marketers, "Manager",
                            staffIndex, inputEditedField);
                    System.out.println("Staff Id " + employeeId + " has been edited.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
            case 3: {
                if (findMarketer(marketers, employeeId) != null) {
                    int staffIndex = findMarketer(marketers, employeeId);
                    int inputEditedField = inputEditedField(sc, inputControl, "Marketer");
                    editSelectedField(sc, inputControl, administrators, managers, marketers, "Marketer",
                            staffIndex, inputEditedField);
                    System.out.println("Staff Id " + employeeId + " has been edited.");
                } else
                    System.out.println("Staff Id not found.");
                break;
            }
        }
    }
}
