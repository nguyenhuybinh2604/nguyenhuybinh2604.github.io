package view;

import java.util.Map;
import java.util.Scanner;

import entity.UserRole;
import handle.InputControl;
import handle.UserHandle;

public class Menu {
    Scanner sc = new Scanner(System.in);
    InputControl inputControl = new InputControl();
    UserHandle userHandle = new UserHandle();
    String loginStatus = "";
    String activeUsername = "";

    public int inputMain() {
        System.out.println("WELCOME TO JAVABANK");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.println("3. Manager");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 3);
    }

    public void callMainMenu(Map<String, Object> users) {
        int input = inputMain();
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    callStartMenu(users, UserRole.CUSTOMER);
                    break;
                }
                case 2 -> {
                    callStartMenu(users, UserRole.STAFF);
                    break;
                }
                case 3 -> {
                    callStartMenu(users, UserRole.MANAGER);
                    break;
                }
                case 0 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call lai main menu
            input = inputMain();
        } while (input >= 0 && input <= 3);
    }

    public int inputStart(UserRole userRole) {
        int maxOption = 0;
        if (userRole == UserRole.MANAGER) {
            System.out.println("SELECT AN OPTION");
            System.out.println("1. Login");
            System.out.println("2. Return");
            System.out.println("0. Exit");
            maxOption = 2;
        } else { // role staff & customer co menu lua chon giong nhau
            System.out.println("SELECT AN OPTION");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Return");
            System.out.println("0. Exit");
            maxOption = 3;
        }
        return inputControl.getInput(sc, 0, maxOption);
    }

    public void callStartMenu(Map<String, Object> users, UserRole userRole) {
        int input = inputStart(userRole);
        do {
            // reset login status at begin of each loop
            loginStatus = "";
            if (input == 1) {
                do {
                    loginStatus = userHandle.login(sc, inputControl, users, userRole);
                    // get username in case of success or wrong password
                    int delimiterIndex = loginStatus.indexOf("_");
                    if (delimiterIndex >= 0)
                        activeUsername = loginStatus.substring(delimiterIndex + 1);
                    if (loginStatus.contains("loginSuccess")) {
                        loginSuccess(userRole, activeUsername);
                        break;
                    } else if (loginStatus.contains("loginFailureWrongPassword")) {
                        do {
                            loginStatus = loginFailure(sc, users, userRole, activeUsername);
                            delimiterIndex = loginStatus.indexOf("_");
                            if (delimiterIndex >= 0)
                                activeUsername = loginStatus.substring(delimiterIndex + 1);
                            if (loginStatus.contains("loginSuccess")) {
                                loginSuccess(userRole, activeUsername);
                                break;
                            }
                            // repeat login failure screen until either: 1/ user quit 2/ login success
                        } while (!loginStatus.contains("loginSuccess") && !loginStatus.contains("returnToStart"));
                    }
                } while (!loginStatus.contains("loginSuccess") && !loginStatus.contains("returnToStart")
                        && !loginStatus.contains("loginFailureWrongUsername")
                        && !loginStatus.contains("loginFailureWrongUserRole"));
            } else if (input == 2 && userRole != UserRole.MANAGER)
                userHandle.register(sc, inputControl, users, userRole);
            else if (input == 2 && userRole == UserRole.MANAGER)
                return;
            else if (input == 3 && userRole != UserRole.MANAGER)
                return;
            else if (input == 3 && userRole == UserRole.MANAGER) {
                // Exit
                exitProgram();
            } else if (input == 0) {
                // Exit
                exitProgram();
            }
            // recall main menu input
            input = inputStart(userRole);
        } while (input >= 0 && input <= 3);

    }

    public int inputLoginFailure(Scanner sc) {
        System.out.println("LOG IN FAILED. SELECT AN OPTION");
        System.out.println("1: Re-log in");
        System.out.println("2: Forgot password");
        System.out.println("3: Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 3);
    }

    public String loginFailure(Scanner sc, Map<String, Object> users, UserRole userRole, String username) {
        int input = loginStatusToInt(loginStatus);
        do {
            switch (input) {
                case 1 -> {
                    loginStatus = userHandle.login(sc, inputControl, users, userRole);
                    return loginStatus;
                }
                case 2 -> {
                    loginStatus = userHandle.forgotPassword(sc, inputControl, users, username);
                    return loginStatus;
                }
                case 3 -> {
                    // Return to previous menu
                    loginStatus = "returnToStart";
                    return loginStatus;
                }
                case 0 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Set option for next loginFailed menu
            input = loginStatusToInt(loginStatus);
        } while (input >= 0 && input <= 3);
        return loginStatus;
    }

    public int loginStatusToInt(String loginStatus) {
        if (!loginStatus.contains("loginSuccess"))
            return inputLoginFailure(sc);
        else
            // if either success or user quits, go to 3 (prev screen)
            return 3;
    }

    public void loginSuccess(UserRole userRole, String username) {
        switch (userRole) {
            case CUSTOMER: {
                callCustomerMenu(username);
                break;
            }
            case STAFF: {
                callStaffMenu(username);
                break;
            }
            case MANAGER: {
                callManagerMenu(username);
                break;
            }
        }
    }

    public int inputCustomer() {
        System.out.println("SELECT AN OPTION");
        System.out.println("1. View info");
        System.out.println("2. Add balance");
        System.out.println("3. Apply for loans");
        System.out.println("4. Open savings");
        System.out.println("5. Foreign exchange");
        System.out.println("6. Fund transfer");
        System.out.println("7. Transaction history");
        System.out.println("8. Edit personal info");
        System.out.println("9. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 9);
    }

    public void callCustomerMenu(String username) {
        int input = inputCustomer();
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    // View customer info
                    break;
                }
                case 2 -> {
                    // add balance
                    break;
                }
                case 3 -> {
                    // borrow
                    break;
                }
                case 4 -> {
                    // open savings
                    break;
                }
                case 5 -> {
                    // foreign exchange
                    break;
                }
                case 6 -> {
                    // fund transfer
                    break;
                }
                case 7 -> {
                    // view transaction history
                    break;
                }
                case 8 -> {
                    // Edit personal info
                    return;
                }
                case 9 -> {
                    // Return to previous menu
                    return;
                }
                case 0 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call input options
            input = inputCustomer();
        } while (input >= 0 && input <= 9);
    }

    public int inputStaff() {
        System.out.println("SELECT AN OPTION");
        System.out.println("1. View customer info");
        System.out.println("2. Approve loan request(s)");
        System.out.println("3. Update credit rating");
        System.out.println("4. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 4);
    }

    public void callStaffMenu(String username) {
        int input = inputStaff();
        do {
            switch (input) {
                case 1 -> {
                    // View customer info
                    break;
                }
                case 2 -> {
                    // approve loans
                    break;
                }
                case 3 -> {
                    // update credit rating
                    break;
                }
                case 4 -> {
                    // Return to previous menu
                    return;
                }
                case 0 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call input options
            input = inputStaff();
        } while (input >= 0 && input <= 4);
    }

    public int inputManager() {
        System.out.println("SELECT AN OPTION");
        System.out.println("1. View bank info");
        System.out.println("2. Customer management"); // all customers at this role
        System.out.println("3. Staff management");
        System.out.println("4. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 4);
    }

    public void callManagerMenu(String username) {
        int input = inputManager();
        do {
            switch (input) {
                case 1 -> {
                    // View bank info: No of customers/loans/deposits/accounts; BS, PL
                    break;
                }
                case 2 -> {
                    // View customer info - all customers
                    // Approve credit rating update from staff
                    break;
                }
                case 3 -> {
                    // Staff management
                    // view staff info & performance, edit staff's rank, edit salary & bonus system
                    // approve staff's user register request
                    break;
                }
                case 4 -> {
                    // Return to previous menu
                    return;
                }
                case 0 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call input options
            input = inputManager();
        } while (input >= 0 && input <= 4);
    }

    public void exitProgram() {
        System.out.println("Exiting. Goodbye.");
        System.exit(0);
    }
}
