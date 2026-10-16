
public class Condo extends RealEstateProperty {

    private int floorNumber;

    public Condo(String address, double price, double size, int floorNumber) {
        super(address, price, size);
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public String getPropertyInfo() {
        return super.getPropertyInfo()
                + ", Type: Condo"
                + ", Floor: " + floorNumber;
    }
}