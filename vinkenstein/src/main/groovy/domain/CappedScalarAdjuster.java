package domain;

public class CappedScalarAdjuster {
    int price;
    double priceStepRatio;
    boolean isLargerBetter;
    double maxAdjustmentRatio;
    int value1;
    int value2;

    public CappedScalarAdjuster(int value1, int value2, int price, double priceStepRatio, boolean isLargerBetter, double maxAdjustmentRatio) {
        this.price = price;
        this.priceStepRatio = priceStepRatio;
        this.isLargerBetter = isLargerBetter;
        this.maxAdjustmentRatio = maxAdjustmentRatio;
        this.value1 = value1;
        this.value2 = value2;
    }

    public int get() {
        int result = 0;
        result += signMultiplier() *
                        Math.min (
                                price * priceStepRatio * Math.abs(value1 - value2),
                                price * maxAdjustmentRatio
                        );
        return result;
    }

    private int signMultiplier() {
        int result;
        if (isLargerBetter) {
            result = (value1 - value2 < 0) ? 1 : -1;
        } else {
            result = (value1 - value2 < 0) ? -1 : 1;
        }

        return result;
    }
}
