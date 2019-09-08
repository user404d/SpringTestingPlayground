package third.party;

import java.util.HashMap;
import java.util.Map;

public class LocalDatabase<T> {

    private Map<String, T> data;

    public LocalDatabase() {
        data = new HashMap<>();
    }

    public void put(String key, T value) {
        data.put(key, value);
    }

    public T get(String key) {
        return data.get(key);
    }
}
