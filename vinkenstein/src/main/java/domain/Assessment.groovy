package domain

class Assessment {

    List<Comparison> getComparables() {
        return comparables
    }
    public List<Comparison> comparables = new ArrayList<>()

    AssessedVehicle getAssessedVehicle() {
        return assessedVehicle
    }

    void setAssessedVehicle(AssessedVehicle assessedVehicle) {
        this.assessedVehicle = assessedVehicle
    }
    private AssessedVehicle assessedVehicle;

    int getSuggestedPrice() {
        return suggestedPrice
    }

    void setSuggestedPrice(int suggestedPrice) {
        this.suggestedPrice = suggestedPrice
    }
    private int suggestedPrice;

}
