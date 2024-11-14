package patterns.dental.clinic.model.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter

public class RegularDentist extends Dentist {
    private String specialty;

    public RegularDentist() {
    }

    public RegularDentist(String firstName, String lastName, String loginID, String loginPass, Date dateOfBirth,
                          long dentistId, String specialty) {
        super(firstName, lastName, loginID, loginPass, dateOfBirth, dentistId);
        this.specialty = specialty;
    }
}
