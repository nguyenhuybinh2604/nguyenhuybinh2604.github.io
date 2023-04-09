package entity;

public class Product {
    public static int autoId;
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String unit;

    public Product() {
        this.id = ++autoId;
    };

    public Product(String name, String description, int quantity, double price, String unit) {
        this.id = ++autoId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.unit = unit;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                ", quantity='" + getQuantity() + "'" +
                ", price='" + getPrice() + "'" +
                ", unit='" + getUnit() + "'" +
                "}";
    }
}
