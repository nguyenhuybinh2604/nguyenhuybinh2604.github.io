package handle;

import java.util.Map;
import java.util.Scanner;

import entity.*;
import service.IUser;

public class UserHandle {
    public String login(Scanner sc, InputControl inputControl, Map<String, Object> users, UserRole userRole) {
        System.out.println("Enter username:");
        String username = inputControl.getNonEmptyString(sc);
        if (users.containsKey(username)) {
            IUser user = (IUser) users.get(username);

            //only continue with ACTIVE users
            if (user.getUserStatus() == UserStatus.ACTIVE) {

                // to ensure the username entered here match the userrole at the menu
                if (user.getUserRole() == userRole) {

                    //eg. customer cant log in using staff username
                    System.out.println("Enter password:");

                    // no need to check password format at login
                    String password = sc.nextLine();

                    if (user.getPassword().equals(password)) {
                        return "loginSuccess_" + username;
                    } else {
                        return "loginFailureWrongPassword_" + username;
                    }
                } else {
                    System.out.println("Wrong username");
                    return "loginFailureWrongUserRole";
                }
            } else {
                System.out.println("User locked");
                return "loginFailureUserLocked";
            }
        } else {
            System.out.println("Wrong username");
            return "loginFailureWrongUsername";
        }
    }

    public String forgotPassword(Scanner sc, InputControl inputControl, Map<String, Object> users, String username) {

        // add code to allow interaction with only ACTIVE users
        System.out.println("Enter recovery email for " + username + ":");
        String email = inputControl.getNonEmptyString(sc);
        if (((IUser) users.get(username)).getEmail().equals(email)) {
            System.out
                    .println(
                            "Enter new password (7-15 characters, at least 01 capitalized and 01 special characters):");
            String newPassword = inputControl.getPassword(sc);

            // update new password
            ((IUser) users.get(username)).setPassword(newPassword);
            System.out.println("Password changed. Logging in");
            return "loginSuccess_" + username;
        } else {
            System.out.println("Wrong email");
            return "loginFailureForgotPasswordWrongEmail";
        }
    }

    public void register(Scanner sc, InputControl inputControl, Map<String, Object> users, UserRole userRole) {
        System.out.println("Register starts");

        // customer -> register freely. autoId = next cusId
        System.out.println("Enter your personal id:");

        // check if id has been used in all records
        String personId = inputControl.getNonEmptyString(sc);
        while (findPersonalId(users, personId) != null) {
            System.out.println("This personal Id is used. Enter another Id:");
            personId = inputControl.getNonEmptyString(sc);
        }
        System.out.println("Enter your name:");
        String name = inputControl.getNonEmptyString(sc);
        System.out.println("Enter your gender:");
        String gender = inputControl.getNonEmptyString(sc);
        System.out.println("Enter your age (>=15):");
        int age = inputControl.getInput(sc, 15, null);
        System.out.println("Enter your address:");
        String address = sc.nextLine();
        System.out.println("Enter your email:");
        String email = inputControl.getEmail(sc);
        while (findEmail(users, email) != null) {
            System.out.println("This email is used. Enter another email:");
            email = inputControl.getEmail(sc);
        }
        System.out.println("Enter your username:");
        String username = inputControl.getNonEmptyString(sc);
        while (users.containsKey(username)) {
            System.out.println("This username is used. Enter another username:");
            username = inputControl.getNonEmptyString(sc);
        }
        System.out.println("Enter your password (7-15 characters, at least 01 capitalized and 01 special characters):");
        String password = inputControl.getPassword(sc);
        int id = getNextId(users, userRole);
        switch (userRole) {
            case CUSTOMER -> {

                // no credit rating yet
                Customer customer = new Customer(id, personId, username, password, email, name, gender,
                        age, address, UserStatus.ACTIVE);
                users.put(username, customer);

                System.out.println("Customer's user has been registered");
            }
            case STAFF -> {
                Staff staff = new Staff(id, personId, username, password, email, name, gender, age, address,
                        UserStatus.LOCKED);
                users.put(username, staff);

                System.out.println("Staff's user has been submitted for register");
            }
        }
    }

    private String findPersonalId(Map<String, Object> users, String personId) {
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            String username = entry.getKey();
            Person user = (Person) users.get(username);
            if (user.getPersonId().equals(personId))
                return username;
        }

        // if a value has been found this row is skipped
        return null;
    }

    private String findEmail(Map<String, Object> users, String email) {
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            String username = entry.getKey();
            IUser user = (IUser) users.get(username);
            if (user.getEmail().equals(email))
                return username;
        }

        // if a value has been found this row is skipped
        return null;
    }

    private int getNextId(Map<String, Object> users, UserRole userRole) {
        int maxCustomerId = 0;
        int maxStaffId = 0;
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            if (entry.getValue().getClass().getSimpleName().equals("Customer") && userRole == UserRole.CUSTOMER) {
                int id = ((Customer) entry.getValue()).getCustomerId();
                if (id > maxCustomerId) maxCustomerId = id;
            } else if (entry.getValue().getClass().getSimpleName().equals("Staff") && userRole == UserRole.STAFF) {
                int id = ((Staff) entry.getValue()).getStaffId();
                if (id > maxStaffId) maxStaffId = id;
            }
        }
        if (userRole == UserRole.CUSTOMER) return ++maxCustomerId;
        else return ++maxStaffId;
    }

    public Customer getCustomerUsername(Map<String, Object> users, int userId) {
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            if (((Customer) entry.getValue()).getCustomerId() == userId) {
                return (Customer) entry.getValue();
            }
        }
        return null;
    }
}
