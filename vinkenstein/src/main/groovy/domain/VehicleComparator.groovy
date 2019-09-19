package domain

class VehicleComparator {
    Comparison compare(AssessedVehicle assessed, Listing comparable) {
        Comparison result = new Comparison();
        result.setComparable(comparable);
        if (assessed.getMake().equalsIgnoreCase(comparable.getMake()) && assessed.getModel().equalsIgnoreCase(comparable.getModel())) {
            result.setValid(true);
            int priceDifferenceFromAssessed = 0;
            priceDifferenceFromAssessed += new CappedScalarAdjuster(assessed.getNumberOfAccidents(), comparable.getNumberOfAccidents(), comparable.getPrice(), 0.1, false, 0.4).get();
            priceDifferenceFromAssessed += new CappedScalarAdjuster(assessed.getYear(), comparable.getYear(), comparable.getPrice(), 0.1, true, 0.4).get();
            priceDifferenceFromAssessed += new CappedScalarAdjuster(assessed.getNumberOfOwners(), comparable.getNumberOfOwners(), comparable.getPrice(), 0.05, false, 0.2).get();
            result.setPriceDifferenceFromAssessed(priceDifferenceFromAssessed);
        } else {
            result.setValid(false);
        }
        return result
    }
}
