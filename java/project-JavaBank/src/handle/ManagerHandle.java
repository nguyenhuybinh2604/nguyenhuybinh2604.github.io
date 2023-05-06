package handle;

import entity.Product;
import entity.ProductStatus;
import entity.Staff;
import entity.Summary;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManagerHandle {
    public void overview(SummaryHandle summaryHandle, List<Product> products) {
            if (products.size() > 0) {
                // get all active & locked products
                List<Product> filteredProducts = products.stream()
                        .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE)
                        .collect(Collectors.toList());

                if (filteredProducts.size() > 0) {

                    // Pivoted Sum
                    Map<Integer, Summary> result = summaryHandle.byCustomer(filteredProducts);

                    // calculate total array
                    Summary total = summaryHandle.getTotal(result);
                    System.out.println("Summary for JavaBank:");
                    System.out.printf("%-50s%,33d\n", "Number of customers:", result.size());

                    // display total array data
                    summaryHandle.displaySummary(total);
                } else System.out.println("No record");
            } else System.out.println("No record");
    }

    public void viewListOfCustomers(InputControl inputControl, SummaryHandle summaryHandle, CustomerHandle customerHandle,
                                    Map<String, Object> users, List<Product> products) {
            if (products.size() > 0) {

                // get all active & locked products
                List<Product> filteredProducts = products.stream()
                        .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE)
                        .collect(Collectors.toList());

                if (filteredProducts.size() > 0) {

                    // Pivoted Sum
                    Map<Integer, Summary> result = summaryHandle.byCustomer(filteredProducts);
                    summaryHandle.displayDetail(inputControl, customerHandle, users, result);
                } else System.out.println("No record");
            } else System.out.println("No record");
    }
}
