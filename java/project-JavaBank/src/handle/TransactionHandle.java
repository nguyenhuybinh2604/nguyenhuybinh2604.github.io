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
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Customer customer = (Customer) users.get(username); // always available at this stage
        if (customer.getProducts() != null && customer.getProducts().size() > 0) {
            Map<Integer, Product> productMap = customer.getProducts().stream()
                    .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                    .collect(Collectors.toMap(Product::getProductId, o -> o));
            if (productMap.size()>0) {
                System.out.printf("%-10s%-10s%12s%12s%10s%10s%30s%30s%8s%10s%10s\n", "IDs", "Staff IDs",
                        "Value Date", "Maturity", "Tenor (M)", "Currency", "Balance",
                        "Balance in VND", "IR", "Status", "Type");
                for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
                    System.out.println(entry.getValue().toString());
                }
                System.out.println("Select account Id to view:");
                int accountId = inputControl.getInput(sc, 1, null);
                if (productMap.containsKey(accountId)) {
                    if (customer.getTransactions() != null && customer.getTransactions().size() > 0) {
                        // filter dates
                        System.out.println("From date (dd/MM/yyyy):");
                        LocalDate fromDate = inputControl.getLocalDate(sc);
                        System.out.println("To date (dd/MM/yyyy):");
                        LocalDate toDate = inputControl.getLocalDate(sc);
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
                                DateTimeFormatter fmtDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                System.out.printf("%-10s%-20s%-10s%-12s%-14s%-10s%30s%30s%30s%30s\n", "IDs","Time", "Type",
                                        "Account IDs", "Customer IDs", "Currency", "Credit", "Debit", "Credit in VND", "Debit in VND");

                                for (Transaction transaction : filteredTransactions)
                                    System.out.println(transaction.toString(fmtDateTime));

                            } else System.out.println("No record");
                        } else System.out.println("From date must not excess to date");
                    } else System.out.println("No record");
                } else System.out.println("Wrong account Id");
            } else System.out.println("Customer has no account");
        } else System.out.println("Customer has no product");
    }

    public int getNextId(List<Transaction> transactions) {
        int maxId = 0;
        for (Transaction transaction : transactions) {
                int id = transaction.getTransactionId();
                if (id > maxId) maxId = id;
        }
        return ++maxId;
    }
}
