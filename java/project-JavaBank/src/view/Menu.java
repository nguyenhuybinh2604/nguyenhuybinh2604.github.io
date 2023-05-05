package view;

import java.util.*;

import entity.*;
import handle.*;
import io.InputData;

public class Menu {
    Scanner sc = new Scanner(System.in);
    InputControl inputControl = new InputControl();
    SummaryHandle summaryHandle = new SummaryHandle();
    UserHandle userHandle = new UserHandle();
    CustomerHandle customerHandle = new CustomerHandle();
    ProductHandle productHandle = new ProductHandle();
    StaffHandle staffHandle = new StaffHandle();
    Map<String, Object> users = new HashMap<>();
    List<Product> products = new ArrayList<>();
    List<InterestRate> interestRates = new ArrayList<>();
    List<ExchangeRate> exchangeRates = new ArrayList<>();
    List<Request> requests = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();
    InputData inputData = new InputData();
    String loginStatus;
    String activeUsername;

    public int inputMain() {
        System.out.println("WELCOME TO JAVABANK");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.println("3. Manager");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 3);
    }

    public void callMainMenu() {
        // initial data import
        inputData.readExcel(inputControl, users, products, interestRates, exchangeRates, requests, transactions);
        int input = inputMain();
        do {
            switch (input) {
                case 1 -> {
                    callStartMenu(UserRole.CUSTOMER);
                }
                case 2 -> {
                    callStartMenu(UserRole.STAFF);
                }
                case 3 -> {
                    callStartMenu(UserRole.MANAGER);
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
            System.out.println("MANAGER MENU. SELECT AN OPTION");
            System.out.println("1. Login");
            System.out.println("2. Return");
            System.out.println("0. Exit");
            maxOption = 2;
        } else if (userRole == UserRole.STAFF) {
            System.out.println("STAFF MENU. SELECT AN OPTION");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Return");
            System.out.println("0. Exit");
            maxOption = 3;
        } else {
            System.out.println("CUSTOMER MENU. SELECT AN OPTION");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Return");
            System.out.println("0. Exit");
            maxOption = 3;
        }
        return inputControl.getInput(sc, 0, maxOption);
    }

    public void callStartMenu(UserRole userRole) {
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
                        && !loginStatus.contains("loginFailureWrongUserRole")
                        && !loginStatus.contains("loginFailureUserLocked"));
            } else if (input == 2 && userRole != UserRole.MANAGER)
                userHandle.register(sc, inputControl, users, userRole);
            else if (input == 2 && userRole == UserRole.MANAGER) // leave uncut to show logic
                return;
            else if (input == 3 && userRole != UserRole.MANAGER)
                return;
            else if (input == 3 && userRole == UserRole.MANAGER) {// leave uncut to show logic
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
                case 0 ->
                        // Exit
                        exitProgram();
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
            case CUSTOMER -> {
                callCustomerMenu(username);
            }
            case STAFF -> {
                callStaffMenu(username);
            }
            case MANAGER -> {
                callManagerMenu(username);
            }
        }
    }

    public int inputCustomer(String username) {
        System.out.println("WELCOME " + username + ". SELECT AN OPTION");
        System.out.println("1. View info");
        System.out.println("2. Add balance");
        System.out.println("3. Open account");
        System.out.println("4. Apply for loans");
        System.out.println("5. Open savings");
        System.out.println("6. Foreign exchange");
        System.out.println("7. Fund transfer");
        System.out.println("8. Edit personal info");
        System.out.println("9. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 9);
    }

    public void callCustomerMenu(String username) {
        int input = inputCustomer(username);
        do {
            switch (input) {
                case 1 -> {
                    // View customer info
                    int inputViewInfo = inputViewCustomerInfo();
                    do {
                        switch (inputViewInfo) {
                            case 1 -> {
                                customerHandle.overview(summaryHandle, users, username);
                            }
                            case 2 -> {
                                //2. List of loans
                                customerHandle.viewProducts(inputControl, users, ProductType.LOAN, username);
                            }
                            case 3 -> {
                                // 3. List of savings
                                customerHandle.viewProducts(inputControl, users, ProductType.SAVING, username);
                            }
                            case 4 -> {
                                // 4. List of accounts
                                customerHandle.viewProducts(inputControl, users, ProductType.ACCOUNT, username);
                            }
                            case 5 -> {
                                //transaction history
                            }
                            case 6 -> {
                                //do nothing, meets the loop exit condition
                            }
                            case 0 -> exitProgram();
                        }
                        inputViewInfo = inputViewCustomerInfo();
                    } while (inputViewInfo >= 0 && inputViewInfo <= 5);
                }
                case 2 -> {
                    productHandle.addBalance(sc, inputControl, users, exchangeRates, username);
                }
                case 3 -> {
                    productHandle.newAccount(sc, users, products, interestRates, username);
                }
                case 4 -> {
                    productHandle.newLoan(sc, inputControl, users, products, interestRates, username);
                }
                case 5 -> {
                    productHandle.newSaving(sc, inputControl, users, products, exchangeRates, interestRates, username);
                }
                case 6 -> {
                    productHandle.foreignExchange(sc, inputControl, users, products, exchangeRates, username);
                }
                case 7 -> {
                    productHandle.fundTransfer(sc, inputControl, users, products, exchangeRates, username);
                }
                case 8 -> {
                    // Edit personal info
                    int inputEditInfo = inputEditCustomerInfo();
                    do {
                        switch (inputEditInfo) {
                            case 8:
                                return;
                            case 0:
                                exitProgram();
                            default:
                                customerHandle.editInfo(sc, inputControl, users, inputEditInfo, username);
                        }
                        inputEditInfo = inputEditCustomerInfo();
                    } while (inputEditInfo >= 0 && inputEditInfo <= 8);
                }
                case 9 -> {
                    // Return to previous menu
                }
                case 0 ->
                        // Exit
                        exitProgram();
            }
            // Call input options
            input = inputCustomer(username);
        } while (input >= 0 && input <= 8);
    }

    public int inputViewCustomerInfo() {
        System.out.println("SELECT AN OPTION");
        System.out.println("1. Overview");
        System.out.println("2. List of loans");
        System.out.println("3. List of savings");
        System.out.println("4. List of accounts");
        System.out.println("5. Transaction history");
        System.out.println("6. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 6);
    }

    public int inputEditCustomerInfo() {
        System.out.println("SELECT AN OPTION");
        System.out.println("1. Personal Id");
        System.out.println("2. Email address");
        System.out.println("3. Password");
        System.out.println("4. Name");
        System.out.println("5. Gender");
        System.out.println("6. Age");
        System.out.println("7. Address");
        System.out.println("8. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 8);
    }

    public int inputStaff(String username) {
        System.out.println("WELCOME " + username + ". SELECT AN OPTION");
        System.out.println("1. Staff result overview");
        System.out.println("2. View customer info");
        System.out.println("3. Approve loan request(s)");
        System.out.println("4. Update credit rating");
        System.out.println("5. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 5);
    }

    public void callStaffMenu(String username) {
        int input = inputStaff(username);
        do {
            switch (input) {
                case 1 -> {
                    staffHandle.overview(summaryHandle, users, products, username);
                }
                case 2 -> {
                    staffHandle.viewListOfCustomers(inputControl, summaryHandle, customerHandle, users, products,
                            username);
                }
                case 3 -> {
                    staffHandle.approveLoans(sc, inputControl, customerHandle, productHandle, users, products,
                            interestRates, exchangeRates, username);
                }
                case 4 -> {
                    staffHandle.updateRating(sc, inputControl, customerHandle, users);
                }
                case 5 -> {
                    //do nothing, meets the loop exit condition
                }
                case 0 ->
                        // Exit
                        exitProgram();
            }
            // Call input options
            input = inputStaff(username);
        } while (input >= 0 && input <= 4);
    }

    public int inputManager(String username) {
        System.out.println("WELCOME " + username + ". SELECT AN OPTION");
        System.out.println("1. View bank info");
        System.out.println("2. Customer management"); // all customers at this role, view customer requests
        System.out.println("3. Staff management"); // register, set salaries, bonuses, approve customer rating change requests ...
//        , assign staff to products with missing staffId
        System.out.println("4. System setup"); // exchange rates, currencies, interest rates structure
        System.out.println("5. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 5);
    }

    public void callManagerMenu(String username) {
        int input = inputManager(username);
        do {
            switch (input) {
                case 1 -> {
                    // View bank info: No of customers/loans/deposits/accounts; BS, PL
                }
                case 2 -> {
                    // View customer info - all customers
                    // Approve credit rating update from staff
                }
                case 3 -> {
                    // Staff management
                    // view staff info & performance, edit staff's rank, edit salary & bonus system
                    // approve staff's user register request
                }
                case 4 -> {
                    // Return to previous menu
                    return;
                }
                case 5 -> {
                    // Return to previous menu
                }
                case 0 ->
                        // Exit
                        exitProgram();
            }
            // Call input options
            input = inputManager(username);
        } while (input >= 0 && input <= 4);
    }

    public void exitProgram() {
        // call data export to excel --> can redesign to run after each completed transaction
        System.out.println("Exiting. Goodbye.");
        System.exit(0);
    }
}
