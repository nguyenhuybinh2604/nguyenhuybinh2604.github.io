public class Laptop {
    private double price;
    private String model;

    public Laptop() {
    };

    public Laptop(String model, double price) {
        this.model = model;
        this.price = price;
    };

    public double getPrice() {
        return price;
    }

    public String getModel(){
        return model;
    }

    public void setPrice(double price) {
        this.price = price;
        // this -> link den class -> lay price cua class
    }

    public void setModel(String model) {
        this.model = model;
        // this -> link den class -> lay price cua class
    }
}
