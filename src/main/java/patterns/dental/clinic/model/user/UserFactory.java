package patterns.dental.clinic.model.user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public static User createPatient(String firstName, String lastName, String loginPass,String dateOfBirth) {
        return new Patient(firstName, lastName, loginPass, dateOfBirth);
    }

    public static Dentist createDentist(long dentistId, String firstName, String lastName, String loginPass,String dateOfBirth, String specialty,
                                    List<String> allowedOperations) {
        if (specialty.toLowerCase().contains("apprentice")) {
            return createApprenticeDentist(dentistId, firstName, lastName, loginPass, dateOfBirth, specialty, allowedOperations);
        } else {
            List<String> operations = new ArrayList<>();
            operations.add("All");
            return createRegularDentist(dentistId, firstName, lastName, loginPass, dateOfBirth, specialty, allowedOperations);
        }
    }

    private static Dentist createRegularDentist(long dentistId, String firstName, String lastName, String LoginPass,String dateOfBirth,
                                                String specialty, List<String> allowedOperations) {
        return new RegularDentist(firstName, lastName, dentistId, LoginPass, dateOfBirth, allowedOperations, specialty);
    }

    private static Dentist createApprenticeDentist(long dentistId, String firstName, String lastName, String LoginPass,String dateOfBirth,
                                                String specialty, List<String> allowedOperations) {
        return new ApprenticeDentist(firstName, lastName, dentistId, LoginPass, dateOfBirth, allowedOperations, specialty);
    }
}
