package handle;

import entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductHandle {
    public void newAccount(Scanner sc, Map<String, Object> users, List<Product> products,
                           List<InterestRate> interestRates, String username) {
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
                // update in customer's product records
                List<Product> subProducts = customer.getProducts();
                subProducts.add(account);
                // Code to add to transaction log
                System.out.println("New " + currency + " account created");
            } else System.out.println("Interest rate for the product is not available. Contact manager");
        } else System.out.println("Currency not supported. Contact manager");
    }

    public void addBalance(Scanner sc, InputControl inputControl, Map<String, Object> users,
                           List<ExchangeRate> exchangeRates, String username) {
        Customer customer = (Customer) users.get(username);
        // get customer's list of products, type: account
        List<Product> subProducts = customer.getProducts();
        //switch to map
        Map<Integer, Product> productMap = subProducts.stream()
                .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                .collect(Collectors.toMap(Product::getProductId, o -> o));
        if (productMap.size() > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
                System.out.println(entry.getValue().toString(inputControl, formatter));
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
                System.out.println("Account balance added");
            } else System.out.println("Wrong account Id entered. Retry");
        } else System.out.println("You have not opened an account. Return to open account");
    }

    //only
    public void fundTransfer(Scanner sc, InputControl inputControl, Map<String, Object> users, List<Product> products,
                             List<ExchangeRate> exchangeRates, String username) {
        Customer sender = (Customer) users.get(username);
        // get all accounts
        Map<Integer, Product> productMap = products.stream()
                .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                .collect(Collectors.toMap(Product::getProductId, o -> o));
        if (productMap.size() > 0) {
            // print all senders' accounts, if any
            int i = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
                if (entry.getValue().getCustomerId() == sender.getCustomerId()) {
                    System.out.println(entry.getValue().toString(inputControl, formatter));
                    i++;
                }
            }
            // only start if customer has an account
            if (i > 0) {
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
                    if (targetAccountId != sourceAccountId)
                        System.out.println("Target account must be different than source account");
                    else if (productMap.containsKey(targetAccountId)
                            && productMap.get(targetAccountId).getCurrency().equals(sourceCurrency)) {
                        Account targetAccount = (Account) productMap.get(targetAccountId);
                        System.out.println("Enter amount to be transferred:");
                        double transferredAmount = inputControl.getInput(sc, 0, null);
                        //check if source has enough money
                        if (sourceAccount.getBalance() >= transferredAmount) {
                            sourceAccount.setBalance(sourceAccount.getBalance() - transferredAmount);
                            sourceAccount.setConvertedBalance(sourceAccount.getBalance() * getExchangeRate(exchangeRates, sourceCurrency, "VND"));
                            targetAccount.setBalance(targetAccount.getBalance() + transferredAmount); //same currency
                            targetAccount.setConvertedBalance(targetAccount.getBalance() * getExchangeRate(exchangeRates, sourceCurrency, "VND"));
                            // print transaction info
                            // add to log
                        } else System.out.println("Not enough money in source account");
                    } else System.out.println("Wrong account Id entered or accounts' currencies do not match. Retry");
                } else System.out.println("Wrong account Id entered. Retry");
            } else System.out.println("Customer has no account. Return to open account");
        } else System.out.println("No account record found. Return to open account");
    }

    public void newSaving(Scanner sc, InputControl inputControl, Map<String, Object> users, List<Product> products,
                          List<ExchangeRate> exchangeRates, List<InterestRate> interestRates, String username) {
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
                    double balance = inputControl.getInput(sc, 0, null);
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
                        // Code to add to transaction log
                        System.out.println("New " + currency + " saving created");
                    } else System.out.println("Interest rate for the product is not available. Contact manager");
                } else System.out.println("Chosen tenor is not available. Contact manager");
            } else System.out.println("Tenor for the product is not available. Contact manager");
        } else System.out.println("Currency not supported. Contact manager");
    }

    public void newLoan(Scanner sc, InputControl inputControl, Map<String, Object> users, List<Product> products,
                        List<InterestRate> interestRates, String username) {
        //check credit rating
        // User type check not required since this can only be called by Customer
        Customer customer = (Customer) users.get(username);
        if (customer.getCreditRating() != CreditRating.UNKNOWN) {
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
                        double balance = inputControl.getInput(sc, 0, null); // converted Balance calculate on loan approval
                        String creditRatingStr = inputControl.toCreditRatingStr(customer.getCreditRating());
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
                            // Code to add to transaction log
                            System.out.println("New " + currency + " loan submitted for approval");
                        } else System.out.println("Interest rate for the product is not available. Contact manager");
                    } else System.out.println("Chosen tenor is not available. Contact manager");
                } else System.out.println("Tenor for the product is not available. Contact manager");
            } else System.out.println("Currency not supported. Contact manager");
        } else System.out.println("Credit rating not set. Contact manager");
    }

    public void foreignExchange(Scanner sc, InputControl inputControl, Map<String, Object> users,
                                List<Product> products, List<ExchangeRate> exchangeRates, String username) {
        Customer customer = (Customer) users.get(username);
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
            if (findAccounts(users, toCurrency, username)) {
                System.out.println("Enter the currency you want to sell:");
                String fromCurrency = sc.nextLine();
                //check if fromCurr accounts available, if not tell customer to open account
                if (findAccounts(users, fromCurrency, username)) {
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
                                    //possible error => no because it stop if 1st condition fails
                                    Account toAccount = (Account) productMap.get(toAccountId);
                                    fromAccount.setBalance(fromAccount.getBalance() - exchangedAmount);
                                    fromAccount.setConvertedBalance(fromAccount.getBalance() * getExchangeRate(exchangeRates, fromCurrency, "VND"));
                                    //convert exchanged amt to toCurrency units
                                    toAccount.setBalance(toAccount.getBalance() + exchangedAmount * exchangeRate);
                                    toAccount.setConvertedBalance(toAccount.getBalance() * getExchangeRate(exchangeRates, toCurrency, "VND"));
                                    // add to transaction log
                                } else System.out.println("Wrong account Id entered. Retry");
                            } else System.out.println("Not enough money in source account");
                        } else System.out.println("Wrong account Id entered. Retry");
                    } else
                        System.out.println("Exchange from " + fromCurrency + " to " + toCurrency + " not available. Contact manager");
                } else System.out.println(fromCurrency + " account not available. Return to open account");
            } else System.out.println(toCurrency + " account not available. Return to open account");
        } else System.out.println("Customer has no account. Return to open account");
    }

    public void displayAccounts(Map<String, Object> users, String currency, String username) {
        Customer customer = (Customer) users.get(username);
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
        for (Product product : filteredProducts) System.out.println(product);
    }

    private boolean findAccounts(Map<String, Object> users, String currency, String username) {
        Customer customer = (Customer) users.get(username);
        List<Product> filteredProducts;
        filteredProducts = customer.getProducts().stream()
                .filter(o -> o.getProductType() == ProductType.ACCOUNT)
                .filter(o -> o.getCurrency().equals(currency))
                .collect(Collectors.toList());
        return filteredProducts.size() > 0;
    }

    // find currency in list of rate curves (int rate table)
    private String findCurrency(List<InterestRate> interestRates, ProductType productType, String currency) {
        // get latest effectDate
        LocalDate latestEffectDate = interestRates.stream()
                .map(InterestRate::getEffectDate)
                .max(LocalDate::compareTo)
                .orElse(null);
        if (latestEffectDate != null) { // when exchange rate table happens to be null -> return null
            List<InterestRate> filteredInterestRates = interestRates.stream()
                    .filter(o -> o.getEffectDate().isEqual(latestEffectDate))
                    .filter(o -> o.getProductType() == productType)
                    .filter(o -> o.getCurrency().equals(currency))
                    .collect(Collectors.toList());
            for (InterestRate interestRate : filteredInterestRates) {
                if (interestRate.getCurrency().equals(currency))
                    return currency;
            }
        } else return null;
        return null;
    }

    // find tenor in list of products rate (int rate table)
    // tenor can't be null -> either loans or savings
    private int findTenor(List<InterestRate> interestRates, ProductType productType, String currency, int tenor) {
        // get latest effectDate
        LocalDate latestEffectDate = interestRates.stream()
                .map(InterestRate::getEffectDate)
                .max(LocalDate::compareTo)
                .orElse(null);
        if (latestEffectDate != null) { //case when exchange rate table happens to be null -> return 0
            List<InterestRate> filteredInterestRates = interestRates.stream()
                    .filter(o -> o.getEffectDate().isEqual(latestEffectDate))
                    .filter(o -> o.getProductType() == productType)
                    .filter(o -> o.getCurrency().equals(currency))
                    .collect(Collectors.toList());
            for (InterestRate interestRate : filteredInterestRates) {
                if (interestRate.getTenor() == tenor)
                    return tenor; // return result and stop for loop
            }
        } else return 0;
        return 0;
    }

    // find latest interest rate by product type, currency, tenor and credit rating
    public Double getInterestRate(List<InterestRate> interestRates, ProductType productType, String currency,
                                  Integer tenor, String creditRatingStr) {
        // get latest effectDate
        LocalDate latestEffectDate = interestRates.stream()
                .map(InterestRate::getEffectDate)
                .max(LocalDate::compareTo)
                .orElse(null);
        if (latestEffectDate != null) {
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
        } else return null;
        return null;
    }

    // find currency in list of currencies (int rate table)
    public double getExchangeRate(List<ExchangeRate> exchangeRates, String fromCurrency, String toCurrency) {
        // get latest effectDate
        LocalDate latestEffectDate = exchangeRates.stream()
                .map(ExchangeRate::getEffectDate)
                .max(LocalDate::compareTo)
                .orElse(null);
        if (latestEffectDate != null) { //case when exchange rate table happens to be null -> return 0
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
                    return 0;
                }
            } else return 0;
        } else return 0;
    }

    private int getNextId(List<Product> products, ProductType productType) {
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
    }


}
