package patterns.dental.clinic.model.user;

import lombok.*;
import patterns.dental.clinic.MyList;

@Getter
@Setter
public class ApprenticeDentist extends Dentist {

    public ApprenticeDentist() {
    }

    public ApprenticeDentist(String firstName, String lastName, long userID, String loginPass, String dateOfBirth,
                                MyList<String> allowedOperations, String specialty) {
        super(firstName, lastName, userID, loginPass, dateOfBirth, allowedOperations, specialty);

    }

    public ApprenticeDentist(String firstName, String lastName, String loginPass, String dateOfBirth) {
        super(firstName, lastName, loginPass, dateOfBirth);
    }
}
