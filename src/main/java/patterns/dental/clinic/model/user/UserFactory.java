package patterns.dental.clinic.model.user;

import lombok.Getter;
import patterns.dental.clinic.MyList;

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

    public User createPatient(String firstName, String lastName, String loginPass,String dateOfBirth) {
        return new Patient(firstName, lastName, loginPass, dateOfBirth);
    }

    public Dentist createDentist(long dentistId, String firstName, String lastName, String loginPass,String dateOfBirth, String specialty,
                                    MyList<String> allowedOperations) {
        if (specialty.equalsIgnoreCase("apprentice")) {
            return createApprenticeDentist(dentistId, firstName, lastName, loginPass, dateOfBirth, specialty, allowedOperations);
        } else {
            List<String> operations = new MyList<>();
            operations.add("All");
            return createRegularDentist(dentistId, firstName, lastName, loginPass, dateOfBirth, specialty, allowedOperations);
        }
    }

    private Dentist createRegularDentist(long dentistId, String firstName, String lastName, String LoginPass,String dateOfBirth,
                                                String specialty, MyList<String> allowedOperations) {
        return new RegularDentist(firstName, lastName, dentistId, LoginPass, dateOfBirth, allowedOperations, specialty);
    }

    private Dentist createApprenticeDentist(long dentistId, String firstName, String lastName, String LoginPass,String dateOfBirth,
                                                String specialty, MyList<String> allowedOperations) {
        return new ApprenticeDentist(firstName, lastName, dentistId, LoginPass, dateOfBirth, allowedOperations, specialty);
    }
}
