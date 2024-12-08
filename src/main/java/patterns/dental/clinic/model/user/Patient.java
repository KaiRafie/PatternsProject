package patterns.dental.clinic.model.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class Patient extends User {
    private static long lastId = 0;
    private long patientID;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String loginID, String loginPass, Date dateOfBirth) {
        super(firstName, lastName, loginPass, dateOfBirth);
        patientID = generateID();
    }


    public long generateID(){
        //To be possibly changed (implemented in DB or in Java)
        return lastId++;
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
