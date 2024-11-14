package patterns.dental.clinic.model.user;

import lombok.Getter;

@Getter
public class UserFactory {
    private static UserFactory userFactory;

    private UserFactory() {
    }

    public static UserFactory getInstance() {
        if (userFactory == null) {
            synchronized (UserFactory.class) {
                if (userFactory == null) {
                    userFactory = new UserFactory();
                }
            }
        }
        return userFactory;
    }

    public User createDefaultUser() {
        return null;
    }


}
