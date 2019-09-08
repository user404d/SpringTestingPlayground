package clients;

import domain.User;
import third.party.LocalDatabase;

public class UserClient extends LocalDatabase<User> {
    public UserClient() {
        super();
        put("John", new User("Doe"));
    }
}
