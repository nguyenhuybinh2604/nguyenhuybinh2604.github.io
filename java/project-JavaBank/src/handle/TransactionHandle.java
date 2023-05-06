package handle;

import entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TransactionHandle {
    public void viewHistory(Scanner sc, InputControl inputControl, Map<String, Object> users, String username) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Customer customer = (Customer) users.get(username); // always available at this stage
        if (customer.getProducts().size() > 0) {
            Map<Integer, Product> productMap = customer.getProducts().stream()
                    .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                    .collect(Collectors.toMap(Product::getProductId, o -> o));
            System.out.println("Select account Id to view:");
            int accountId = inputControl.getInput(sc, 1, null);
            if (productMap.containsKey(accountId)) {
                if (customer.getTransactions().size() > 0) {
                    // filter dates
                    System.out.println("From date (dd/MM/yyyy):");
                    LocalDate fromDate = inputControl.getLocalDate(sc, fmt);
                    System.out.println("To date (dd/MM/yyyy):");
                    LocalDate toDate = inputControl.getLocalDate(sc, fmt);
                    if (fromDate.atStartOfDay().isBefore(toDate.atTime(LocalTime.MAX))) {
                        // filter transaction type
                        System.out.println("SELECT TRANSACTION TYPE");
                        System.out.println("1. Add balance");
                        System.out.println("2. Fund transfer");
                        System.out.println("3. Foreign exchange");
                        System.out.println("4. All");
                        int input = inputControl.getInput(sc, 1, 4);
                        List<Transaction> filteredTransactions = new ArrayList<>();
                        switch (input) {
                            case 1 -> {
                                filteredTransactions = customer.getTransactions().stream()
                                        .filter(o -> o.getAccountId() == accountId)
                                        .filter(o -> o.getTransactionType() != TransactionType.ADDBALANCE)
                                        .filter(o -> o.getTransactionTime().isAfter(fromDate.atStartOfDay()))
                                        .filter(o -> o.getTransactionTime().isBefore(toDate.atTime(LocalTime.MAX)))
                                        .collect(Collectors.toList());
                            }
                            case 2 -> {
                                filteredTransactions = customer.getTransactions().stream()
                                        .filter(o -> o.getAccountId() == accountId)
                                        .filter(o -> o.getTransactionType() != TransactionType.FUNDTRANSFER)
                                        .filter(o -> o.getTransactionTime().isAfter(fromDate.atStartOfDay()))
                                        .filter(o -> o.getTransactionTime().isBefore(toDate.atTime(LocalTime.MAX)))
                                        .collect(Collectors.toList());
                            }
                            case 3 -> {
                                filteredTransactions = customer.getTransactions().stream()
                                        .filter(o -> o.getAccountId() == accountId)
                                        .filter(o -> o.getTransactionType() != TransactionType.FOREIGNEXCHANGE)
                                        .filter(o -> o.getTransactionTime().isAfter(fromDate.atStartOfDay()))
                                        .filter(o -> o.getTransactionTime().isBefore(toDate.atTime(LocalTime.MAX)))
                                        .collect(Collectors.toList());
                            }
                            case 4 -> {
                                filteredTransactions = customer.getTransactions().stream()
                                        .filter(o -> o.getAccountId() == accountId)
                                        .filter(o -> o.getTransactionTime().isAfter(fromDate.atStartOfDay()))
                                        .filter(o -> o.getTransactionTime().isBefore(toDate.atTime(LocalTime.MAX)))
                                        .collect(Collectors.toList());
                            }
                        }
                        if (filteredTransactions.size() > 0) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            System.out.printf("%-20s%-8s%-10s%-10s%-10s%30s%30s%30s%30s\n", "Time", "Type",
                                    "Account IDs", "Customer IDs", "Currency", "Credit", "Debit", "Credit in VND", "Debit in VND");

                            for (Transaction transaction : filteredTransactions)
                                System.out.println(transaction.toString(formatter));

                        } else System.out.println("No record");
                    } else System.out.println("From date must not excess to date");
                } else System.out.println("No record");
            } else System.out.println("Wrong account Id");
        } else System.out.println("Customer has no product");
    }
}
