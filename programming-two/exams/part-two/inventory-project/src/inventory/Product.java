package inventory;

public class Product {
    public String id;
    public String name;
    public Double price;
    public Integer quantity;
    public Integer discount;

    public Product(String id, String name, Double price, Integer quantity, Integer discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    // toString
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price +
                ", quantity=" + quantity + ", discount=" + discount + "]";
    }
}
