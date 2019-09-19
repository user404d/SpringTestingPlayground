package domain

class Comparison {
    int similarityScore;

    Comparison() {
    }

    Comparison(Listing comparable, int similarityScore) {
        this.similarityScore = similarityScore
        this.comparable = comparable
    }

    boolean getValid() {
        return valid
    }

    void setValid(boolean valid) {
        this.valid = valid
    }
    boolean valid

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
