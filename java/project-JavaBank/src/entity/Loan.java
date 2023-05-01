package entity;

public class Loan extends IProduct {
    private String loanId;

    public Loan() {
    }

    @Override
    public String getProductId() {
        return this.loanId;
    };

    @Override
    public void setProductId(String productId) {
        this.loanId = productId;
    };
}
