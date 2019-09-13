package domain

class VehicleComparator {
    Comparison compare(AssessedVehicle assessed, Listing comparable) {
        Comparison result = new Comparison();
        result.setComparable(comparable);
        if (assessed.getMake().equalsIgnoreCase(comparable.getMake()) && assessed.getModel().equalsIgnoreCase(comparable.getModel())) {
            int similarityScore = 100;
            similarityScore -= Math.abs(comparable.getNumberOfAccidents() - assessed.getNumberOfAccidents())
            result.setSimilarityScore(similarityScore);

            int priceDifferenceFromAssessed = 0;
            priceDifferenceFromAssessed +=
                    (assessed.getNumberOfAccidents() - comparable.getNumberOfAccidents() < 0 ? -1 : 1) *
                    Math.min (
                    comparable.getPrice() * 0.1 * Math.abs(assessed.getNumberOfAccidents() - comparable.getNumberOfAccidents()),
            comparable.getPrice() * 0.4
                    );
            result.setPriceDifferenceFromAssessed(priceDifferenceFromAssessed);
        }
        return result
    }
}
