package handle;

import entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductHandle {
    public void newAccount(Scanner sc, Map<String, Object> users, List<Product> products,
                           List<InterestRate> interestRates, String username) {
        if (users != null && users.size() > 0 && users.containsKey(username)) {

            // show currently supported CCY
            System.out.println("Choose a currency:");
            String currency = sc.nextLine();
            if (findCurrency(interestRates, ProductType.ACCOUNT, currency) != null) {

                // User type check not required since this can only be called by Customer
                Customer customer = (Customer) users.get(username);
                int customerId = customer.getCustomerId();
                LocalDate valueDate = LocalDate.now();

                //get latest interest rate for this type of account
                Double interestRate = getInterestRate(interestRates, ProductType.ACCOUNT, currency, null, null);
                if (interestRate != null) {

                    // null staffId: manager assigns later; null tenor & matDate: for product type ACCOUNT
                    Account account = new Account(customerId, null, valueDate, null, null, currency,
                            0.0, 0.0, interestRate, ProductStatus.ACTIVE);
                    int accountId = getNextId(products, ProductType.ACCOUNT);
                    account.setProductId(accountId);

                    // update in customer's personal product records
                    List<Product> subProducts = customer.getProducts();
                    subProducts.add(account);

                    // update in common records
                    products.add(account);

                    System.out.println("New " + currency + " account created");
                } else System.out.println("Interest rate for the product is not available");
            } else System.out.println("Currency not supported");
        } else System.out.println("Customer not found");
    }

    public void addBalance(Scanner sc, InputControl inputControl, TransactionHandle transactionHandle,
                           BalanceSheet balanceSheet, Map<String, Object> users, List<ExchangeRate> exchangeRates,
                           List<Transaction> transactions, String username) {
        if (users != null && users.size() > 0 && users.containsKey(username)) {
            Customer customer = (Customer) users.get(username);
            if (customer.getProducts() != null && customer.getProducts().size() > 0) {

                // get customer's list of products, type: account
                List<Product> subProducts = customer.getProducts();

                //switch to map
                Map<Integer, Product> productMap = subProducts.stream()
                        .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                        .collect(Collectors.toMap(Product::getProductId, o -> o));
                if (productMap.size() > 0) {
                    System.out.printf("%-10s%-10s%12s%12s%10s%10s%30s%30s%8s%10s%10s\n", "IDs", "Staff IDs",
                            "Value Date", "Maturity", "Tenor (M)", "Currency", "Balance",
                            "Balance in VND", "IR", "Status", "Type");
                    for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
                        System.out.println(entry.getValue().toString());
                    }
                    System.out.println("Select the account Id to add balance:");
                    int accountId = inputControl.getInput(sc, 1, null);
                    if (productMap.containsKey(accountId)) {
                        Account account = (Account) productMap.get(accountId);
                        System.out.println("Enter the added amount:");
                        double addedAmount = inputControl.getInput(sc, 0.0, null);
                        account.setBalance(account.getBalance() + addedAmount);

                        //set converted balance
                        String currency = account.getCurrency();
                        account.setConvertedBalance(account.getBalance()
                                * getExchangeRate(exchangeRates, currency, "VND"));

                        // to transaction log
                        int transactionId = transactionHandle.getNextId(transactions);
                        Transaction transaction = new Transaction(transactionId, LocalDateTime.now(), TransactionType.ADDBALANCE,
                                accountId, customer.getCustomerId(), currency, addedAmount, 0, addedAmount *
                                getExchangeRate(exchangeRates, currency, "VND"), 0);
                        transactions.add(transaction);
                        customer.getTransactions().add(transaction);

                        // change in balance sheet
                        balanceSheet.setCashBalance(balanceSheet.getCashBalance()
                                + (addedAmount * getExchangeRate(exchangeRates, currency, "VND")));
                        balanceSheet.setDepositBalance(balanceSheet.getDepositBalance()
                                + (addedAmount * getExchangeRate(exchangeRates, currency, "VND")));

                        System.out.println("Account balance added");
                    } else System.out.println("Wrong account Id entered. Retry");
                } else System.out.println("You have not opened an account. Return to open account");
            } else System.out.println("No record");
        } else System.out.println("Customer not found");
    }

    public void fundTransfer(Scanner sc, InputControl inputControl, UserHandle userHandle, TransactionHandle transactionHandle, Map<String, Object> users, List<Product> products,
                             List<ExchangeRate> exchangeRates, List<Transaction> transactions, String username) {
        if (users != null && users.size() > 0 && users.containsKey(username)) {
            Customer sender = (Customer) users.get(username);
            // see if customer has any product
            if (sender.getProducts() != null && sender.getProducts().size() > 0) {

                // get all accounts
                Map<Integer, Product> productMap = products.stream()
                        .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                        .collect(Collectors.toMap(Product::getProductId, o -> o));
                if (productMap.size() > 0) {

                    // print all senders' accounts, if any
                    System.out.printf("%-10s%-10s%12s%12s%10s%10s%30s%30s%8s%10s%10s\n", "IDs", "Staff IDs",
                            "Value Date", "Maturity", "Tenor (M)", "Currency", "Balance",
                            "Balance in VND", "IR", "Status", "Type");
                    for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
                        if (entry.getValue().getCustomerId() == sender.getCustomerId())
                            System.out.println(entry.getValue().toString());
                    }
                    System.out.println("Select source account Id:");
                    int sourceAccountId = inputControl.getInput(sc, 1, null);

                    //check if source account available & if it belongs to sender
                    if (productMap.containsKey(sourceAccountId) &&
                            productMap.get(sourceAccountId).getCustomerId() == sender.getCustomerId()) {
                        Account sourceAccount = (Account) productMap.get(sourceAccountId);
                        String sourceCurrency = sourceAccount.getCurrency();

                        // does not print accounts, sender must select right one
                        System.out.println("Select target account Id:");
                        int targetAccountId = inputControl.getInput(sc, 1, null);

                        //check if targetAccount exists, <>sourceAccount & has same currency
                        if (targetAccountId == sourceAccountId)
                            System.out.println("Target account must be different than source account");
                        else if (productMap.containsKey(targetAccountId)
                                && productMap.get(targetAccountId).getCurrency().equals(sourceCurrency)) {
                            Account targetAccount = (Account) productMap.get(targetAccountId);
                            System.out.println("Enter amount to be transferred:");
                            double transferredAmount = inputControl.getInput(sc, 0.0, null);

                            //check if source has enough money
                            if (sourceAccount.getBalance() >= transferredAmount) {
                                sourceAccount.setBalance(sourceAccount.getBalance() - transferredAmount);
                                sourceAccount.setConvertedBalance(sourceAccount.getBalance() * getExchangeRate(exchangeRates, sourceCurrency, "VND"));
                                targetAccount.setBalance(targetAccount.getBalance() + transferredAmount); //same currency
                                targetAccount.setConvertedBalance(targetAccount.getBalance() * getExchangeRate(exchangeRates, sourceCurrency, "VND"));

                                // to transaction log
                                // send transaction
                                int transactionSendId = transactionHandle.getNextId(transactions);
                                Transaction transactionSend = new Transaction(transactionSendId, LocalDateTime.now(), TransactionType.FUNDTRANSFER,
                                        sourceAccountId, sender.getCustomerId(), sourceCurrency, 0, transferredAmount, 0,
                                        transferredAmount * getExchangeRate(exchangeRates, sourceCurrency, "VND"));
                                transactions.add(transactionSend);
                                sender.getTransactions().add(transactionSend);

                                // receive transaction
                                int transactionReceiveId = transactionHandle.getNextId(transactions);
                                int receiverId = targetAccount.getCustomerId();
                                Customer receiver = userHandle.getCustomerUsername(users, receiverId);

                                Transaction transactionReceive = new Transaction(transactionReceiveId, LocalDateTime.now(), TransactionType.FUNDTRANSFER,
                                        targetAccountId, receiverId, sourceCurrency, transferredAmount, 0,
                                        transferredAmount * getExchangeRate(exchangeRates, sourceCurrency, "VND"), 0);

                                transactions.add(transactionReceive);
                                receiver.getTransactions().add(transactionReceive);

                                // print transaction info
                                System.out.printf("%s%s%s%,.2f%s%d%s%d\n", "Transfer success: ", sourceCurrency, " ", transferredAmount, " from Account ",
                                        sourceAccountId, " to Account ", targetAccountId);

                            } else System.out.println("Not enough money in source account");
                        } else
                            System.out.println("Wrong account Id entered or accounts' currencies do not match. Retry");
                    } else System.out.println("Wrong account Id entered. Retry");
                } else System.out.println("No account record found. Return to open account");
            } else System.out.println("No record");
        } else System.out.println("Customer not found");
    }

    public void newSaving(Scanner sc, InputControl inputControl, BalanceSheet balanceSheet, Map<String, Object> users,
                          List<Product> products, List<ExchangeRate> exchangeRates, List<InterestRate> interestRates,
                          String username) {
        if (users != null && users.size() > 0 && users.containsKey(username)) {
            System.out.println("Choose a currency:");
            String currency = sc.nextLine();
            if (findCurrency(interestRates, ProductType.SAVING, currency) != null) {

                // User type check not required since this can only be called by Customer
                Customer customer = (Customer) users.get(username);
                int customerId = customer.getCustomerId();
                LocalDate valueDate = LocalDate.now();
                if (displayTenors(interestRates, ProductType.SAVING, currency) != null) {
                    System.out.println("Select tenor of your saving (in months):");
                    displayTenors(interestRates, ProductType.SAVING, currency);
                    int tenor = inputControl.getInput(sc, 1, null);
                    if (findTenor(interestRates, ProductType.SAVING, currency, tenor) != 0) {

                        // auto calculate matdate basing on tenor
                        LocalDate maturityDate = valueDate.plusMonths(tenor);
                        System.out.println("Enter saving balance:");
                        double balance = inputControl.getInput(sc, 0.0, null);
                        double convertedBalance = getExchangeRate(exchangeRates, currency, "VND") * balance;
                        Double interestRate = getInterestRate(interestRates, ProductType.SAVING, currency, tenor, null);
                        if (interestRate != null) {

                            // null staffId: manager assigns later;
                            Saving saving = new Saving(customerId, null, valueDate, maturityDate, tenor, currency, balance,
                                    convertedBalance, interestRate, ProductStatus.ACTIVE);
                            int savingId = getNextId(products, ProductType.SAVING);
                            saving.setProductId(savingId);

                            // update in customer's product records
                            List<Product> subProducts = customer.getProducts();
                            subProducts.add(saving);

                            // also add in common records
                            products.add(saving);

                            // change in balance sheet
                            balanceSheet.setCashBalance(balanceSheet.getCashBalance()
                                    + saving.getConvertedBalance());
                            balanceSheet.setDepositBalance(balanceSheet.getDepositBalance()
                                    + saving.getConvertedBalance());

                            System.out.println("New " + currency + " saving created");
                        } else System.out.println("Interest rate for the product is not available");
                    } else System.out.println("Chosen tenor is not available");
                } else System.out.println("Tenor for the product is not available");
            } else System.out.println("Currency not supported");
        } else System.out.println("Customer not found");
    }

    public void newLoan(Scanner sc, InputControl inputControl, Map<String, Object> users, List<Product> products,
                        List<InterestRate> interestRates, String username) {
        if (users != null && users.size() > 0 && users.containsKey(username)) {

            //check credit rating
            // User type check not required since this can only be called by Customer
            Customer customer = (Customer) users.get(username);
            if (customer.getCreditRating() != CreditRating.NA) {
                System.out.println("Choose a currency:");
                String currency = sc.nextLine();
                if (findCurrency(interestRates, ProductType.LOAN, currency) != null) {
                    int customerId = customer.getCustomerId();
                    if (displayTenors(interestRates, ProductType.LOAN, currency) != null) {
                        System.out.println("Select tenor of your loan (in months):");
                        System.out.println(displayTenors(interestRates, ProductType.LOAN, currency));
                        int tenor = inputControl.getInput(sc, 1, null);
                        if (findTenor(interestRates, ProductType.LOAN, currency, tenor) != 0) {
                            System.out.println("Enter borrowing amount:");
                            double balance = inputControl.getInput(sc, 0.0, null); // converted Balance calculate on loan approval
                            String creditRatingStr = customer.getCreditRating().toString();
                            Double interestRate = getInterestRate(interestRates, ProductType.LOAN, currency, tenor, creditRatingStr);
                            if (interestRate != null) {

                                // null staffId: manager assigns later; LOCKED status & null valueDate & matDate -> need approval before granting loans
                                Loan loan = new Loan(customerId, null, null, null, tenor, currency, balance,
                                        0, interestRate, ProductStatus.LOCKED);
                                int loanId = getNextId(products, ProductType.LOAN);
                                loan.setProductId(loanId);

                                // update in customer's product records
                                List<Product> subProducts = customer.getProducts();
                                subProducts.add(loan);

                                // also add in common records
                                products.add(loan);

                                System.out.println("New " + currency + " loan submitted for approval");
                            } else
                                System.out.println("Interest rate for the product is not available");
                        } else System.out.println("Chosen tenor is not available");
                    } else System.out.println("Tenor for the product is not available");
                } else System.out.println("Currency not supported");
            } else System.out.println("Credit rating not set");
        } else System.out.println("Customer not found");
    }

    // within a customer
    public void foreignExchange(Scanner sc, InputControl inputControl, TransactionHandle transactionHandle, Map<String, Object> users, List<Product> products,
                                List<ExchangeRate> exchangeRates, List<Transaction> transactions, String username) {
        if (users != null && users.size() > 0 && users.containsKey(username)) {
            Customer customer = (Customer) users.get(username);
            if (customer.getProducts() != null && customer.getProducts().size() > 0) {

                // get customer's list of products, type: account
                List<Product> subProducts = customer.getProducts();

                //switch to map
                Map<Integer, Product> productMap = subProducts.stream()
                        .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                        .collect(Collectors.toMap(Product::getProductId, o -> o));
                if (productMap.size() > 0) {
                    System.out.println("Enter the currency you want to buy:");
                    String toCurrency = sc.nextLine();

                    //check if toCurr accounts available, if not tell customer to open account
                    if (findAccountsByCurrency(users, toCurrency, username)) {
                        System.out.println("Enter the currency you want to sell:");
                        String fromCurrency = sc.nextLine();

                        //check if fromCurr accounts available, if not tell customer to open account
                        if (findAccountsByCurrency(users, fromCurrency, username)) {
                            double exchangeRate = getExchangeRate(exchangeRates, fromCurrency, toCurrency);
                            if (exchangeRate != 0) {

                                //display selling accounts
                                displayAccounts(users, fromCurrency, username);
                                System.out.println("Select the account Id to retrieve currency:");
                                int fromAccountId = inputControl.getInput(sc, 1, null);

                                //check if entered accountId exists & match with displayed
                                if (productMap.containsKey(fromAccountId) && productMap.get(fromAccountId).getCurrency().equals(fromCurrency)) {

                                    //possible error => no because it stop if 1st condition fails
                                    Account fromAccount = (Account) productMap.get(fromAccountId);
                                    System.out.println("Enter exchanged amount, <= " + fromAccount.getBalance() + " " + fromCurrency + ":");
                                    double exchangedAmount = inputControl.getInput(sc, 0.0, null);

                                    // check if fromAcc has enough money
                                    if (fromAccount.getBalance() >= exchangedAmount) {

                                        // display buying accounts
                                        displayAccounts(users, toCurrency, username);
                                        System.out.println("Select the account Id to receive currency:");
                                        int toAccountId = inputControl.getInput(sc, 1, null);

                                        //check if entered accountId exists & match with displayed
                                        if (productMap.containsKey(toAccountId) && productMap.get(toAccountId).getCurrency().equals(toCurrency)) {
                                            Account toAccount = (Account) productMap.get(toAccountId);
                                            fromAccount.setBalance(fromAccount.getBalance() - exchangedAmount);
                                            fromAccount.setConvertedBalance(fromAccount.getBalance() * getExchangeRate(exchangeRates, fromCurrency, "VND"));

                                            //convert exchanged amt to toCurrency units
                                            toAccount.setBalance(toAccount.getBalance() + exchangedAmount * exchangeRate);
                                            toAccount.setConvertedBalance(toAccount.getBalance() * getExchangeRate(exchangeRates, toCurrency, "VND"));

                                            // to transaction log
                                            // send transaction
                                            int transactionSellId = transactionHandle.getNextId(transactions);
                                            Transaction transactionSell = new Transaction(transactionSellId, LocalDateTime.now(), TransactionType.FOREIGNEXCHANGE,
                                                    fromAccountId, customer.getCustomerId(), fromCurrency, 0, exchangedAmount, 0,
                                                    exchangedAmount * getExchangeRate(exchangeRates, fromCurrency, "VND"));
                                            transactions.add(transactionSell);
                                            customer.getTransactions().add(transactionSell);

                                            // receive transaction
                                            int transactionBuyId = transactionHandle.getNextId(transactions);

                                            Transaction transactionBuy = new Transaction(transactionBuyId, LocalDateTime.now(), TransactionType.FOREIGNEXCHANGE,
                                                    toAccountId, customer.getCustomerId(), toCurrency, exchangedAmount * exchangeRate, 0,
                                                    exchangedAmount * exchangeRate * getExchangeRate(exchangeRates, toCurrency, "VND"), 0);
                                            transactions.add(transactionBuy);
                                            customer.getTransactions().add(transactionBuy);

                                            // print transaction info
                                            System.out.printf("%s%s%s%,.2f%s%s%s%,.2f\n", "Exchange success: ", fromCurrency, " ", exchangedAmount, " for ",
                                                    toCurrency, " ", exchangedAmount * exchangeRate);
                                        } else System.out.println("Wrong account Id entered. Retry");
                                    } else System.out.println("Not enough money in source account");
                                } else System.out.println("Wrong account Id entered. Retry");
                            } else
                                System.out.println("Exchange from " + fromCurrency + " to " + toCurrency + " not available");
                        } else System.out.println(fromCurrency + " account not available. Return to open account");
                    } else System.out.println(toCurrency + " currency not available. Return to open account");
                } else System.out.println("Customer has no account. Return to open account");
            } else System.out.println("No record");
        } else System.out.println("Customer not found");
    }

    public void displayAccounts(Map<String, Object> users, String currency, String username) {
        if (users != null && users.size() > 0 && users.containsKey(username)) {
            Customer customer = (Customer) users.get(username);
            if (customer.getProducts() != null && customer.getProducts().size() > 0) {
                List<Product> filteredProducts;
                if (currency != null)
                    filteredProducts = customer.getProducts().stream()
                            .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                            .filter(o -> o.getCurrency().equals(currency))
                            .collect(Collectors.toList());

                    // if null currency is passed -> println all accounts
                else filteredProducts = customer.getProducts().stream()
                        .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                        .collect(Collectors.toList());
                System.out.printf("%-10s%-10s%12s%12s%10s%10s%30s%30s%8s%10s%10s\n", "IDs", "Staff IDs",
                        "Value Date", "Maturity", "Tenor (M)", "Currency", "Balance",
                        "Balance in VND", "IR", "Status", "Type");
                for (Product product : filteredProducts) System.out.println(product.toString());
            } else System.out.println("No record");
        } else System.out.println("No record");
    }

    // in customers product list
    private boolean findAccountsByCurrency(Map<String, Object> users, String currency, String username) {
        if (users != null && users.size() > 0) {
            if (users.containsKey(username)) {
                Customer customer = (Customer) users.get(username);
                if (customer.getProducts() != null && customer.getProducts().size() > 0) {
                    List<Product> filteredProducts;
                    filteredProducts = customer.getProducts().stream()
                            .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                            .filter(o -> o.getCurrency().equals(currency))
                            .collect(Collectors.toList());
                    return filteredProducts.size() > 0;
                } else return false;
            } else return false;
        } else return false;
    }

    // find currency in list of rate curves (int rate table)
    private String findCurrency(List<InterestRate> interestRates, ProductType productType, String currency) {
        if (interestRates != null && interestRates.size() > 0) {

            // get latest effectDate
            LocalDate latestEffectDate = interestRates.stream()
                    .filter(p -> p.getEffectDate().isBefore(LocalDate.now().plusDays(1))) // latest day before today
                    .map(InterestRate::getEffectDate)
                    .max(LocalDate::compareTo)
                    .orElse(null);

            // when exchange rate table happens to be null -> return null
            List<InterestRate> filteredInterestRates = interestRates.stream()
                    .filter(o -> o.getEffectDate().isEqual(latestEffectDate))
                    .filter(o -> o.getProductType() == productType)
                    .filter(o -> o.getCurrency().equals(currency))
                    .collect(Collectors.toList());
            for (InterestRate interestRate : filteredInterestRates) {
                if (interestRate.getCurrency().equals(currency))
                    return currency;
            }
        }
        return null;
    }

    // find tenor in list of products rate (int rate table)
    // tenor can't be null -> either loans or savings
    private int findTenor(List<InterestRate> interestRates, ProductType productType, String currency, int tenor) {
        if (interestRates != null && interestRates.size() > 0) {

            // get latest effectDate
            LocalDate latestEffectDate = interestRates.stream()
                    .filter(p -> p.getEffectDate().isBefore(LocalDate.now().plusDays(1))) // latest day before today
                    .map(InterestRate::getEffectDate)
                    .max(LocalDate::compareTo)
                    .orElse(null);

            //case when exchange rate table happens to be null -> return 0
            List<InterestRate> filteredInterestRates = interestRates.stream()
                    .filter(o -> o.getEffectDate().isEqual(latestEffectDate))
                    .filter(o -> o.getProductType() == productType)
                    .filter(o -> o.getCurrency().equals(currency))
                    .collect(Collectors.toList());
            for (InterestRate interestRate : filteredInterestRates) {
                if (interestRate.getTenor() == tenor)
                    return tenor; // return result and stop for loop
            }
        }
        return 0;
    }

    // find latest interest rate by product type, currency, tenor and credit rating
    public Double getInterestRate(List<InterestRate> interestRates, ProductType productType, String currency,
                                  Integer tenor, String creditRatingStr) {
        if (interestRates != null && interestRates.size() > 0) {

            // get latest effectDate
            LocalDate latestEffectDate = interestRates.stream()
                    .filter(p -> p.getEffectDate().isBefore(LocalDate.now().plusDays(1))) // latest day before today
                    .map(InterestRate::getEffectDate)
                    .max(LocalDate::compareTo) // get the maximum value
                    .orElse(null); // handle empty list

            List<InterestRate> filteredInterestRates = new ArrayList<>();
            switch (productType) {
                case ACCOUNT -> //avoid credit rating str && tenor which are null
                        filteredInterestRates = interestRates.stream()
                                .filter(o -> o.getEffectDate().isEqual(latestEffectDate))
                                .filter(o -> o.getProductType().equals(productType))
                                .filter(o -> o.getCurrency().equals(currency))
                                .collect(Collectors.toList());
                case LOAN -> // Interest rate can only be defined with a determined credit rating
                        filteredInterestRates = interestRates.stream()
                                .filter(o -> o.getEffectDate().isEqual(latestEffectDate))
                                .filter(o -> o.getProductType().equals(productType))
                                .filter(o -> o.getCurrency().equals(currency))
                                .filter(o -> o.getTenor().equals(tenor))
                                .filter(o -> o.getCreditRatingStr().equals(creditRatingStr))
                                .collect(Collectors.toList());
                case SAVING -> //avoid credit rating str which is null
                        filteredInterestRates = interestRates.stream()
                                .filter(o -> o.getEffectDate().isEqual(latestEffectDate))
                                .filter(o -> o.getProductType().equals(productType))
                                .filter(o -> o.getCurrency().equals(currency))
                                .filter(o -> o.getTenor().equals(tenor))
                                .collect(Collectors.toList());
            }
            // Iterate through the filtered list
            for (InterestRate interestRate : filteredInterestRates)
                // return and end loop after 1st match, if list is empty -> return null
                return interestRate.getInterestRate();
        }
        return null;
    }

    // find currency in list of currencies (int rate table)
    public Double getExchangeRate(List<ExchangeRate> exchangeRates, String fromCurrency, String toCurrency) {
        if (exchangeRates != null && exchangeRates.size() > 0) {

            // get latest effectDate
            LocalDate latestEffectDate = exchangeRates.stream()
                    .filter(p -> p.getEffectDate().isBefore(LocalDate.now().plusDays(1))) // latest day before today
                    .map(ExchangeRate::getEffectDate)
                    .max(LocalDate::compareTo) // get the maximum value
                    .orElse(null); // handle empty list

            //case when exchange rate table happens to be null -> return 0
            List<ExchangeRate> filteredExchangeRates = exchangeRates.stream()
                    .filter(o -> o.getEffectDate().isEqual(latestEffectDate))

                    // if only 1 target Currency below filter is not required
                    .filter(o -> o.getToCurrency().equals("VND"))
                    .collect(Collectors.toList());
            double fromRate = 0;
            double toRate = 0;
            boolean foundFromRate = false;
            boolean foundToRate = false;

            // loop once to find 2 values
            for (ExchangeRate exchangeRate : filteredExchangeRates) {
                if (exchangeRate.getFromCurrency().equals(fromCurrency)) {
                    fromRate = exchangeRate.getExchangeRate();
                    foundFromRate = true;
                }
                if (exchangeRate.getFromCurrency().equals(toCurrency)) {
                    toRate = exchangeRate.getExchangeRate();
                    foundToRate = true;
                }
                if (foundFromRate && foundToRate) break;
            }
            if (foundFromRate && foundToRate) {
                try {
                    return fromRate / toRate;
                } catch (ArithmeticException e) {
                    return null;
                }
            } else return null;
        } else return null;
    }


    public int getNextId(List<Product> products, ProductType productType) {
        int maxId = 0;
        for (Product product : products) {
            if (product.getProductType() == productType) {
                int id = product.getProductId();
                if (id > maxId) maxId = id;
            }
        }
        return ++maxId;
    }

    //used after currency for the product is found
    private String displayTenors(List<InterestRate> interestRates, ProductType productType, String currency) {
        if (interestRates != null && interestRates.size() > 0) {
            if (productType == ProductType.ACCOUNT)
                return "Current account product have no tenor";
            else {

                // filter the list to get selected product type
                List<InterestRate> filteredInterestRates = interestRates.stream()
                        .filter(o -> o.getProductType() == productType)
                        .filter(o -> o.getCurrency().equals(currency))
                        .collect(Collectors.toList());

                // get distinct tenors
                List<Integer> distinctTenor = filteredInterestRates.stream()
                        .map(InterestRate::getTenor)
                        .distinct()
                        .collect(Collectors.toList());

                // print the distinct tenors
                if (distinctTenor.size() > 0)
                    return distinctTenor.toString();
                else return null;
            }
        } else return "Interest rate data not available";
    }

    // for certain effect date
    public String displayCurrencies(List<InterestRate> interestRates, ProductType productType, LocalDate effectDate) {
        if (interestRates != null && interestRates.size() > 0) {

            // filter the list to get selected product type
            List<InterestRate> filteredInterestRates = interestRates.stream()
                    .filter(o -> o.getProductType() == productType)
                    .filter(o -> o.getEffectDate().equals(effectDate))
                    .collect(Collectors.toList());

            // get distinct currencies
            List<String> distinctCurrencies = filteredInterestRates.stream()
                    .map(InterestRate::getCurrency)
                    .distinct()
                    .collect(Collectors.toList());

            // print the distinct tenors
            if (distinctCurrencies.size() > 0)
                return distinctCurrencies.toString();
            else return null;

        } else return "Interest rate data not available";
    }

}
