package handle;

import entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SummaryHandle {

    // pivot group list of products by customer - only customers with at least 1 product
    public Map<Integer, Summary> byCustomer(CustomerHandle customerHandle, Map<String, Object> users, List<Product> filteredProducts) {
        Map<Integer, Summary> result = new HashMap<>();
        for (Product product : filteredProducts) {
            int key = product.getCustomerId();
            Summary summary = result.getOrDefault(key, new Summary());
            if (product.getProductType() == ProductType.LOAN && product.getProductStatus() == ProductStatus.LOCKED)
                summary.addToCount(1, 1);// 2 - no of locked loans
            else if (product.getProductType() == ProductType.LOAN) {
                summary.addToCount(0, 1);// 1 - no of loans
                summary.addToSum(0, product.getConvertedBalance());// 1 - loan balance
                summary.addToSum(1, product.getConvertedBalance() * product.getInterestRate()); // 2 - avg rate x balance
            } else if (product.getProductType() == ProductType.SAVING) {
                summary.addToCount(2, 1);// 3 - no of savings
                summary.addToSum(2, product.getConvertedBalance());// 3 - deposit balance
                summary.addToSum(3, product.getConvertedBalance() * product.getInterestRate()); // 4 - avg rate x balance
            } else if (product.getProductType() == ProductType.ACCOUNT) {
                summary.addToCount(3, 1);// 4 - no of accounts
                summary.addToSum(2, product.getConvertedBalance());// 3 - deposit balance
                summary.addToSum(3, product.getConvertedBalance() * product.getInterestRate()); // 4 - avg rate x balance
            }

            // add last 7d transactions customerSummary.addToCount(4,x);
            String customerUsername = customerHandle.findCustomer(users, key);
            int transactionCount = customerHandle.noOfTransaction(users, customerUsername, 7);
            summary.addToCount(4, transactionCount);

            // update to summary
            result.put(key, summary);
        }
        return result;
    }

    // pivot group list of products by staff, including staff with no product
    public Map<Integer, Summary> byStaff(Map<String, Object> users, List<Product> filteredProducts) {
        Map<Integer, Summary> result = new HashMap<>();

        if (users != null && users.size() > 0) {
            for (Object user : users.values()) {
                // only get active staff (staffs whose registration pending approval are not included)
                if (user.getClass().getSimpleName().equals("Staff")
                        && ((Staff) user).getUserStatus() == UserStatus.ACTIVE) {
                    int key = ((Staff) user).getStaffId();

                    Summary summary = result.getOrDefault(key, new Summary());

                    // get related to staff products, if any
                    List<Product> filteredStaffProducts = filteredProducts.stream()
                            .filter(o -> o.getStaffId() == key)
                            .collect(Collectors.toList());

                    // loop through all staffs, including those with empty products
                    for (Product product : filteredStaffProducts) {
                        if (product.getProductType() == ProductType.LOAN && product.getProductStatus() == ProductStatus.LOCKED)
                            summary.addToCount(1, 1);// 2 - no of locked loans
                        else if (product.getProductType() == ProductType.LOAN) {
                            summary.addToCount(0, 1);// 1 - no of loans
                            summary.addToSum(0, product.getConvertedBalance());// 1 - loan balance
                            summary.addToSum(1, product.getConvertedBalance() * product.getInterestRate()); // 2 - avg rate x balance
                        } else if (product.getProductType() == ProductType.SAVING) {
                            summary.addToCount(2, 1);// 3 - no of savings
                            summary.addToSum(2, product.getConvertedBalance());// 3 - deposit balance
                            summary.addToSum(3, product.getConvertedBalance() * product.getInterestRate()); // 4 - avg rate x balance
                        } else if (product.getProductType() == ProductType.ACCOUNT) {
                            summary.addToCount(3, 1);// 4 - no of accounts
                            summary.addToSum(2, product.getConvertedBalance());// 3 - deposit balance
                            summary.addToSum(3, product.getConvertedBalance() * product.getInterestRate()); // 4 - avg rate x balance
                        }

                        // update to summary
                        result.put(key, summary);
                    }
                }
            }
        }
        return result;
    }

    // display each line (customer) of result map
    public void displayDetailCustomer(CustomerHandle customerHandle, Map<String, Object> users,
                                      Map<Integer, Summary> result) {
        // for total line
        int totalLoanCount = 0;
        int totalSavingCount = 0;
        int totalAccountCount = 0;
        int totalLockedLoanCount = 0;
        int totalTransactionCount = 0;
        double totalLoanBalance = 0;
        double totalDepositBalance = 0;
        double totalLoanBalanceByRate = 0;
        double totalDepositBalanceByRate = 0;

        // Print result - header
        System.out.printf("%-10s%-25s%-7s%-15s%30s%8s%8s%9s%30s%8s%15s\n", "IDs", "Name", "Rating",
                "Loans (locked)", "Balance in VND", "Avg IR", "Savings", "Accounts", "Balance in VND", "Avg IR", "Transactions");
        for (Map.Entry<Integer, Summary> entry : result.entrySet()) {
            Integer customerId = entry.getKey();
            Summary summary = entry.getValue();
            String username = customerHandle.findCustomer(users, customerId);
            String customerName = ((Person) users.get(username)).getName();
            CreditRating creditRating = ((Customer) users.get(username)).getCreditRating();
            String creditRatingStr = creditRating.toString();
            double avgRateLoans = summary.getSum(0) == 0 ? 0 : summary.getSum(1) * 100 / summary.getSum(0);
            double avgRateDeposits = summary.getSum(2) == 0 ? 0 : summary.getSum(3) * 100 / summary.getSum(2);
            totalLoanCount += summary.getCount(0);
            totalLockedLoanCount += summary.getCount(1);
            totalSavingCount += summary.getCount(2);
            totalAccountCount += summary.getCount(3);
            totalTransactionCount += summary.getCount(4);
            totalLoanBalance += summary.getSum(0);
            totalLoanBalanceByRate += summary.getSum(1);
            totalDepositBalance += summary.getSum(2);
            totalDepositBalanceByRate += summary.getSum(3);

            // print each customer info
            System.out.printf("%-10d%-25s%-7s%,5d %,8d %,30.2f%,7.2f%%%,8d%,9d%,30.2f%,7.2f%%%,15d\n", customerId,
                    customerName, creditRatingStr, summary.getCount(0), summary.getCount(1),
                    summary.getSum(0), avgRateLoans, summary.getCount(2),
                    summary.getCount(3), summary.getSum(2), avgRateDeposits, summary.getCount(4));
        }
        double totalAvgRateLoans = totalLoanBalance == 0 ? 0 : totalLoanBalanceByRate * 100 / totalLoanBalance;
        double totalAvgRateDeposits = totalDepositBalance == 0 ? 0 : totalDepositBalanceByRate * 100 / totalDepositBalance;
        // print total line
        System.out.printf("%-10s%-25s%-7s%,5d %,8d %,30.2f%,7.2f%%%,8d%,9d%,30.2f%,7.2f%%%,15d\n", "", "Total",
                "", totalLoanCount, totalLockedLoanCount,
                totalLoanBalance, totalAvgRateLoans, totalSavingCount,
                totalAccountCount, totalDepositBalance, totalAvgRateDeposits, totalTransactionCount);
    }

    // display each line (staf) of result map
    public void displayDetailStaff(StaffHandle staffHandle, Map<String, Object> users,
                                   Map<Integer, Summary> result) {
        // for total line
        int totalLoanCount = 0;
        int totalSavingCount = 0;
        int totalAccountCount = 0;
        int totalLockedLoanCount = 0;
        double totalLoanBalance = 0;
        double totalDepositBalance = 0;
        double totalLoanBalanceByRate = 0;
        double totalDepositBalanceByRate = 0;

        // Print result - header
        System.out.printf("%-10s%-32s%-15s%30s%8s%8s%9s%30s%8s\n", "IDs", "Name",
                "Loans (locked)", "Balance in VND", "Avg IR", "Savings", "Accounts", "Balance in VND", "Avg IR");
        for (Map.Entry<Integer, Summary> entry : result.entrySet()) {
            Integer staffId = entry.getKey();
            Summary summary = entry.getValue();
            String username = staffHandle.findStaff(users, staffId);
            String staffName = ((Person) users.get(username)).getName();
            double avgRateLoans = summary.getSum(0) == 0 ? 0 : summary.getSum(1) * 100 / summary.getSum(0);
            double avgRateDeposits = summary.getSum(2) == 0 ? 0 : summary.getSum(3) * 100 / summary.getSum(2);
            totalLoanCount += summary.getCount(0);
            totalLockedLoanCount += summary.getCount(1);
            totalSavingCount += summary.getCount(2);
            totalAccountCount += summary.getCount(3);
            totalLoanBalance += summary.getSum(0);
            totalLoanBalanceByRate += summary.getSum(1);
            totalDepositBalance += summary.getSum(2);
            totalDepositBalanceByRate += summary.getSum(3);

            // print each staff info
            System.out.printf("%-10d%-32s%,5d %,8d %,30.2f%,7.2f%%%,8d%,9d%,30.2f%,7.2f%%\n", staffId,
                    staffName, summary.getCount(0), summary.getCount(1),
                    summary.getSum(0), avgRateLoans, summary.getCount(2),
                    summary.getCount(3), summary.getSum(2), avgRateDeposits);
        }
        double totalAvgRateLoans = totalLoanBalance == 0 ? 0 : totalLoanBalanceByRate * 100 / totalLoanBalance;
        double totalAvgRateDeposits = totalDepositBalance == 0 ? 0 : totalDepositBalanceByRate * 100 / totalDepositBalance;

        // print total line
        System.out.printf("%-10s%-32s%,5d %,8d %,30.2f%,7.2f%%%,8d%,9d%,30.2f%,7.2f%%\n", "", "Total",
                totalLoanCount, totalLockedLoanCount,
                totalLoanBalance, totalAvgRateLoans, totalSavingCount,
                totalAccountCount, totalDepositBalance, totalAvgRateDeposits);
    }

    //only calculate total of map's array columns
    public Summary getTotal(Map<Integer, Summary> result) {
        Summary total = new Summary();
        for (Map.Entry<Integer, Summary> entry : result.entrySet()) {
            Summary summary = entry.getValue();
            total.addToCount(0, summary.getCount(0));
            total.addToCount(1, summary.getCount(1));
            total.addToCount(2, summary.getCount(2));
            total.addToCount(3, summary.getCount(3));
            //count of last 7d transactions here
            total.addToCount(4, summary.getCount(4));
            total.addToSum(0, summary.getSum(0));
            total.addToSum(1, summary.getSum(1));
            total.addToSum(2, summary.getSum(2));
            total.addToSum(3, summary.getSum(3));
        }
        return total;
    }

    // display only summary record
    public void displaySummary(Summary total) {
        System.out.printf("%-50s%,33d\n", "Number of loans:", total.getCount(0));
        System.out.printf("%-50s%,33d\n", "Number of locked loans:", total.getCount(1));
        System.out.printf("%-50s%,33d\n", "Number of savings:", total.getCount(2));
        System.out.printf("%-50s%,33d\n", "Number of accounts:", total.getCount(3));
        System.out.printf("%-50s%,33d\n", "Number of transactions in last 7d:", total.getCount(4));
        System.out.printf("%-50s%3s%,30.2f\n", "Total outstanding loan balance:", "VND", total.getSum(0));
        System.out.printf("%-50s%,33.2f%%\n", "Average interest rate on loans:",
                (total.getSum(0) == 0) ? 0 : (total.getSum(1) * 100 / total.getSum(0)));
        System.out.printf("%-50s%3s%,30.2f\n", "Total outstanding deposit balance:", "VND", total.getSum(2));
        System.out.printf("%-50s%,33.2f%%\n", "Average interest rate on deposits:",
                (total.getSum(2) == 0) ? 0 : (total.getSum(3) * 100 / total.getSum(2)));

    }
}
