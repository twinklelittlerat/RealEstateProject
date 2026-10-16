
public abstract class RealEstateProperty implements Property {

    private String address;
    private double price;
    private double size;

    public RealEstateProperty(String address, double price, double size) {
        this.address = address;
        this.price = price;
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public double getSize() {
        return size;
    }

    @Override
    public String getPropertyInfo() {
        return "Address: " + address
                + ", Price: " + price
                + ", Size: " + size;
    }
}