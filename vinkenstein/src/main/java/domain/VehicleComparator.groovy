package domain

class VehicleComparator {
    Comparison compare(Listing assessed, Listing comparable) {
        Comparison result = new Comparison();
        result.setComparable(comparable);
        if (assessed.getMake().equalsIgnoreCase(comparable.getMake()) && assessed.getModel().equalsIgnoreCase(comparable.getModel())) {
            result.setSimilarityScore(100);
        }
        return result
    }
}
