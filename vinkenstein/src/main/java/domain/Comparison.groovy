package domain

class Comparison {
    int similarityScore;

    int getPriceDifferenceFromAssessed() {
        return priceDifferenceFromAssessed
    }

    void setPriceDifferenceFromAssessed(int priceDifferenceFromAssessed) {
        this.priceDifferenceFromAssessed = priceDifferenceFromAssessed
    }
    int priceDifferenceFromAssessed

    Listing getComparable() {
        return comparable
    }

    void setComparable(Listing comparable) {
        this.comparable = comparable
    }
    Listing comparable

    int getSimilarityScore() {
        return similarityScore
    }

    void setSimilarityScore(int similarityScore) {
        this.similarityScore = similarityScore
    }
}
