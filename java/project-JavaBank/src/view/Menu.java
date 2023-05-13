package view;

import java.util.*;

import entity.*;
import handle.*;
import io.DataIO;

public class Menu {

    Scanner sc = new Scanner(System.in);
    InputControl inputControl = new InputControl();
    DataIO dataIO = new DataIO();

    SummaryHandle summaryHandle = new SummaryHandle();
    UserHandle userHandle = new UserHandle();
    CustomerHandle customerHandle = new CustomerHandle();
    TransactionHandle transactionHandle = new TransactionHandle();
    StaffHandle staffHandle = new StaffHandle();
    ManagerHandle managerHandle = new ManagerHandle();
    ProductHandle productHandle = new ProductHandle();

    BalanceSheet balanceSheet;
    Map<String, Object> users = new HashMap<>();
    List<Product> products = new ArrayList<>();
    List<InterestRate> interestRates = new ArrayList<>();
    List<ExchangeRate> exchangeRates = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();

    String loginStatus;
    String activeUsername;

    public int inputMain() {
        System.out.println("\nWELCOME TO JAVABANK");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.println("3. Manager");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 3);
    }

    public void callMainMenu() {

        // initial data import
        dataIO.readExcel(inputControl, users, products, interestRates, exchangeRates, transactions);

        // initialize bs
        balanceSheet = new BalanceSheet(products);

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
            System.out.println("\nMANAGER MENU. SELECT AN OPTION");
            System.out.println("1. Login");
            System.out.println("2. Return");
            System.out.println("0. Exit");
            maxOption = 2;
        } else if (userRole == UserRole.STAFF) {
            System.out.println("\nSTAFF MENU. SELECT AN OPTION");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Return");
            System.out.println("0. Exit");
            maxOption = 3;
        } else {
            System.out.println("\nCUSTOMER MENU. SELECT AN OPTION");
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
        System.out.println("\nLOG IN FAILED. SELECT AN OPTION");
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
        System.out.println("\nWELCOME " + username + ". SELECT AN OPTION");
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

    public int inputViewCustomerInfo() {
        System.out.println("\nSELECT AN OPTION");
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
        System.out.println("\nSELECT AN OPTION");
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
                                customerHandle.overview(customerHandle, summaryHandle, users, username);
                            }
                            case 2 -> {
                                customerHandle.viewProducts(users, ProductType.LOAN, username);
                            }
                            case 3 -> {
                                customerHandle.viewProducts(users, ProductType.SAVING, username);
                            }
                            case 4 -> {
                                customerHandle.viewProducts(users, ProductType.ACCOUNT, username);
                            }
                            case 5 -> {
                                transactionHandle.viewHistory(sc, inputControl, users, username);
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
                    productHandle.addBalance(sc, inputControl, transactionHandle, users, exchangeRates, transactions, username);
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
                    productHandle.foreignExchange(sc, inputControl, transactionHandle, users, products, exchangeRates, transactions, username);
                }
                case 7 -> {
                    productHandle.fundTransfer(sc, inputControl, userHandle, transactionHandle, users, products, exchangeRates, transactions, username);
                }
                case 8 -> {
                    // Edit personal info
                    int inputEditInfo = inputEditCustomerInfo();
                    do {
                        switch (inputEditInfo) {
                            case 8:
                                // do nothing - meet loop exit condition
                            case 0:
                                exitProgram();
                            default:
                                customerHandle.editInfo(sc, inputControl, users, inputEditInfo, username);
                        }
                        inputEditInfo = inputEditCustomerInfo();
                    } while (inputEditInfo >= 0 && inputEditInfo <= 7);
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

    public int inputStaff(String username) {
        System.out.println("\nWELCOME " + username + ". SELECT AN OPTION");
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
                    staffHandle.overview(customerHandle, summaryHandle, users, products, username);
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
                    staffHandle.updateRating(sc, inputControl, customerHandle, users, username);
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
        System.out.println("\nWELCOME " + username + ". SELECT AN OPTION");
        System.out.println("1. View bank info");
        System.out.println("2. Customer management");
        System.out.println("3. Staff management");
        // register, set salaries, bonuses, approve customer rating change requests ...
        // assign staff to products with missing staffId
        System.out.println("4. Exchange rates");
        System.out.println("5. Interest rates");
        System.out.println("6. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 6);
    }

    public int inputViewBankInfo() {
        System.out.println("\nELECT AN OPTION");
        System.out.println("1. Bank overview");
        System.out.println("2. Customer info");
        System.out.println("3. Staff info");
        System.out.println("4. Balance sheet");
        System.out.println("5. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 5);
    }

    public int inputStaffManagement() {
        System.out.println("\nSELECT AN OPTION");
        System.out.println("1. Approve staff register");
        System.out.println("2. Assign staff");
        System.out.println("3. Adjust salary");
        System.out.println("4. Calculate bonus");
        System.out.println("5. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 5);
    }

    public int inputXRManagement() {
        System.out.println("\nSELECT AN OPTION");
        System.out.println("1. View exchange rates");
        System.out.println("2. Update exchange rates");
        System.out.println("3. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 3);
    }

    public int inputIRManagement() {
        System.out.println("\nSELECT AN OPTION");
        System.out.println("1. View interest rates");
        System.out.println("2. Update interest rates");
        System.out.println("3. Return");
        System.out.println("0. Exit");

        return inputControl.getInput(sc, 0, 3);
    }

    public void callManagerMenu(String username) {
        int input = inputManager(username);
        do {
            switch (input) {
                case 1 -> {
                    // View customer info
                    int inputViewInfo = inputViewBankInfo();
                    do {
                        switch (inputViewInfo) {
                            case 1 -> {
                                managerHandle.overview(customerHandle, summaryHandle, users, products);
                            }
                            case 2 -> {
                                managerHandle.viewListOfCustomers(inputControl, summaryHandle, customerHandle, users, products);
                            }
                            case 3 -> {
                                managerHandle.viewListOfStaffs(summaryHandle, staffHandle, users, products);
                            }
                            case 4 -> {
                                System.out.println(balanceSheet.toString());
                            }
                            case 5 -> {
                                //do nothing, meets the loop exit condition
                            }
                            case 0 -> exitProgram();
                        }
                        inputViewInfo = inputViewBankInfo();
                    } while (inputViewInfo >= 0 && inputViewInfo <= 4);
                }
                case 2 -> {
                    // Approve credit rating update from staff
                }
                case 3 -> {
                    int inputStaffManagement = inputStaffManagement();
                    do {
                        switch (inputStaffManagement) {
                            case 1 -> {
                                managerHandle.approveStaffRegister(sc, inputControl, users);
                            }
                            case 2 -> {
                                managerHandle.assignStaffs(sc, inputControl, summaryHandle, staffHandle, users, products);
                            }
                            case 3 -> {
                                managerHandle.editSalary(sc, inputControl, summaryHandle, staffHandle, users, products);
                            }
                            case 4 -> {
                                managerHandle.viewBonus(sc, inputControl, summaryHandle, staffHandle, users, products);
                            }
                            case 5 -> {
                                //do nothing, meets the loop exit condition
                            }
                            case 0 -> exitProgram();
                        }
                        inputStaffManagement = inputStaffManagement();
                    } while (inputStaffManagement >= 0 && inputStaffManagement <= 4);
                }
                case 4 -> {
                    int inputXR = inputXRManagement();
                    do {
                        switch (inputXR) {
                            case 1 -> {
                                managerHandle.viewExchangeRate(sc, inputControl, exchangeRates);
                            }
                            case 2 -> {
                                managerHandle.updateExchangeRate(sc, inputControl, exchangeRates);
                            }
                            case 3 -> {
                                //do nothing, meets the loop exit condition
                            }
                            case 0 -> exitProgram();
                        }
                        inputXR = inputXRManagement();
                    } while (inputXR >= 0 && inputXR <= 2);
                }
                case 5 -> {
                    int inputIR = inputIRManagement();
                    do {
                        switch (inputIR) {
                            case 1 -> {
                                managerHandle.viewInterestRate(sc, inputControl, interestRates);
                            }
                            case 2 -> {
                                managerHandle.updateInterestRate(sc, inputControl, productHandle, interestRates);
                            }
                            case 3 -> {
                                //do nothing, meets the loop exit condition
                            }
                            case 0 -> exitProgram();
                        }
                        inputIR = inputIRManagement();
                    } while (inputIR >= 0 && inputIR <= 2);
                }
                case 6 -> {
                    // Return to previous menu
                }
                case 0 ->
                        // Exit
                        exitProgram();
            }
            // Call input options
            input = inputManager(username);
        } while (input >= 0 && input <= 5);
    }

    public void exitProgram() {

        // call data export to excel --> can redesign to run after each completed transaction
        dataIO.writeExcel(inputControl, users, products, interestRates, exchangeRates, transactions);

        System.out.println("Exiting. Goodbye.");
        System.exit(0);
    }
}
