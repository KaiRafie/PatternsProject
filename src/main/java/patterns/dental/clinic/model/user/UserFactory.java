package patterns.dental.clinic.model.user;

import lombok.Getter;

import java.util.List;

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

    public User createUser(User user) {
        String userClass = user.getClass().getName().split("\\.")[4].toLowerCase();
        return switch (userClass) {
            case "patient":
                yield createPatient(user);
            case "apprenticedentist":
                yield createRegularDentist(user);
            case "regulardentist":
                yield createApprenticeDentist(user);
            default:
                throw new IllegalStateException("Unexpected value: " + userClass);
        };
    }

    private Patient createPatient(User user) {
        return new Patient(user.getFirstName(), user.getLastName(), user.getLoginPass(), user.getDateOfBirth());
    }

    private Dentist createRegularDentist(User user) {
        return new RegularDentist(user.getFirstName(), user.getLastName(), user.getLoginPass(), user.getDateOfBirth());
    }

    private Dentist createApprenticeDentist(User user) {
        return new ApprenticeDentist(user.getFirstName(), user.getLastName(), user.getLoginPass(), user.getDateOfBirth());
    }
}
