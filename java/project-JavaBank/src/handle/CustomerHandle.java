package handle;

import entity.*;
import service.IUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerHandle {
    public void overview(CustomerHandle customerHandle, SummaryHandle summaryHandle, Map<String, Object> users, String username) {
        Customer customer = (Customer) users.get(username);
        int customerId = customer.getCustomerId();
        if (customer.getProducts() != null && customer.getProducts().size() > 0) {

            // get only products belong to the customer
            List<Product> filteredProducts = customer.getProducts().stream()
                    .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE) //chi lay active & locked
                    .collect(Collectors.toList());

            // Pivoted Sum
            Map<Integer, Summary> result = summaryHandle.byCustomer(customerHandle, users, filteredProducts);

            // calculate total array
            Summary total = summaryHandle.getTotal(result);
            System.out.println("Summary for customer No." + customerId + " - " + customer.getName() + ":");

            // display total array data
            summaryHandle.displaySummary(total);
        } else System.out.println("No record");
    }

    public void viewProducts(Map<String, Object> users, ProductType productType, String username) {
        Customer customer = (Customer) users.get(username);
        if (customer.getProducts() != null && customer.getProducts().size() > 0) {
            List<Product> filteredProducts = customer.getProducts().stream()
                    .filter(o -> o.getProductType() == productType)
                    .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE)
                    .collect(Collectors.toList());
            if (filteredProducts.size() > 0) {
                System.out.printf("%-10s%-10s%12s%12s%10s%10s%30s%30s%8s%10s%10s\n", "IDs", "Staff IDs",
                        "Value Date", "Maturity", "Tenor (M)", "Currency", "Balance",
                        "Balance in VND", "IR", "Status", "Type");
                for (Product product : filteredProducts)
                    System.out.println(product.toString());
            } else System.out.println("No record");
        } else System.out.println("No record");
    }

    public void editInfo(Scanner sc, InputControl inputControl, Map<String, Object> users, int input, String username) {
        switch (input) {
            case 1 -> {
                System.out.println("Enter new personal Id:");
                String newPersonId = inputControl.getNonEmptyString(sc);
                if (findPersonId(users, newPersonId) == null || !((Person) users.get(username)).getPersonId().equals(newPersonId)) {
                    ((Person) users.get(username)).setPersonId(newPersonId);
                    System.out.println("New personal Id has been set");
                } else System.out.println("Id is used. Try another Id");
            }
            case 2 -> {
                System.out.println("Enter new email:");
                String newEmail = inputControl.getEmail(sc);
                if (findEmail(users, newEmail) == null || !((IUser) users.get(username)).getEmail().equals(newEmail)) {
                    ((IUser) users.get(username)).setEmail(newEmail);
                    System.out.println("New email has been set");
                } else System.out.println("Email is used. Try another email");
            }
            case 3 -> {
                System.out.println("Enter new password (7-15 characters, at least 01 capitalized and 01 special characters):");
                String newPassword = inputControl.getPassword(sc);
                ((IUser) users.get(username)).setPassword(newPassword);
                System.out.println("New password has been set");
            }
            case 4 -> {
                System.out.println("Enter new name:");
                String newName = inputControl.getNonEmptyString(sc);
                ((Person) users.get(username)).setName(newName);
                System.out.println("New name has been set");
            }
            case 5 -> {
                System.out.println("Enter new gender:");
                String newGender = inputControl.getNonEmptyString(sc);
                ((Person) users.get(username)).setGender(newGender);
                System.out.println("New gender has been set");
            }
            case 6 -> {
                System.out.println("Enter new age (>=15):");
                int newAge = inputControl.getInput(sc, 15, null);
                ((Person) users.get(username)).setAge(newAge);
                System.out.println("New age has been set");
            }
            case 7 -> {
                System.out.println("Enter new address:");
                String newAddress = sc.nextLine(); //nullable, without specific control
                ((Person) users.get(username)).setAddress(newAddress);
                System.out.println("New address has been set");
            }
        }
    }

    public int noOfTransaction(Map<String, Object> users, String username, int dayRange) {
        int returnValue = 0;
        Customer customer = (Customer) users.get(username);
        if (customer.getTransactions() != null && customer.getTransactions().size() > 0) {
            for (Transaction transaction : customer.getTransactions()) {
                if (transaction.getTransactionTime().isBefore(LocalDateTime.now()) &&
                        transaction.getTransactionTime().isAfter((LocalDate.now()).minusDays(dayRange).atStartOfDay()))
                    returnValue++;
            }
        }
        return returnValue;
    }

    //
    private String findPersonId(Map<String, Object> users, String personId) {
        if (users != null && users.size() > 0) {
            for (Object user : users.values()) {
                if (((Person) user).getPersonId().equals(personId)) {
                    return personId;
                }
            }
        }
        return null;
    }

    private String findEmail(Map<String, Object> users, String email) {
        if (users != null && users.size() > 0) {
            for (Object user : users.values()) {
                if (((IUser) user).getEmail().equals(email)) {
                    return email;
                }
            }
        }
        return null;
    }

    public String findCustomer(Map<String, Object> users, int customerId) {
        if (users != null && users.size() > 0) {
            for (Object user : users.values()) {
                if (user.getClass().getSimpleName().equals("Customer")
                        && ((Customer) user).getCustomerId() == customerId) {
                    return ((Customer) user).getUsername();
                }
            }
        }
        return null;
    }
}
