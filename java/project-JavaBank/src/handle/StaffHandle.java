//package handle;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Scanner;
//
//import service.IUser;
////import service.SortByIncome;
////import service.SortByName;
//
//public class StaffHandle {
//
//    public void displayStaffInfo(List<IUser> employees) {
////        for (IUser iEmployee : employees) {
////            System.out.println(iEmployee);
////        }
//    }
//
//    public Integer findEmployee(List<IUser> employees, String employeeId) {
//        Integer returnValue = null;
////        for (IUser iEmployee : employees) {
////            if (iEmployee.getEmployeeId().equals(employeeId)) {
////                returnValue = employees.indexOf(iEmployee);
////                break;
////            }
////        }
//        return returnValue;
//    }
//
//    public void findEmployeebyIncome(Scanner sc, InputControl inputControl, List<IUser> employees) {
////        System.out.println("Please enter From value:");
////        double fromValue = inputControl.getInput(sc, 0, null);
////        System.out.println("Please enter To value:");
////        double toValue = inputControl.getInput(sc, 0, null);
////        System.out.println("Employees matching income range from " + fromValue + " to " + toValue + " :");
////        for (IUser iEmployee : employees) {
////            if (iEmployee.getIncome() >= fromValue && iEmployee.getIncome() <= toValue)
////                System.out.println(iEmployee);
////        }
//    }
//
//    public void deleteStaff(Scanner sc, InputControl inputControl, List<IUser> employees) {
//        // int typeOfEmployee = inputTypeOfEmployee(sc, inputControl);
//        System.out.println("Enter employee's Id:");
//        String employeeId = sc.nextLine();
//        if (findEmployee(employees, employeeId) != null) {
//            int staffIndex = findEmployee(employees, employeeId);
//            employees.remove(staffIndex);
//            System.out.println("Staff Id " + employeeId + " has been removed.");
//        } else
//            System.out.println("Staff Id not found.");
//    }
//
//    public int inputEditedField(Scanner sc, InputControl inputControl, String employeeId) {
//        System.out.println("SELECT FIELD TO EDIT:");
//        System.out.println("1. Name");
//        System.out.println("2. Age");
//        System.out.println("3. Address");
//        System.out.println("4. Basic salary");
//        int maxInput = 0;
//        switch (employeeId.substring(0, employeeId.length() - 3)) {
//            case "admin": {
//                maxInput = 4;
//                break;
//            }
//            case "manager": {
//                System.out.println("5. Role salary");
//                maxInput = 5;
//                break;
//            }
//            case "marketing": {
//                System.out.println("5. Sales");
//                System.out.println("6. Rate of bonus");
//                maxInput = 6;
//                break;
//            }
//        }
//        return inputControl.getInput(sc, 1, maxInput);
//    }
//
//    public void editSelectedField(Scanner sc, InputControl inputControl, List<IUser> employees, int staffIndex,
//            int selectedField) {
////        System.out.println("Enter new value:");
////        switch (selectedField) {
////            case 1: {
////                String newName = sc.nextLine();
////                employees.get(staffIndex).setName(newName);
////                break;
////            }
////            case 2: {
////                int newAge = inputControl.getInput(sc, 1, null);
////                employees.get(staffIndex).setAge(newAge);
////                break;
////            }
////            case 3: {
////                String newAddress = sc.nextLine();
////                employees.get(staffIndex).setAddress(newAddress);
////                break;
////            }
////            case 4: {
////                double newBasicSalary = inputControl.getInput(sc, 0, null);
////                employees.get(staffIndex).setSalaryBasic(newBasicSalary);
////                break;
////            }
////            case 5: {
////                String employeeId = employees.get(staffIndex).getEmployeeId();
////                if (employeeId.substring(0, employeeId.length() - 3).equals("manager")) {
////                    double newRoleSalary = inputControl.getInput(sc, 0, null);
////                    employees.get(staffIndex).setSalaryRole(newRoleSalary);
////                } else {
////                    int newSales = inputControl.getInput(sc, 0, null);
////                    employees.get(staffIndex).setSales(newSales);
////                }
////                break;
////            }
////            case 6: {
////                double newRateOfBonus = inputControl.getInput(sc, 0, 1);
////                employees.get(staffIndex).setRateOfBonus(newRateOfBonus);
////                break;
////            }
////        }
//    }
//
//    public void editStaff(Scanner sc, InputControl inputControl, List<IUser> employees) {
////        // int typeOfEmployee = inputTypeOfEmployee(sc, inputControl);
////        System.out.println("Enter employee's Id:");
////        String employeeId = sc.nextLine();
////        if (findEmployee(employees, employeeId) != null) {
////            int staffIndex = findEmployee(employees, employeeId);
////            int inputEditedField = inputEditedField(sc, inputControl, employeeId);
////            editSelectedField(sc, inputControl, employees, staffIndex, inputEditedField);
////            System.out.println("Staff Id " + employeeId + " has been edited.");
////        } else
////            System.out.println("Staff Id not found.");
//    }
//
//    public void sortStaff(Scanner sc, InputControl inputControl, List<IUser> employees, String sortField) {
//        switch (sortField) {
////            case "name": {
////                Collections.sort(employees, new SortByName());
////                System.out.println("After sorted by name:");
////                displayStaffInfo(employees);
////                break;
////            }
////            case "income": {
////                Collections.sort(employees, new SortByIncome());
////                System.out.println("After sorted by income:");
////                displayStaffInfo(employees);
////                break;
////            }
////        }
//    }
//
//    public void viewTop5Earners(Scanner sc, InputControl inputControl, List<IUser> employees) {
////        Collections.sort(employees, new SortByIncome());
////        System.out.println("Top 05 earners are:");
////        for (int i = 0; i < 5; i++) {
////            System.out.println(employees.get(i));
////        }
//    }
//}
