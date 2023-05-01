package entity;

public class Saving extends IProduct {
    private String savingId;

    public Saving() {
    }

    @Override
    public String getProductId() {
        return this.savingId;
    };

    @Override
    public void setProductId(String productId) {
        this.savingId = productId;
    };
}
