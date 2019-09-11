package vinkenstein;

public class ThirdPartyClient {
    public String get() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "the real thing";
    };
}
