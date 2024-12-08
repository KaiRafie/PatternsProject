package patterns.dental.clinic.model.user;

import lombok.*;
import patterns.dental.clinic.MyList;

import java.util.Date;

@Getter
@Setter

public class RegularDentist extends Dentist {

    public RegularDentist() {
    }


    public RegularDentist(String firstName, String lastName, long userID, String loginPass, String dateOfBirth,
                          MyList<String> allowedOperations, String specialty) {
        super(firstName, lastName, userID, loginPass, dateOfBirth, allowedOperations, specialty);
    }

    public RegularDentist(String firstName, String lastName, String loginPass, String dateOfBirth) {
        super(firstName, lastName, loginPass, dateOfBirth);
    }
}
