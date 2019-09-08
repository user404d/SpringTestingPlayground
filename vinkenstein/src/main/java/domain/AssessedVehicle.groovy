package domain

class AssessedVehicle {
    protected String vin;
    protected String make;
    protected String model;
    protected int year;
    protected int numberOfAccidents;
    protected int numberOfOwners;
    AssessedVehicle() {
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
