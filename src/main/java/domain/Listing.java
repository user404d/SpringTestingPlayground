package domain;

public class Listing {
    private String vin;
    private String make;
    private String model;
    private String year;
    private int numberOfLikes;

    public int getPrice() {
        return price;
    }

    private int price;

    public int getNumberOfAccidents() {
        return numberOfAccidents;
    }

    private int numberOfAccidents;

    public Listing(String vin, String make, String model, String year, int numberOfLikes, int numberOfOwners, int price, int numberOfAccidents) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.numberOfLikes = numberOfLikes;
        this.numberOfOwners = numberOfOwners;
        this.price = price;
        this.numberOfAccidents = numberOfAccidents;
    }

    public int getNumberOfOwners() {
        return numberOfOwners;
    }

    private int numberOfOwners;

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }
}
