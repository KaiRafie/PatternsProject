package patterns.dental.clinic.model.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class Patient extends User {
    private long patientID = 0;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String loginID, String loginPass, Date dateOfBirth, long patientId) {
        super(firstName, lastName, loginID, loginPass, dateOfBirth);
        this.patientID = patientId;
    }


    public long generateID(){
        //TODO: Implement id generation method
        return 0;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", dateOfBirth=" + getDateOfBirth() +
                ", patientFullName" + getFirstName() + " " + getLastName() +
                '}';
    }
}
