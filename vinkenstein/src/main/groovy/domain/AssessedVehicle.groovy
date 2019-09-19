package domain

class AssessedVehicle {
    protected String vin;
    protected String make;

    void setMake(String make) {
        this.make = make
    }

    void setModel(String model) {
        this.model = model
    }

    void setYear(int year) {
        this.year = year
    }
    protected String model;
    protected int year;

    void setNumberOfAccidents(int numberOfAccidents) {
        this.numberOfAccidents = numberOfAccidents
    }

    void setNumberOfOwners(int numberOfOwners) {
        this.numberOfOwners = numberOfOwners
    }
    protected int numberOfAccidents;
    protected int numberOfOwners;
    AssessedVehicle() {
    }

    AssessedVehicle(String vin, String make, String model, int year, int numberOfAccidents, int numberOfOwners) {
        this.vin = vin
        this.make = make
        this.model = model
        this.year = year
        this.numberOfAccidents = numberOfAccidents
        this.numberOfOwners = numberOfOwners
    }

    public int getNumberOfAccidents() {
        return numberOfAccidents;
    }

    public int getNumberOfOwners() {
        return numberOfOwners;
    }

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
}
