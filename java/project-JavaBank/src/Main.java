import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Account;
import entity.IProduct;
import entity.Loan;
import entity.Request;
import entity.Saving;
import entity.Transaction;
import io.InputData;
import view.Menu;

public class Main {

    public static void main(String[] args) {
        Map<String, Object> users = new HashMap<>();
        List<Loan> loans = new ArrayList<>();
        List<Saving> savings = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<IProduct> products = new ArrayList<>();
        List<Request> requests = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        InputData inputData = new InputData();
        inputData.readExcel(users, loans, savings, accounts, products, requests, transactions);

        // System.out.println(users.size());
        // System.out.println();

        // for (Map.Entry<String, Object> entry : users.entrySet()) {
        //     String key = entry.getKey();
        //     Object value = entry.getValue();
        //     System.out.println(key + ": (user: " + value.toString() + ")");
        // }
        Menu menu = new Menu();
        menu.callMainMenu(users);

    }
}