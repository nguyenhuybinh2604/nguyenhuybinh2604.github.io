import java.util.List;

public class Customer extends Person{
    private String customerId;
    private CreditRating creditRating;
    private double totalCreditLimit;
    private double usedCreditLimit;
    private double remainingCreditLimit;
    private List<Loan> loans;
    private List<Deposit> deposits;
    private List<Investment> investments;
    private List<Request> requests;
}
