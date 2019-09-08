package domain

class ComparableListing {
    private Listing listing;
    private int similarityScore;

    ComparableListing(Listing listing, int similarityScore) {
        this.listing = listing
        this.similarityScore = similarityScore
    }

    Listing getListing() {
        return listing
    }

    void setListing(Listing listing) {
        this.listing = listing
    }

    int getSimilarityScore() {
        return similarityScore
    }

    void setSimilarityScore(int similarityScore) {
        this.similarityScore = similarityScore
    }
}
