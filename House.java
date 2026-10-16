public class House extends RealEstateProperty {

    private int numFloors;

    public House(String address, double price, double size, int numFloors) {
        super(address, price, size);
        this.numFloors = numFloors;
    }

    public int getNumFloors() {
        return numFloors;
    }

    @Override
    public String getPropertyInfo() {
        return super.getPropertyInfo()
                + ", Type: House"
                + ", Floors: " + numFloors;
    }
}