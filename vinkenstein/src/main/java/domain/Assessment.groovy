package domain

class Assessment {

    List<ComparableListing> getComparables() {
        return comparables
    }
    public List<ComparableListing> comparables = new ArrayList<>()

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
