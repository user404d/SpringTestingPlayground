package domain;

public class Listing extends AssessedVehicle {

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getPrice() {
        return price;
    }

    private int price;

    public Listing() {

    }

    public Listing(String vin, String make, String model, int year, int numberOfOwners, int price, int numberOfAccidents) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.numberOfOwners = numberOfOwners;
        this.price = price;
        this.numberOfAccidents = numberOfAccidents;
    }

}
