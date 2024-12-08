package patterns.dental.clinic.model.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class Patient extends User {
    private static long lastId = 0;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String loginPass, String dateOfBirth) {
        super(firstName, lastName, loginPass, dateOfBirth);
    }


    // to query patients to the system's list
    public Patient(String firstName, String lastName, long userID, String loginPass, String dateOfBirth) {
        super(firstName, lastName, userID, loginPass, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + getUserID() +
                ", dateOfBirth=" + getDateOfBirth() +
                ", patientFullName=" + getFirstName() + " " + getLastName() +
                ", patientPassword=" + getLoginPass() +
                '}';
    }
}
